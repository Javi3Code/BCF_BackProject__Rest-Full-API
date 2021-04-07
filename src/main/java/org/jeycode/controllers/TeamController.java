package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.teamsdto.TeamDto;
import org.jeycode.service.repositoryservice.TeamService;
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
@RequestMapping("/teams")
public class TeamController
{

      private final TeamService teamService;

      @PostMapping
      public ResponseEntity<?> insertOneTeam(@Valid @RequestBody TeamDto teamDto)
      {
            return teamService.insertOneTeam(teamDto);
      }

      @GetMapping
      public ResponseEntity<?> getOneOrMoreTeams()// Temporalmente solo se obtienen todos
      {
            return teamService.getAllTeam();
      }

      @DeleteMapping
      public ResponseEntity<?> deleteOneOrMoreTeams(@RequestParam(required = false, name = "all", defaultValue = "false") boolean all,
                                                    @Valid @RequestBody(required = false) TeamDto teamDto)
      {
            return teamService.tryToDeleteOneOrMoreTeams(all,teamDto);
      }

}
