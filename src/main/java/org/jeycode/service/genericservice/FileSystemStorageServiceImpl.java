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

import org.jeycode.execptionsmanaged.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementación de un {@link FilesStorageService} que almacena los ficheros subidos
 * dentro del servidor donde se ha desplegado la aplicación.
 *
 */
@Service
@Slf4j
public class FileSystemStorageServiceImpl implements FilesStorageService
{

      private final Path logsLocation,logsToSendLocation,rulesPdfLocation;

      public FileSystemStorageServiceImpl(@Value(LOGS_FILE_LOCATION_VAR) String logsPath,
            @Value(GZIP_TEMP_DIR_LOCATION_VAR) String logsToSendLocation,@Value(RULES_PDF_LOCATION_VAR) String rulesPdfPath)
      {
            this.logsLocation = Paths.get(logsPath);
            this.logsToSendLocation = Paths.get(logsToSendLocation+DIR_TO_COMPRESS);
            this.rulesPdfLocation = Paths.get(rulesPdfPath);
      }

      /**
       * Método que almacena un fichero .pdf en el almacenamiento secundario desde un
       * objeto de tipo {@link org.springframework.web.multipart#MultipartFile}
       * MultipartFile. Se guarda un único fichero con el nombre descrito, donde se
       * guardarán las reglas establecidas.
       * 
       */
      @Override
      public String storeNewPdfRules(MultipartFile file)
      {
            var filename = StringUtils.cleanPath(file.getOriginalFilename());
            var extension = StringUtils.getFilenameExtension(filename);
            var storedFilename = PDF_RULES_NAME;
            var errorMsg = "Error al guardar el fichero ";
            try
            {
                  if (file.isEmpty())
                  {
                        log.error(errorMsg);
                        throw new StorageException(errorMsg + filename);
                  }
                  if (!extension.equals(PDF_EXT_VALID))
                  {
                        var isInvalidExtMsg = errorMsg + ". El fichero no tiene una extensión válida(.pdf).";
                        log.error(isInvalidExtMsg);
                        throw new StorageException(isInvalidExtMsg);
                  }
                  try (InputStream inputStream = file.getInputStream())
                  {
                        Files.copy(inputStream,this.rulesPdfLocation.resolve(storedFilename),StandardCopyOption.REPLACE_EXISTING);
                        return storedFilename;
                  }
            }
            catch (IOException e)
            {
                  log.error(errorMsg);
                  throw new StorageException(errorMsg + filename,e);
            }

      }

      @Async(EXECUTOR_PREPARE_LOGS)
      @Override
      public CompletableFuture<Boolean> prepareLogsPackage()
      {
            return CompletableFuture.supplyAsync(()->
                  {
                        var logs = loadLogFiles();
                        if (logs.isEmpty())
                        {
                              log.error(LOGS_EMPTY);
                              throw new ResponseStatusException(HttpStatus.CONFLICT,LOGS_EMPTY);
                        }
                        logs.forEach(file->
                              {
                                    try
                                    {
                                          Files.copy(file.toPath(),logsToSendLocation,null);
                                    }
                                    catch (IOException ex)
                                    {
                                          log.error(COPY_LOGS_ERROR);
                                          throw new ResponseStatusException(HttpStatus.CONFLICT,COPY_LOGS_ERROR);
                                    }
                              });
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
                              .collect(Collectors.toSet());
            }
            catch (IOException ex)
            {
                  log.error(FILE_READ_ERROR);
                  throw new StorageException(FILE_READ_ERROR,ex);
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

}