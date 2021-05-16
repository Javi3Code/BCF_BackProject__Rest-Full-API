package org.jeycode.dtos.playerdto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ApiModel(
      description = "Es el DTO que se retorna en las operaciones GET en el controlador de jugadores y también en el controlador de apuestas.",
      value = "Basic2 - Jugador DTO", subTypes = {CompletePlayerDto.class}, parent = AbstractPlayerDto.class)
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class PlayerDtoWithId extends AbstractPlayerDto
{

      protected long playerId;
}
