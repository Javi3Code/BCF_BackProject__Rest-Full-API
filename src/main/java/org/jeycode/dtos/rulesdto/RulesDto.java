package org.jeycode.dtos.rulesdto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.jeycode.utilities.ApplicationExceptionUtils;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@ApiModel(description = "Utilizado tanto para entrada como salida en el controlador de reglas.", value = "Reglas DTO")
@Builder(builderMethodName = "of", buildMethodName = "get")
@Data
public class RulesDto implements ApplicationExceptionUtils
{

      @NotNull(message = NONE_CAN_BE_NULL)
      @Min(value = 0, message = RULES_POINTS_NON_VALID)
      private final Integer resultPoints,signPoints,goalsBCFPoints;
}
