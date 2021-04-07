package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.playerdto.PlayerDtoToCreate;
import org.jeycode.dtos.playerdto.PlayerDtoToUpdate;
import org.jeycode.service.repositoryservice.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/players")
public class PlayerController
{

      private final PlayerService playerService;

      @PostMapping
      public ResponseEntity<?> insertOnePlayer(@Valid @RequestBody PlayerDtoToCreate playerDto)
      {
            return playerService.insertOnePlayer(playerDto);
      }

      @PutMapping
      public ResponseEntity<?> updateOnePlayer(@Valid @RequestBody PlayerDtoToUpdate playerDto)
      {
            return playerService.updateOnePlayer(playerDto);
      }

      @GetMapping
      public ResponseEntity<?> getAllPlayers(@RequestParam(required = false, defaultValue = "0", name = "level") int level)
      {
            return playerService.getAllPlayers(level);
      }

      @DeleteMapping
      public ResponseEntity<?> deleteOnePlayer(@Valid @RequestBody PlayerDtoToUpdate playerDto)
      {
            return playerService.deleteOnePlayer(playerDto);
      }
}
