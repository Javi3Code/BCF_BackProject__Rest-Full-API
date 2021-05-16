package org.jeycode.controllers;

import org.jeycode.service.genericservice.SendLogsToSupportService;
import org.jeycode.utilities.ControllerUtils;
import org.jeycode.utilities.SwaggerStrings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {SwaggerStrings.LOGS_CONTROLLER_TAG})
@RequiredArgsConstructor
@Controller
@RequestMapping(ControllerUtils.LOGS_URL)
public class LogsController implements ControllerUtils
{

      private final SendLogsToSupportService sendLogsToSupportService;

      @ApiOperation(value = GET_SEND_LOGS, notes = GET_SEND_LOGS_NOTES, code = CODE_200)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = GET_SEND_LOGS_OK),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_409, message = ERR_409 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @GetMapping @ResponseBody
      public boolean sendLogsToApplicationSupport(@ApiParam(defaultValue = LOGS_REQUEST_DEFAULT_MSG, value = GET_SEND_LOGS_DESC,
            name = LOGS_REQUEST_DEFAULT_MSG_NAME) @RequestParam(required = false,
                  defaultValue = LOGS_REQUEST_DEFAULT_MSG) String clientErrorMessage)
      { 
            return sendLogsToSupportService.sendLogsToApplicationSupport(clientErrorMessage);
      }

}