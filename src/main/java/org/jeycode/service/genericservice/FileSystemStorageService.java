package org.jeycode.service.genericservice;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.jeycode.execptionsmanaged.StorageException;
import org.jeycode.execptionsmanaged.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementación de un {@link StorageService} que almacena los ficheros subidos
 * dentro del servidor donde se ha desplegado la aplicación.
 *
 */
@Service
@Slf4j
public class FileSystemStorageService implements StorageService
{

      private final Path rootLocation;

      public FileSystemStorageService(@Value(FILE_LOCATION_VAR) String path)
      {
            this.rootLocation = Paths.get(path);
      }

      @Override
      public ResponseEntity<Resource> serveFile(String filename,HttpServletRequest request)
      {
            var file = loadAsResource(filename);

            String contentType = null;
            try
            {
                  contentType = request.getServletContext()
                                       .getMimeType(file.getFile()
                                                        .getAbsolutePath());
            }
            catch (IOException ex)
            {
                  log.error(SERVE_FILE_ERROR);
            }

            if (contentType == null)
            {
                  contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                                 .contentType(MediaType.parseMediaType(contentType))
                                 .body(file);
      }

      /**
       * Método que almacena un fichero en el almacenamiento secundario desde un
       * objeto de tipo {@link org.springframework.web.multipart#MultipartFile}
       * MultipartFile
       */
      @Override
      public String store(MultipartFile file)
      {
            var filename = StringUtils.cleanPath(file.getOriginalFilename());
            var extension = StringUtils.getFilenameExtension(filename);
            var storedFilename = RULES_PDF_NAME + "." + extension;
            try
            {
                  if (file.isEmpty())
                  {
                        throw new StorageException("Failed to store empty file " + filename);
                  }
                  if (filename.contains(".."))
                  {
                        // This is a security check
                        throw new StorageException("Cannot store file with relative path outside current directory " + filename);
                  }
                  try (InputStream inputStream = file.getInputStream())
                  {
                        Files.copy(inputStream,this.rootLocation.resolve(storedFilename),StandardCopyOption.REPLACE_EXISTING);
                        return storedFilename;
                  }
            }
            catch (IOException e)
            {
                  throw new StorageException("Failed to store file " + filename,e);
            }

      }

      /**
       * Método que devuelve la ruta de todos los ficheros que hay en el
       * almacenamiento secundario del proyecto.
       */
      @Override
      public Stream<Path> loadAll()
      {
            try
            {
                  return Files.walk(this.rootLocation,1)
                              .filter(path-> !path.equals(this.rootLocation))
                              .map(this.rootLocation::relativize);
            }
            catch (IOException ex)
            {
                  log.error(FILE_READ_ERROR);
                  throw new StorageException(FILE_READ_ERROR,ex);
            }

      }

      /**
       * Método que es capaz de cargar un fichero a partir de su nombre Devuelve un
       * objeto de tipo Path
       */
      @Override
      public Path load(String filename)
      {
            return rootLocation.resolve(filename);
      }

      /**
       * Método que es capaz de cargar un fichero a partir de su nombre Devuelve un
       * objeto de tipo Resource
       */
      @Override
      public Resource loadAsResource(String filename)
      {
            try
            {
                  Path file = load(filename);
                  Resource resource = new UrlResource(file.toUri());
                  if (!resource.exists() || !resource.isReadable())
                  {
                        throw new StorageFileNotFoundException("Could not read file: " + filename);
                  }
                  return resource;
            }
            catch (MalformedURLException e)
            {
                  throw new StorageFileNotFoundException("Could not read file: " + filename,e);
            }
      }

      /**
       * Método que elimina todos los ficheros del almacenamiento secundario del
       * proyecto.
       */
      @Override
      public void deleteAll()
      {
            FileSystemUtils.deleteRecursively(rootLocation.toFile());
      }

      /**
       * Método que inicializa el almacenamiento secundario del proyecto
       */
      @Override
      public void init()
      {
            try
            {
                  Files.createDirectories(rootLocation);
            }
            catch (IOException e)
            {
                  throw new StorageException("Could not initialize storage",e);
            }
      }

      @Override
      public void delete(String filename)
      {
            String justFilename = StringUtils.getFilename(filename);
            try
            {
                  Path file = load(justFilename);
                  Files.deleteIfExists(file);
            }
            catch (IOException e)
            {
                  throw new StorageException("Error trying to delete a file",e);
            }

      }

}