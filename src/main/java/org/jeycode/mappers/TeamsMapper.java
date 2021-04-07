package org.jeycode.mappers;

import org.jeycode.dtos.teamsdto.TeamDto;
import org.jeycode.models.Team;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TeamsMapper  
{

      TeamDto toTeamDto(Team team);

      Team toTeam(TeamDto teamDto);
}
