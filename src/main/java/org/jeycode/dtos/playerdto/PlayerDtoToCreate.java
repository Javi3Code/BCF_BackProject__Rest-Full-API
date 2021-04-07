package org.jeycode.dtos.playerdto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jeycode.constants.ApplicationExceptionUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@NoArgsConstructor
public class PlayerDtoToCreate implements ApplicationExceptionUtils
{

      @NotNull(message = PLAYERNAME_NON_VALID)
      @NotBlank(message = PLAYERNAME_NON_VALID)
      @Size(max = 20, min = 1, message = PLAYERNAME_NON_VALID)
      protected String playerNick;
      @NotNull(message = PLAYERMAIL_NON_VALID)
      @NotBlank(message = PLAYERMAIL_NON_VALID)
      @Size(max = 50, message = PLAYERMAIL_NON_VALID)
      @Email(regexp = MAIL_REGEXP, message = PLAYERMAIL_NON_VALID)
      protected String playerMail;
}
