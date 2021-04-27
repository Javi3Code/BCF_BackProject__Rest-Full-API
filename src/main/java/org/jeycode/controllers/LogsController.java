package org.jeycode.controllers;

import org.jeycode.service.genericservice.SendLogsToSupportService;
import org.jeycode.utilities.ApplicationExceptionUtils;
import org.jeycode.utilities.ControllerUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping(ControllerUtils.LOGS_URL)
public class LogsController implements ApplicationExceptionUtils
{

      private final SendLogsToSupportService sendLogsToSupportService;

      @GetMapping
      @ResponseBody
      public boolean sendLogsToApplicationSupport(@RequestParam(required = false,
            defaultValue = LOGS_REQUEST_DEFAULT_MSG) String clientErrorMessage)
      {
            return sendLogsToSupportService.sendLogsToApplicationSupport(clientErrorMessage);
      }

}