package org.jeycode.controllers;

import org.jeycode.constants.ApplicationExceptionUtils;
import org.jeycode.service.genericservice.SendMailService;
import org.jeycode.service.genericservice.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController implements ApplicationExceptionUtils
{

      private final StorageService storageService;
      private final SendMailService sendMailService;

      @GetMapping
      @ResponseBody
      public boolean sendLoggsToApplicationSupport(@RequestParam(required = false, defaultValue = LOGS_REQUEST_DEFAULT_MSG) String errorMessage)
      {
            try
            {
                  if (errorMessage.isBlank())
                  {
                        errorMessage = LOGS_REQUEST_DEFAULT_MSG;
                  }
                  sendMailService.sendLogsToApplicationSupport(storageService.loadLogFiles(),errorMessage);
                  return true;
            }
            catch (Exception ex)
            {
                  throw new ResponseStatusException(HttpStatus.CONFLICT,"Error en el envío de logs a soporte." + ex.getMessage());
            }
      }

}