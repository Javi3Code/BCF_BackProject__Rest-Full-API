package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.concretematchdto.CompleteConcreteMatchDto;
import org.jeycode.dtos.concretematchdto.ConcreteMatchDtoToUpdate;
import org.jeycode.dtos.playerdto.PlayerDtoWithId;
import org.jeycode.dtos.playerfootballmatchdto.JourneyPlayerFootballMatchDto;
import org.jeycode.dtos.playerfootballmatchdto.PlayerFootballmatchDtoToCreate;
import org.jeycode.service.repositoryservice.FootballDayService;
import org.jeycode.utilities.ControllerUtils;
import org.jeycode.utilities.SwaggerStrings;
import org.springframework.http.ResponseEntity;
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

@Api(tags = {SwaggerStrings.JOURNEY_CONTROLLER_TAG})
@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerUtils.FOOTBALLDAY_URL)
public class FootballDayController implements ControllerUtils
{

      private final FootballDayService footballDayService;

      @ApiOperation(value = POST_BET_IN, notes = POST_BET_IN_NOTES, code = CODE_200)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = POST_BET_IN_OK, response = JourneyPlayerFootballMatchDto.class),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @PostMapping
      public ResponseEntity<?> insertOnePlayerResult(@ApiParam(required = true, value = BET_ADD_DESC,
            name = BET_ADD_DTO) @Valid @RequestBody PlayerFootballmatchDtoToCreate plfmDto)
      {
            return footballDayService.insertOnePlayerResult(plfmDto);
      }

      @ApiOperation(value = GET_BET, notes = GET_BET_NOTES, code = CODE_200, responseContainer = LIST_CONTAINER)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = GET_BET_OK, response = PlayerDtoWithId.class),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @GetMapping
      public ResponseEntity<?> getFootballDayData(@ApiParam(value = BET_GET_DESC) @RequestParam(name = FT_DAY_TYPE_OF_DATA_PARAM,
            required = false, defaultValue = ZERO_DFLT_VALUE) int typeOfData)
      {
            return footballDayService.getFootballDayData(typeOfData);
      }

      @ApiOperation(value = PUT_BET_UP, notes = PUT_BET_UP_NOTES, code = CODE_200)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = PUT_BET_UP_OK, response = CompleteConcreteMatchDto.class),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @PutMapping
      public ResponseEntity<?> endTheFootballDay(@ApiParam(required = true, value = BET_UP_DESC,
            name = BET_UP_DTO) @Valid @RequestBody ConcreteMatchDtoToUpdate concreteMatch)
      {
            return footballDayService.endTheFootballDay(concreteMatch);
      }
}
