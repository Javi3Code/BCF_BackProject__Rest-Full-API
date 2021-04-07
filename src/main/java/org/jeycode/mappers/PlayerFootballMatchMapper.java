package org.jeycode.mappers;

import org.jeycode.dtos.playerfootballmatchdto.JourneyPlayerFootballMatchDto;
import org.jeycode.dtos.playerfootballmatchdto.PlayerFootballMatchDtoCompleteToUpdate;
import org.jeycode.dtos.playerfootballmatchdto.PlayerFootballMatchDtoToUpdate;
import org.jeycode.dtos.playerfootballmatchdto.PlayerFootballmatchDtoToCreate;
import org.jeycode.dtos.playerfootballmatchdto.StandardPlayerFootballMatchDto;
import org.jeycode.models.PlayerFootballMatch;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PlayerMapper.class}, injectionStrategy = InjectionStrategy.FIELD)
public interface PlayerFootballMatchMapper // Necesidad de hacer la injection en field -> dependency cycle err
{

      PlayerFootballMatch toCreateNewPlayerFootballmatch(PlayerFootballmatchDtoToCreate playerFootballMatchDto);

      PlayerFootballMatch toUpdateNewPlayerFootballmatch(PlayerFootballMatchDtoToUpdate playerFootballMatchDto);

      PlayerFootballMatch toCompletePlayerFootballMatch(PlayerFootballMatchDtoCompleteToUpdate playerFootballMatchDto);

      /*
       */

      @Mapping(source = "player.playerNick", target = "playerNick")
      JourneyPlayerFootballMatchDto toJourneyPlayerFootballMatchDto(PlayerFootballMatch playerFootballMatch);

      StandardPlayerFootballMatchDto toCompletePlayerFootballMatchDto(PlayerFootballMatch playerFootBallMatch);

}
