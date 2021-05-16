package org.jeycode.dtos.playerdto;

import java.util.List;

import org.jeycode.dtos.playerfootballmatchdto.StandardPlayerFootballMatchDto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ApiModel(
      description = "Es el DTO que se retorna en las operaciones GET en el controlador de jugadores y también en el controlador de apuestas.",
      value = "Complete - Jugador DTO", parent = PlayerDtoWithId.class)
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class CompletePlayerDto extends PlayerDtoWithId
{

      protected List<StandardPlayerFootballMatchDto> lstOfPlayerFootBallMatch;

}
