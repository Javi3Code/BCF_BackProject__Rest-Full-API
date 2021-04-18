package org.jeycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ApplicationRunner
{

      public static void main(String[] args)
      {
            SpringApplication.run(ApplicationRunner.class,args);
      }
//
//      @Bean
//      public CommandLineRunner getCMD()
//      {
//
//            return args->
//                  {
//                        try
//                        {
//                              var compressor = new GZipArchiver();
//                              compressor.setDestFile(compressFileTemporalDestination);
//                              var plexusIoGzipResourceCollection = new PlexusIoGzipResourceCollection();
//                              plexusIoGzipResourceCollection.setFile(fileToSend);
//                              PlexusIoResource nextResource = plexusIoGzipResourceCollection.getResources()
//                                                                                            .next();
//                              compressor.setSource(nextResource);
//                              compressor.compress();
//                              return true;
//                        }
//                        catch (Exception ex)
//                        {
//                              log.error(COMPRESSOR_ERROR_MSG,ex);
//                              throw new ResponseStatusException(HttpStatus.CONFLICT,COMPRESSOR_ERROR_MSG);
//                        }
//                        finally
//                        {
//                              compressor.close();
//                        }
//                  };
//      }

}
