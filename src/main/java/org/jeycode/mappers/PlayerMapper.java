package org.jeycode.mappers;

import org.jeycode.dtos.playerdto.AbstractPlayerDto;
import org.jeycode.dtos.playerdto.CompletePlayerDto;
import org.jeycode.dtos.playerdto.PlayerDtoToCreate;
import org.jeycode.dtos.playerdto.PlayerDtoToUpdate;
import org.jeycode.dtos.playerdto.PlayerDtoWithId;
import org.jeycode.models.Player;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {PlayerFootballMatchMapper.class}, injectionStrategy = InjectionStrategy.FIELD, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PlayerMapper // Necesidad de hacer la injection en field -> dependency cycle err
{

      Player toCreateNewPlayer(PlayerDtoToCreate playerDto);

      Player toUpdatePlayer(PlayerDtoToUpdate playerDto);

      /*
       * 
       */

      AbstractPlayerDto toSimplePlayerDto(Player player);

//      Player playerDtoWithIdtoPlayer(PlayerDtoWithId playerDto);

      PlayerDtoWithId toSimplePlayerDtoWithId(Player player);

//      Player completePlayerDtoPlayer(CompletePlayerDto playerDto);

      CompletePlayerDto toCompletePlayerDto(Player player);
}
