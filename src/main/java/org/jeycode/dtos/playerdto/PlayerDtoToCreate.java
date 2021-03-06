package org.jeycode.dtos.playerdto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jeycode.utilities.ApplicationExceptionUtils;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "Es el DTO que se espera en la operación POST en el controlador de jugadores.", value = "CR - Jugador DTO",
      subTypes = {PlayerDtoToUpdate.class})
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@NoArgsConstructor
public class PlayerDtoToCreate implements ApplicationExceptionUtils
{

      @NotNull(message = PLAYERNAME_NON_NULL)
      @NotBlank(message = PLAYERNAME_NON_BLANK)
      @Size(max = 20, min = 1, message = PLAYERNAME_NON_VALID_SIZE)
      protected String playerNick;
      @NotNull(message = PLAYERMAIL_NON_NULL)
      @NotBlank(message = PLAYERMAIL_NON_BLANK)
      @Size(max = 50, message = PLAYERMAIL_NON_VALID_SIZE)
      @Email(regexp = MAIL_REGEXP, message = PLAYERMAIL_NON_VALID_PATTERN)
      protected String playerMail;
}
