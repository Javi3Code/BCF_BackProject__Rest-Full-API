package org.jeycode.service.genericservice;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.jeycode.execptionsmanaged.GenericBackendException;
import org.jeycode.execptionsmanaged.RequestParamException;
import org.jeycode.execptionsmanaged.StorageException;
import org.jeycode.execptionsmanaged.StorageFileNotFoundException;
import org.jeycode.service.components.ZipFileComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementación de un {@link FilesStorageService} que almacena los ficheros
 * subidos dentro del servidor donde se ha desplegado la aplicación.
 *
 */
@Service
@Slf4j
public class FileSystemStorageServiceImpl implements FilesStorageService
{

      private final Path logsLocation,logsToSendLocation,logsCompressedLocation,rulesPdfLocation,compressFileTemporalDestination;
      private final ZipFileComponent zipFileService;

      public FileSystemStorageServiceImpl(@Value(LOGS_FILE_LOCATION_VAR) String logsPath,
            @Value(GZIP_TEMP_DIR_LOCATION_VAR) String logsToSendLocation,@Value(RULES_PDF_LOCATION_VAR) String rulesPdfPath,
            ZipFileComponent zipFileService)
      {
            this.logsLocation = Paths.get(logsPath);
            this.logsToSendLocation = Paths.get(logsToSendLocation + DIR_TO_COMPRESS);
            this.logsCompressedLocation = Paths.get(logsToSendLocation + DIR_TO_MOVE);
            this.rulesPdfLocation = Paths.get(rulesPdfPath);
            this.compressFileTemporalDestination = Paths.get(logsToSendLocation);
            this.zipFileService = zipFileService;
      }

      /**
       * Método que almacena un fichero .pdf en el almacenamiento secundario desde un
       * objeto de tipo {@link org.springframework.web.multipart#MultipartFile}
       * MultipartFile. Se guarda un único fichero con el nombre descrito, donde se
       * guardarán las reglas establecidas.
       * 
       */
      @Override
      public void storeNewPdfRules(MultipartFile file)
      {
            try
            {
                  validate(file);
                  try (InputStream inputStream = file.getInputStream())
                  {
                        Files.copy(inputStream,this.rulesPdfLocation.resolve(PDF_RULES_NAME),StandardCopyOption.REPLACE_EXISTING);
                  }
            }
            catch (Exception ex)
            {
                  var erroMessage = ex.getMessage();
                  log.error(erroMessage,ex);
                  HttpStatus status = ex instanceof GenericBackendException ? ((GenericBackendException)ex).getStatus() :
                        HttpStatus.INTERNAL_SERVER_ERROR;
                  throw new ResponseStatusException(status,erroMessage);
            }

      }

