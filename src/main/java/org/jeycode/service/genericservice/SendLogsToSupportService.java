package org.jeycode.service.genericservice;

import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

import org.jeycode.utilities.ApplicationExceptionUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendLogsToSupportService implements ApplicationExceptionUtils
{

      private final FilesStorageService filesStorageService;
      private final SendMailService sendMailService;

      public boolean sendLogsToApplicationSupport(String clientErrorMessage)
      {
            String messageToSendWithLogs = clientErrorMessage.isBlank() ? LOGS_REQUEST_DEFAULT_MSG : clientErrorMessage;
            BiConsumer<Boolean,Throwable> action = getAction(messageToSendWithLogs);
            try
            {
                  return filesStorageService.prepareLogsPackage()
                                            .whenCompleteAsync(action)
                                            .get();
            }
            catch (InterruptedException | ExecutionException ex)
            {
                  return false;
            }

      }

      /*
       * *****************************Private Methods*********************************
       */

      private BiConsumer<Boolean,Throwable> getAction(String messageToSendWithLogs)
      {
            BiConsumer<Boolean,Throwable> action = (result,ex)->
                  {
                        if (result)
                        {
                              sendMailService.sendLogsToApplicationSupport(messageToSendWithLogs,filesStorageService.getFileToSend())
                                             .whenCompleteAsync((actionResult,exR)->
                                                   {
                                                         if (actionResult)
                                                         {
                                                               log.info("Se envió el correo a soporte correctamente.");
                                                               filesStorageService.deleteTempDir();
                                                               log.info("Se borraron los directorios temporales para los logs.");
                                                         }
                                                   });
                        }
                  };
            return action;
      }
}
