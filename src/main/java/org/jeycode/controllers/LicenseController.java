package org.jeycode.controllers;

import javax.servlet.http.HttpServletRequest;

import org.jeycode.service.genericservice.FilesStorageService;
import org.jeycode.utilities.ControllerUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping(ControllerUtils.LICENSE_URL)
public class LicenseController
{

      private final FilesStorageService fileStorageService;

      @GetMapping
      public ResponseEntity<Resource> serveApplicationLicense(HttpServletRequest request)
      {
            return fileStorageService.serveApplicationLicenseFile(request);
      }
}
