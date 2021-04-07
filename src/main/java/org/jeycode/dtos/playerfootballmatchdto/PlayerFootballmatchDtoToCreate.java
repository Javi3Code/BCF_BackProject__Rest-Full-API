package org.jeycode.dtos.playerfootballmatchdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.jeycode.constants.ApplicationExceptionUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@NoArgsConstructor
public class PlayerFootballmatchDtoToCreate implements ApplicationExceptionUtils
{

      @NotNull(message = PFM_PLAYERNICK_CANT_BE_NULL)
      @NotBlank(message = PFM_PLAYERNICK_CANT_BE_BLANK)
      private String playerNick;

      @NotNull(message = MATCHRESULT_NON_NULL)
      @NotBlank(message = MATCHRESULT_NON_BLANK)
      @Pattern(regexp = MATCH_RESULT_REGEXP, message = MATCHRESULT_NON_VALID)
      private String playerFootBallMatchResult;
}
