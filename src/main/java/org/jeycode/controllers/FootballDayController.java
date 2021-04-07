package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.concretematchdto.ConcreteMatchDtoToUpdate;
import org.jeycode.dtos.playerfootballmatchdto.PlayerFootballmatchDtoToCreate;
import org.jeycode.service.repositoryservice.FootballDayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bets")
public class FootballDayController
{

      private final FootballDayService footballDayService;

      @PostMapping
      public ResponseEntity<?> insertOnePlayerResult(@Valid @RequestBody PlayerFootballmatchDtoToCreate plfmDto)
      {
            return footballDayService.insertOnePlayerResult(plfmDto);
      }

      @GetMapping
      public ResponseEntity<?> getFootballDayData(@RequestParam(name = "typeOfData", required = false, defaultValue = "0") int typeOfData)
      {
            return footballDayService.getFootballDayData(typeOfData);
      }

      @PutMapping
      public ResponseEntity<?> endTheFootballDay(@Valid @RequestBody ConcreteMatchDtoToUpdate concreteMatch)
      {
            return footballDayService.endTheFootballDay(concreteMatch);
      }
}
