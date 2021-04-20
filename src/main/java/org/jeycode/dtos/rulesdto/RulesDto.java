package org.jeycode.dtos.rulesdto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.jeycode.utilities.ApplicationExceptionUtils;

import lombok.Builder;
import lombok.Data;

@Builder(builderMethodName = "of", buildMethodName = "get")
@Data
public class RulesDto implements ApplicationExceptionUtils
{

      @NotNull(message = NONE_CAN_BE_NULL)
      @Min(value = 0, message = RULES_POINTS_NON_VALID)
      private final Integer resultPoints,signPoints,goalsBCFPoints;
}
