package org.jeycode.dtos.playerdto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "Es el DTO que se espera en la operación PUT en el controlador de jugadores.", value = "UP - Jugador DTO")
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class PlayerDtoToUpdate extends PlayerDtoToCreate
{

      @NotNull(message = NON_VALID_ID_NULL)
      @Positive(message = NON_VALID_ID_VALUE)
      protected long playerId;
}
