package org.jeycode.dtos.concretematchdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class ConcreteMatchDtoToUpdate extends ConcreteMatchDtoToCreate
{

      @NotNull(message = NON_VALID_ID)
      @Positive(message = NON_VALID_ID)
      protected long concreteMatchId;

      @NotNull(message = MATCHRESULT_NON_VALID)
      @NotBlank(message = MATCHRESULT_NON_VALID)
      @Pattern(regexp = MATCH_RESULT_REGEXP, message = MATCHRESULT_NON_VALID)
      private String resultOfConcreteMatch;
}
