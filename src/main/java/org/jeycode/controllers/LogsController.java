package org.jeycode.controllers;

import java.util.function.BiConsumer;

import org.jeycode.constants.ApplicationExceptionUtils;
import org.jeycode.service.genericservice.SendMailService;
import org.jeycode.service.genericservice.FilesStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

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
            try
            {
                  String messageToSendWithLogs = clientErrorMessage.isBlank() ? LOGS_REQUEST_DEFAULT_MSG : clientErrorMessage;
                  BiConsumer<Boolean,Throwable> action = (result,ex)->
                        {
                              if (result)
                              {
                                    sendMailService.sendLogsToApplicationSupport(messageToSendWithLogs);
                              }
                              else
                              {
                                    log.error(ex.getMessage(),ex.getStackTrace());
                                    throw new ResponseStatusException(HttpStatus.CONFLICT,ex.getMessage());
                              }
                        };
                  return !filesStorageService.prepareLogsPackage()
                                        .whenCompleteAsync(action)
                                        .isCompletedExceptionally();
            }
            catch (Exception ex)
            {
                  log.error(ex.getMessage());
                  throw new ResponseStatusException(HttpStatus.CONFLICT,SEND_LOGS_ERROR + ex.getMessage());
            }
      }

}