      @Async(EXECUTOR_PREPARE_LOGS)
      @Override
      public CompletableFuture<Boolean> prepareLogsPackage()
      {
            return CompletableFuture.supplyAsync(()->
                  {
                        var logs = loadLogFiles();
                        log.info("Se han recuperado todos los ficheros de logs a enviar.");
                        if (logs.isEmpty())
                        {
                              log.error(LOGS_EMPTY);
                              throw new ResponseStatusException(HttpStatus.CONFLICT,LOGS_EMPTY);
                        }
                        tryToCreateDir();
                        logs.forEach(file->
                              {
                                    try
                                    {
                                          var pathTarget = Paths.get(logsToSendLocation.toString() + "/" + file.getName());
                                          Files.copy(file.toPath(),pathTarget,StandardCopyOption.REPLACE_EXISTING);
                                          log.info("Se copió un fichero de logs en el directorio: " + DIR_TO_COMPRESS);
                                    }
                                    catch (Exception ex)
                                    {
                                          log.error(COPY_LOGS_ERROR,ex);
                                          throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,COPY_LOGS_ERROR);
                                    }
                              });
                        log.info("Se cargaron todos los logs a comprimir en el directorio: " + DIR_TO_COMPRESS);
                        zipFileService.compress();
                        log.info("Se comprimieron los archivos de log.");
                        return true;
                  });
      }

      @Override
      public Set<File> loadLogFiles()
      {
            try
            {
                  Comparator<File> lastModification = (file,otherFile)-> (file.lastModified() > otherFile.lastModified() ? -1 : 1);
                  return Files.list(logsLocation)
                              .map(Path::toFile)
                              .sorted(lastModification)
                              .limit(5)
                              .peek(file-> log.info(file.getName() + " es uno de los ficheros de logs a enviar."))
                              .collect(Collectors.toSet());
            }
            catch (IOException ex)
            {
                  log.error(FILE_READ_ERROR,ex);
                  throw new StorageException(FILE_READ_ERROR,ex);
            }
      }

      @Override
      public ResponseEntity<Resource> serveApplicationLicenseFile(HttpServletRequest request)
      {
            try
            {
                  Path filePath = Path.of(".");
                  var file = new UrlResource(filePath.toUri());
                  if (!file.exists() || !file.isReadable())
                  {
                        throw new StorageFileNotFoundException(FILESERVICE_LICENSE_NOT_FOUND);
                  }
                  var contentType = request.getServletContext()
                                           .getMimeType(file.getFile()
                                                            .getAbsolutePath());

                  if (contentType == null)
                  {
                        contentType = LICENSE_NOT_FOUND;
                  }

                  return ResponseEntity.ok()
                                       .contentType(MediaType.parseMediaType(contentType))
                                       .body(file);
            }
            catch (StorageFileNotFoundException ex)
            {
                  log.error(ex.getMessage(),ex);
                  throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
            }
            catch (Exception ex)
            {
                  log.error(ex.getMessage(),ex);
                  throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
            }
      }

      @Override
      public boolean deleteTempDir()
      {
            try
            {
                  var deleteIn = FileSystemUtils.deleteRecursively(compressFileTemporalDestination.toFile());
                  if (!deleteIn)
                  {
                        throw new Exception();
                  }
                  var deleteIfExists = Files.deleteIfExists(compressFileTemporalDestination);
                  if (deleteIfExists)
                  {
                        log.info("Se borró el directorio temporal para compresión de logs.");
                  }
                  return deleteIfExists;
            }
            catch (Exception ex)
            {
                  log.error(DELETE_TEMP_DIR_COMPRESSION,ex);
                  throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,DELETE_TEMP_DIR_COMPRESSION);
            }
      }

      /**
       * Método que elimina todos los ficheros de logs del almacenamiento secundario
       * del proyecto.
       */
      @Override
      public void deleteAllLogs()
      {
            FileSystemUtils.deleteRecursively(logsLocation.toFile());
      }

      @Override
      public File getFileToSend()
      {
            return logsCompressedLocation.toFile();
      }

      /*
       * *************Private Methods***************
       */
      private void tryToCreateDir()
      {
            try
            {
                  Files.createDirectories(logsToSendLocation);
                  log.info("Se ha creado el directorio temporal: " + logsToSendLocation.toString());
            }
            catch (Exception ex)
            {
                  log.error(CREATE_DIR_LOGZIP_ERR,ex);
                  throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,CREATE_DIR_LOGZIP_ERR);
            }
      }

      private void validate(MultipartFile file)
      {
            if (file == null || file.isEmpty())
            {
                  throw new RequestParamException(PART_PDF_NO_NULL_OR_EMPTY);
            }
            var filename = StringUtils.cleanPath(file.getOriginalFilename());
            var extension = StringUtils.getFilenameExtension(filename);
            log.info("extensión del fichero-> " + extension);
            if (!extension.equals(PDF_EXT_VALID))
            {
                  var isInvalidExtMsg = NOT_VALID_EXT_PDF + STORE_PDF_ERR + filename;
                  throw new RequestParamException(isInvalidExtMsg);

            }
            log.info("El pdf de reglas es válido, se procede a guardar en el storage secundario.");
      }

}