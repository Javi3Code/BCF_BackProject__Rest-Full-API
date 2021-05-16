package org.jeycode.dtos.playerdto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "Es el DTO que se retorna en las operaciones GET y POST en el controlador de jugadores.",
      value = "Basic - Jugador DTO", subTypes = {PlayerDtoWithId.class})
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
public class AbstractPlayerDto
{

      protected String playerNick,playerMail;
      protected int playerTotalPoints;
}
