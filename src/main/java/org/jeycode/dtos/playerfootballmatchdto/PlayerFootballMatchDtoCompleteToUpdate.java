package org.jeycode.dtos.playerfootballmatchdto;

import javax.validation.constraints.NotNull;

import org.jeycode.dtos.concretematchdto.ConcreteMatchDtoToUpdate;
import org.jeycode.dtos.playerdto.PlayerDtoToUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlayerFootballMatchDtoCompleteToUpdate extends PlayerFootballMatchDtoToUpdate
{

      @NotNull(message = PFMPLAYER_CANT_BE_NULL)
      private PlayerDtoToUpdate player;

      @NotNull(message = PFMCONCMATCH_CANT_BE_NULL)
      private ConcreteMatchDtoToUpdate concreteMatch;
}
