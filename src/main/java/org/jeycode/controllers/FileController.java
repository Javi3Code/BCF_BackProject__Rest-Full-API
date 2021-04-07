package org.jeycode.controllers;

import javax.servlet.http.HttpServletRequest;

import org.jeycode.service.genericservice.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController
{

      private final StorageService storageService;

      @GetMapping("/{filename:.+}")
      @ResponseBody
      public ResponseEntity<Resource> serveFile(@PathVariable String filename,HttpServletRequest request)
      {
            return storageService.serveFile(filename,request);
      }

}