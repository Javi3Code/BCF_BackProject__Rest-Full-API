package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.rulesdto.RulesDto;
import org.jeycode.service.genericservice.FilesStorageService;
import org.jeycode.service.repositoryservice.RulesService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rules")
public class RulesController
{

      private final RulesService rulesService;
      private final FilesStorageService fileStorageService;

      @GetMapping
      public ResponseEntity<?> getRulesSet()
      {
            return rulesService.getRulesSet();
      }

      @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
      public ResponseEntity<?> updateRules(@RequestPart(name = "rulesPdf") MultipartFile rulesPdf,
                                           @Valid @RequestPart(required = false, name = "rules") RulesDto rulesDto)
      {
            fileStorageService.storeNewPdfRules(rulesPdf);
            return rulesDto == null ? rulesService.updateToDefaultRules() : rulesService.updateRules(rulesDto);
      }

}
