package org.jeycode.dtos.concretematchdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "Es el DTO que se espera en la operacion PUT en el controlador de apuestas.", value = "UP - Partido DTO")
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class ConcreteMatchDtoToUpdate extends ConcreteMatchDtoToCreate
{

      @NotNull(message = NON_VALID_ID_NULL)
      @Positive(message = NON_VALID_ID_VALUE)
      protected long concreteMatchId;

      @NotNull(message = MATCHRESULT_NON_NULL)
      @NotBlank(message = MATCHRESULT_NON_BLANK)
      @Pattern(regexp = MATCH_RESULT_REGEXP, message = MATCHRESULT_NON_VALID_PATTERN)
      private String resultOfConcreteMatch;
}
