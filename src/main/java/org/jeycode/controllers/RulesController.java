package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.rulesdto.RulesDto;
import org.jeycode.service.repositoryservice.RulesService;
import org.jeycode.utilities.ControllerUrl;
import org.jeycode.utilities.SwaggerStrings;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {SwaggerStrings.RULES_CONTROLLER_TAG})
@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerUrl.RULES_URL)
public class RulesController implements SwaggerStrings
{

      private final RulesService rulesService;

      @ApiOperation(value = GET_RULES_SET, notes = GET_RULES_SET_NOTE, code = CODE_200, produces = Json, response = RulesDto.class)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = RULES_GET_RULES_SET_OK, response = RulesDto.class),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @GetMapping
      public ResponseEntity<?> getRulesSet()
      {
            return rulesService.getRulesSet();
      }

      @ApiOperation(value = "", notes = "", code = CODE_200, consumes = Json + COMMA + MultipartFile, produces = Json,
            response = RulesDto.class)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = UPDATE_OK_MSG, response = RulesDto.class, reference = RULES_DTO),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
      public ResponseEntity<?> updateRules(@ApiParam(allowMultiple = false, required = true,
            value = "PDF con las nuevas reglas a establecer")
      @RequestPart(name = "rulesPdf") MultipartFile rulesPdf,
                                           @Valid
                                           @RequestPart(required = false, name = "rules") RulesDto rulesDto)
      {
            return rulesDto == null ? rulesService.updateToDefaultRules(rulesPdf) : rulesService.updateRules(rulesPdf,rulesDto);
      }

}
