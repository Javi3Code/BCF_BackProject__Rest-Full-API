package org.jeycode.controllers;

import org.jeycode.service.repositoryservice.SeasonService;
import org.jeycode.utilities.ControllerUtils;
import org.jeycode.utilities.SwaggerStrings;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {SwaggerStrings.SEASONS_CONTROLLER_TAG})
@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerUtils.SEASONS_URL)
public class SeasonRestartController implements ControllerUtils
{

      private final SeasonService seasonStartService;

      @ApiOperation(value = PUT_SEASON_INIT, notes = PUT_SEASON_INIT_NOTES, code = CODE_200)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = PUT_SEASON_INIT_OK),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @PutMapping
      public boolean resetSeason()
      {
            return seasonStartService.tryToResetSeason();
      }

}
