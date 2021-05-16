package org.jeycode.controllers;

import javax.validation.Valid;

import org.jeycode.dtos.concretematchdto.AbstractConcreteMatchDto;
import org.jeycode.dtos.concretematchdto.CompleteConcreteMatchDto;
import org.jeycode.dtos.concretematchdto.ConcreteMatchDtoToCreate;
import org.jeycode.service.repositoryservice.ConcreteMatchService;
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

@Api(tags = {SwaggerStrings.CONCRETEMATCH_CONTROLLER_TAG})
@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerUtils.CONCRETEMATCHS_URL)
public class ConcreteMatchController implements ControllerUtils
{

      private final ConcreteMatchService concreteMatchService;

      @ApiOperation(value = POST_CONCRETEMATCH_IN, notes = POST_CONCRETEMATCH_IN_NOTES, code = CODE_200)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = POST_CONCRETEMATCH_IN_OK, response = AbstractConcreteMatchDto.class),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @PostMapping
      public ResponseEntity<?> insertOneConcreteMatch(@ApiParam(required = true, value = CONCRETEMATCH_ADD_DESC,
            name = CONCRETEMATCH_ADD_DTO) @Valid @RequestBody ConcreteMatchDtoToCreate concreteMatchDto)
      {
            return concreteMatchService.insertOneConcreteMatch(concreteMatchDto);
      }

      @ApiOperation(value = GET_CONCRETEMATCH, notes = GET_CONCRETEMATCH_NOTES, code = CODE_200, responseContainer = LIST_CONTAINER)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = GET_CONCRETEMATCH_OK, response = CompleteConcreteMatchDto.class),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @GetMapping
      public ResponseEntity<?> getOpenMatch()
      {
            return concreteMatchService.getOpenMatch();
      }

      @ApiOperation(value = DELETE_CONCRETEMATCH_IN, notes = DELETE_CONCRETEMATCH_IN_NOTES, code = CODE_200)
      @ApiResponses(value = {@ApiResponse(code = CODE_200, message = DELETE_CONCRETEMATCH_IN_OK),
                             @ApiResponse(code = CODE_400, message = ERR_400 + DEFAULT_ERROR_JSON_RESPONSE),
                             @ApiResponse(code = CODE_500, message = ERR_500 + DEFAULT_ERROR_JSON_RESPONSE)})
      @DeleteMapping
      public ResponseEntity<?> deleteConcreteMatch(@ApiParam(value = CONCRETEMATCH_DEL_DESC) @RequestParam(required = false,
            defaultValue = FALSE, name = ALL_PARAM) boolean all)
      {
            return concreteMatchService.deleteConcreteMatch(all);
      }
}
