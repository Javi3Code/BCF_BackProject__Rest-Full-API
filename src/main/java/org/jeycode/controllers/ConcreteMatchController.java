package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.concretematchdto.ConcreteMatchDtoToCreate;
import org.jeycode.service.repositoryservice.ConcreteMatchService;
import org.jeycode.utilities.ControllerUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerUrl.CONCRETEMATCHS_URL)
public class ConcreteMatchController
{

      private final ConcreteMatchService concreteMatchService;

      @PostMapping
      public ResponseEntity<?> insertOneConcreteMatch(@Valid
      @RequestBody ConcreteMatchDtoToCreate concreteMatchDto)
      {
            return concreteMatchService.insertOneConcreteMatch(concreteMatchDto);
      }

      @GetMapping
      public ResponseEntity<?> getOpenMatch()
      {
            return concreteMatchService.getOpenMatch();
      }

      @DeleteMapping
      public ResponseEntity<?> deleteConcreteMatch(@RequestParam(required = false, defaultValue = "false", name = "all") boolean all)
      {
            return concreteMatchService.deleteConcreteMatch(all);
      }
}
