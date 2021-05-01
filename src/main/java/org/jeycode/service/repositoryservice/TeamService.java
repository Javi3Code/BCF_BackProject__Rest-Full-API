package org.jeycode.service.repositoryservice;

import java.util.stream.Collectors;

import org.jeycode.dtos.teamsdto.TeamDto;
import org.jeycode.execptionsmanaged.GenericBackendException;
import org.jeycode.execptionsmanaged.RequestParamException;
import org.jeycode.execptionsmanaged.UniqueConstraintException;
import org.jeycode.mappers.TeamsMapper;
import org.jeycode.repositories.TeamRepository;
import org.jeycode.utilities.RestServiceUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TeamService implements RestServiceUtils
{

      private final TeamRepository teamRepository;
      private final TeamsMapper teamMapper;

      public ResponseEntity<?> insertOneTeam(TeamDto teamDto)
      {
            try
            {
                  var teamPresent = teamRepository.existsById(teamDto.getTeamName());
                  if (teamPresent)
                  {
                        throw new UniqueConstraintException(INSERT_TEAM_PK_VIOLATED);
                  }

                  var teamSaved = teamRepository.save(teamMapper.toTeam(teamDto));
                  log.info("Se añadió el equipo correctamente");
                  return ResponseEntity.ok(teamSaved);

            }
            catch (GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(ex.getStatus(),exMsg);
            }
            catch (Exception ex)
            {
                  log.error(INSERT_TEAM_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,INSERT_TEAM_SERV_UNKNOWN_ERROR);
            }
      }

      public ResponseEntity<?> getAllTeam()
      {
            try
            {
                  var lstOfTeams = teamRepository.findAll()
                                                 .stream()
                                                 .map(teamMapper::toTeamDto)
                                                 .collect(Collectors.toList());

                  log.info("Se han obtenido todos los equipos de la database.");
                  return ResponseEntity.ok(lstOfTeams);

            }
            catch (Exception ex)
            {
                  log.error(GET_TEAMS_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,GET_TEAMS_SERV_UNKNOWN_ERROR);
            }
      }

      public ResponseEntity<?> tryToDeleteOneOrMoreTeams(boolean all,TeamDto teamDto)
      {
            try
            {
                  return deleteOneOrMoreTeams(all,teamDto);
            }
            catch (GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exMsg);
            }
            catch (Exception ex)
            {
                  var errorMsg = all ? DELETE_TEAMS_SERV_UNKNOWN_ERROR : DELETE_TEAM_SERV_UNKNOWN_ERROR;
                  log.error(errorMsg,ex);
                  throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,errorMsg);
            }
      }

      public boolean existsById(String teamToTest)
      {
            return teamRepository.existsById(teamToTest);
      }

      /*
       * =====================private methods=====================
       */

      private ResponseEntity<?> deleteOneOrMoreTeams(boolean all,TeamDto teamDto)
      {
            if (all && teamDto != null)
            {
                  throw new RequestParamException(DELETE_TEAMS_REQUEST_PARAM_CONFLICT);
            }
            if (all)
            {
                  teamRepository.deleteByTeamNameNot(BURGOS_CF);
                  log.info("Se borraron todos los equipos correctamente.");
                  return ResponseEntity.ok(true);
            }
            if (teamDto == null)
            {
                  throw new RequestParamException(DELETE_TEAM_REQUEST_PARAM_NULL);
            }
            if (teamDto.getTeamName()
                       .equals(BURGOS_CF))
            {
                  throw new RequestParamException(DELETE_TEAM_REQUEST_PARAM_NOT_EQ_BCF);
            }
            var team = teamMapper.toTeam(teamDto);
            if (!teamRepository.existsById(teamDto.getTeamName()))
            {
                  throw new RequestParamException(DELETE_TEAM_REQUEST_PARAM_NOT_EXISTS);
            }
            teamRepository.delete(team);
            log.info("Se borró el equipo correctamente.");
            return ResponseEntity.ok(true);
      }

}
