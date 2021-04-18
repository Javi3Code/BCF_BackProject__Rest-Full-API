package org.jeycode.service.components;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

@Component
@Slf4j
public class ZipFileComponentImpl implements ZipFileComponent
{

      private final File compressFileTemporalDestination,fileToSend;
      private final String temporalFile;

      public ZipFileComponentImpl(@Value(GZIP_TEMP_DIR_LOCATION_VAR) String location)
      {
            compressFileTemporalDestination = new File(location);
            temporalFile = compressFileTemporalDestination.getAbsolutePath() + DIR_TO_MOVE;
            fileToSend = new File(compressFileTemporalDestination.getAbsolutePath() + DIR_TO_COMPRESS);
      }

      @Override
      public boolean compress()
      {
            try
            {
                  var zipFile = new ZipFile(temporalFile);
                  var zipFileParameters = new ZipParameters();
                  zipFileParameters.setEncryptFiles(true);
                  zipFileParameters.setCompressionLevel(CompressionLevel.ULTRA);
                  zipFileParameters.setCompressionMethod(CompressionMethod.DEFLATE);
                  zipFileParameters.setEncryptionMethod(EncryptionMethod.AES);
                  zipFileParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
                  zipFile.setRunInThread(true);
                  zipFile.setPassword(ZIP_PASS.toCharArray());
                  zipFile.addFolder(fileToSend,zipFileParameters);
                  log.info("Se ha comprimido con éxito el fichero.");
            }
            catch (Exception ex)
            {
                  log.error(COMPRESSOR_ERROR_MSG,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,COMPRESSOR_ERROR_MSG);
            }
            return true;

      }

}
