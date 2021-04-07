package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.rulesdto.RulesDto;
import org.jeycode.service.repositoryservice.RulesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rules")
public class RulesController
{

      private final RulesService rulesService;

      @GetMapping
      public ResponseEntity<?> getRulesSet()
      {
            return rulesService.getRulesSet();
      }

      @PutMapping
      public ResponseEntity<?> updateRules(@Valid @RequestBody(required = false) RulesDto rulesDto)
      {
            return rulesDto == null ? rulesService.updateToDefaultRules() : rulesService.updateRules(rulesDto);
      }

}
