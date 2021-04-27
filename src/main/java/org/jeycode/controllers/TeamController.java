package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.teamsdto.TeamDto;
import org.jeycode.service.repositoryservice.TeamService;
import org.jeycode.utilities.ControllerUtils;
import org.jeycode.utilities.SwaggerStrings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

@Api(tags = {SwaggerStrings.TEAM_CONTROLLER_TAG})
@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerUtils.TEAMS_URL)
public class TeamController implements ControllerUtils
{

      private final TeamService teamService;

      @ApiOperation(value = POST_TEAM_IN, notes = POST_TEAM_IN_NOTES, code = CODE_200, response = TeamDto.class)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = POST_TEAM_IN_OK),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_409, message = ERR_409 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @PostMapping
      public ResponseEntity<?> insertOneTeam(@ApiParam(required = true, value = TEAM_DTO_ADD_DESC) @Valid @RequestBody TeamDto teamDto)
      {
            return teamService.insertOneTeam(teamDto);
      }

      @ApiOperation(value = GET_TEAMS, notes = GET_TEAMS_NOTES, code = CODE_200, responseContainer = LIST_CONTAINER,
            response = TeamDto.class)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = GET_TEAMS_OK),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @GetMapping
      public ResponseEntity<?> getOneOrMoreTeams()// Temporalmente solo se obtienen todos
      {
            return teamService.getAllTeam();
      }

      @ApiOperation(value = DELETE_TEAM_IN, notes = DELETE_TEAM_IN_NOTES, code = CODE_200, responseContainer = LIST_CONTAINER,
            response = TeamDto.class)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = DELETE_TEAM_IN_OK),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_409, message = ERR_409 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @DeleteMapping
      public ResponseEntity<?> deleteOneOrMoreTeams(@ApiParam(name = ALL_PARAM, value = ALL_PARAM_DESC) @RequestParam(required = false,
            name = ALL_PARAM, defaultValue = FALSE) boolean all,
                                                    @ApiParam(value = TEAM_DTO_DEL_DESC) @Valid @RequestBody(
                                                          required = false) TeamDto teamDto)
      {
            return teamService.tryToDeleteOneOrMoreTeams(all,teamDto);
      }

}
