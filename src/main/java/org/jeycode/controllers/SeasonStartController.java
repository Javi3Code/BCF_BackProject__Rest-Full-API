package org.jeycode.controllers;

import org.jeycode.service.repositoryservice.SeasonService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seasons")
public class SeasonStartController
{

      private final SeasonService seasonStartService;

      @PutMapping
      public boolean resetSeason()
      {
            return seasonStartService.tryToResetSeason();
      }

}
