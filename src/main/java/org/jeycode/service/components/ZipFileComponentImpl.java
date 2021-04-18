package org.jeycode.service.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.codehaus.plexus.archiver.gzip.GZipCompressor;
import org.codehaus.plexus.archiver.gzip.PlexusIoGzipResourceCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ZipFileComponentImpl implements ZipFileComponent
{

      private final GZipCompressor compressor;
      private final File compressFileTemporalDestination;
      @Getter
      private final File fileToSend;

      public ZipFileComponentImpl(GZipCompressor compressor,@Value(GZIP_TEMP_DIR_LOCATION_VAR) String location)
      {
            this.compressor = compressor;
            compressFileTemporalDestination = new File(location);
            fileToSend = new File(compressFileTemporalDestination.getAbsolutePath() + DIR_TO_COMPRESS);
      }

      @Override
      public boolean compress()
      {
            try
            {
                  compressor.setDestFile(compressFileTemporalDestination);
                  var plexusIoGzipResourceCollection = new PlexusIoGzipResourceCollection();
                  plexusIoGzipResourceCollection.setFile(fileToSend);
                  compressor.setSource(plexusIoGzipResourceCollection.getResources()
                                                                     .next());
                  compressor.compress();
                  compressor.close();
                  return true;
            }
            catch (Exception ex)
            {
                  log.error(COMPRESSOR_ERROR_MSG);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,COMPRESSOR_ERROR_MSG);
            }
      }

      @Override
      public boolean deleteTempDir()
      {
            try
            {
                  var deleteIfExists = Files.deleteIfExists(compressFileTemporalDestination.toPath());
                  if (deleteIfExists)
                  {
                        log.info("Se borró el directorio temporal para compresión de logs.");
                  }
                  return deleteIfExists;
            }
            catch (IOException ex)
            {
                  log.error(DELETE_TEMP_DIR_COMPRESSION);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,DELETE_TEMP_DIR_COMPRESSION);
            }
      }

}
