package org.jeycode.service.genericservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.codehaus.plexus.archiver.gzip.GZipCompressor;
import org.codehaus.plexus.archiver.gzip.PlexusIoGzipResourceCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ZipFileServiceImpl implements ZipFileService
{

      private final GZipCompressor compressor;

      private final File compressFileTemporalDestination;

      public ZipFileServiceImpl(GZipCompressor compressor,@Value(GZIP_TEMP_DIR_LOCATION_VAR) String location)
      {
            this.compressor = compressor;
            compressFileTemporalDestination = new File(location);
      }

      @Override
      public boolean compress()
      {
            try
            {
                  compressor.setDestFile(compressFileTemporalDestination);
                  var plexusIoGzipResourceCollection = new PlexusIoGzipResourceCollection();
                  plexusIoGzipResourceCollection.setFile(new File(compressFileTemporalDestination.getAbsolutePath() + DIR_TO_COMPRESS));
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
                  return Files.deleteIfExists(compressFileTemporalDestination.toPath());
            }
            catch (IOException ex)
            {
                  log.error(DELETE_TEMP_DIR_COMPRESSION);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,DELETE_TEMP_DIR_COMPRESSION);
            }
      }

}
