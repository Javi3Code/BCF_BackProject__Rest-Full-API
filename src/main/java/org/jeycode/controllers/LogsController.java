package org.jeycode.controllers;

import org.jeycode.constants.ApplicationExceptionUtils;
import org.jeycode.service.genericservice.SendLogsToSupportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/logs")
public class LogsController implements ApplicationExceptionUtils
{

      private final SendLogsToSupportService sendLogsToSupportService;

      @GetMapping
      @ResponseBody
      public boolean sendLogsToApplicationSupport(@RequestParam(required = false, defaultValue = LOGS_REQUEST_DEFAULT_MSG) String clientErrorMessage)
      {
            return sendLogsToSupportService.sendLogsToApplicationSupport(clientErrorMessage);
      }

}