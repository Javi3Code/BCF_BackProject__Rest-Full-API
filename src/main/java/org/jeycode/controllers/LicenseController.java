package org.jeycode.controllers;

import javax.servlet.http.HttpServletRequest;

import org.jeycode.service.genericservice.FilesStorageService;
import org.jeycode.utilities.ControllerUtils;
import org.jeycode.utilities.SwaggerStrings;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {SwaggerStrings.LICENSE_CONTROLLER_TAG})
@RequiredArgsConstructor
@Controller
@RequestMapping(ControllerUtils.LICENSE_URL)
public class LicenseController implements ControllerUtils
{

      private final FilesStorageService fileStorageService;

      @ApiOperation(value = GET_LICENSE, notes = GET_LICENSE_NOTES, code = CODE_200)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = GET_LICENSE_OK),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @GetMapping
      public ResponseEntity<Resource> serveApplicationLicense(HttpServletRequest request)
      {
            return fileStorageService.serveApplicationLicenseFile(request);
      }
}
