package org.jeycode.controllers;

import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

import org.jeycode.constants.ApplicationExceptionUtils;
import org.jeycode.service.genericservice.FilesStorageService;
import org.jeycode.service.genericservice.SendMailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
@Slf4j
public class LogsController implements ApplicationExceptionUtils
{

      private final FilesStorageService filesStorageService;
      private final SendMailService sendMailService;

      @GetMapping
      @ResponseBody
      public boolean sendLogsToApplicationSupport(@RequestParam(required = false, defaultValue = LOGS_REQUEST_DEFAULT_MSG) String clientErrorMessage)
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