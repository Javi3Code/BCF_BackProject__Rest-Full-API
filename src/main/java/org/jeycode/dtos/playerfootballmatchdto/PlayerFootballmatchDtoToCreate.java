package org.jeycode.dtos.playerfootballmatchdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.jeycode.utilities.ApplicationExceptionUtils;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "Usado para crear una apuesta en el controlador de estas.", value = "Externo - CR - Apuesta DTO",
      subTypes = {PlayerFootballMatchDtoToUpdate.class})
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
      @Pattern(regexp = MATCH_RESULT_REGEXP, message = MATCHRESULT_NON_VALID_PATTERN)
      private String playerFootBallMatchResult;
}
