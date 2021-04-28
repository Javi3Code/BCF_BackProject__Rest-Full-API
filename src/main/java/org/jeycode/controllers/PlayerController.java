package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.playerdto.AbstractPlayerDto;
import org.jeycode.dtos.playerdto.PlayerDtoToCreate;
import org.jeycode.dtos.playerdto.PlayerDtoToUpdate;
import org.jeycode.dtos.playerdto.PlayerDtoWithId;
import org.jeycode.service.repositoryservice.PlayerService;
import org.jeycode.utilities.ControllerUtils;
import org.jeycode.utilities.SwaggerStrings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {SwaggerStrings.PLAYER_CONTROLLER_TAG})
@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerUtils.PLAYERS_URL)
public class PlayerController implements ControllerUtils
{

      private final PlayerService playerService;

      @ApiOperation(value = POST_PLAYER_IN, notes = POST_PLAYER_IN_NOTES, code = CODE_200)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = POST_PLAYER_IN_OK, response = AbstractPlayerDto.class),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_409, message = ERR_409 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @PostMapping
      public ResponseEntity<?> insertOnePlayer(@ApiParam(required = true,
            value = PLAYER_ADD_DESC) @Valid @RequestBody PlayerDtoToCreate playerDto)
      {
            return playerService.insertOnePlayer(playerDto);
      }

      @ApiOperation(value = PUT_PLAYER_UP, notes = PUT_PLAYER_UP_NOTES, code = CODE_200)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = PUT_PLAYER_UP_OK, response = PlayerDtoWithId.class),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_409, message = ERR_409 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @PutMapping
      public ResponseEntity<?> updateOnePlayer(@ApiParam(required = true,
            value = PLAYER_UP_DESC) @Valid @RequestBody PlayerDtoToUpdate playerDto)
      {
            return playerService.updateOnePlayer(playerDto);
      }

      @ApiOperation(value = GET_PLAYERS, notes = GET_PLAYERS_NOTES, code = CODE_200, responseContainer = LIST_CONTAINER)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = GET_PLAYERS_OK_0, response = AbstractPlayerDto.class),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @GetMapping
      public ResponseEntity<?> getAllPlayers(@ApiParam(allowableValues = PLAYERS_GET_ALOWABLES_VALUES,
            defaultValue = ZERO_DFLT_VALUE, value = PLAYERS_GET_DESC, name = PLAYER_LEVEL_DATA_NAME) @RequestParam(
                  required = false, defaultValue = ZERO_DFLT_VALUE, name = PLAYER_LEVEL_NAME) int level)
      {
            return playerService.getAllPlayers(level);
      }

      @ApiOperation(value = DELETE_PLAYER_IN, notes = DELETE_PLAYER_IN_NOTES, code = CODE_200)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = DELETE_PLAYER_IN_OK),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @DeleteMapping
      public ResponseEntity<?> deleteOnePlayer(@ApiParam(required = true,
            value = PLAYER_DEL_DESC) @Valid @RequestBody PlayerDtoToUpdate playerDto)
      {
            return playerService.deleteOnePlayer(playerDto);
      }
}
