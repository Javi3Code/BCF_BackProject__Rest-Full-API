package org.jeycode.service.repositoryservice;

import java.util.Optional;

import org.jeycode.dtos.concretematchdto.ConcreteMatchDtoToCreate;
import org.jeycode.execptionsmanaged.GenericBackendException;
import org.jeycode.execptionsmanaged.RequestParamException;
import org.jeycode.mappers.ConcreteMatchMapper;
import org.jeycode.models.ConcreteMatch;
import org.jeycode.repositories.ConcreteMatchRepository;
import org.jeycode.service.genericservice.SendMailService;
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
public class ConcreteMatchService implements RestServiceUtils
{

      private final ConcreteMatchRepository concreteMatchRepository;
      private final TeamService teamService;
      private final PlayerService playerService;
      private final SendMailService sendMailService;
      private final ConcreteMatchMapper concreteMatchMapper;

      public ResponseEntity<?> insertOneConcreteMatch(ConcreteMatchDtoToCreate concreteMatchDto)
      {
            try
            {
                  if (concreteMatchRepository.findByResultOfConcreteMatchIsNull()
                                             .isPresent())
                  {
                        throw new RequestParamException(INSERT_CONCRETEMATCH_THERE_IS_AN_OPEN_MATCH);
                  }
                  return tryToInsertNewConcreteMatch(concreteMatchDto);
            }
            catch (GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exMsg);
            }
            catch (Exception ex)
            {
                  log.error(INSERT_CONCRETEMATCH_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,INSERT_CONCRETEMATCH_SERV_UNKNOWN_ERROR);
            }
      }

      public ResponseEntity<?> getOpenMatch()
      {
            try
            {
                  var openConcreteMatch = concreteMatchRepository.findByResultOfConcreteMatchIsNull()
                                                                 .orElseThrow(()-> new RequestParamException(GET_OPEN_CONCRETEMATCH_NOT_EXISTS));
                  log.info("Se ha obtenido el partido abierto");
                  return ResponseEntity.ok(concreteMatchMapper.toCompleteConcreteMatchDto(openConcreteMatch));
            }
            catch (GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exMsg);
            }
            catch (Exception ex)
            {
                  log.error(GET_OPEN_CONCRETEMATCH_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,GET_OPEN_CONCRETEMATCH_SERV_UNKNOWN_ERROR);
            }
      }

      public ResponseEntity<?> deleteConcreteMatch(boolean all)
      {
            try
            {
                  if (all)
                  {
                        concreteMatchRepository.deleteAll();
                        log.info("Se han borrado todos los partidos de la temporada.");
                        return ResponseEntity.ok()
                                             .build();
                  }
                  if (!(concreteMatchRepository.deleteByResultOfConcreteMatchIsNull() > 0))
                  {
                        throw new RequestParamException(DELETE_OPEN_CONCRETEMATCH_NOT_EXISTS);
                  }

                  log.info("Se ha borrado el partido abierto");
                  return ResponseEntity.ok()
                                       .build();
            }
            catch (GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exMsg);
            }
            catch (Exception ex)
            {
                  log.error(DELETE_CONCRETEMATCH_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,DELETE_CONCRETEMATCH_SERV_UNKNOWN_ERROR);
            }
      }

      public ConcreteMatch save(ConcreteMatch openConcreteMatch)
      {
            return concreteMatchRepository.save(openConcreteMatch);
      }

      public Optional<ConcreteMatch> findByResultOfConcreteMatchIsNull()
      {
            return concreteMatchRepository.findByResultOfConcreteMatchIsNull();
      }

      /*
       * =============================private methods=============================
       */

      private ResponseEntity<?> tryToInsertNewConcreteMatch(ConcreteMatchDtoToCreate concreteMatchDto)
      {
            var localTeam = concreteMatchDto.getLocalTeam();
            var visitorTeam = concreteMatchDto.getVisitorTeam();
            var thatGameWasPlayed = concreteMatchRepository.existsByLocalTeamAndVisitorTeam(localTeam,visitorTeam);
            if (thatGameWasPlayed)
            {
                  throw new RequestParamException(INSERT_CONCRETEMATCH_WAS_PLAYED);
            }
            if (localTeam.equals(visitorTeam))
            {
                  throw new RequestParamException(INSERT_CONCRETEMATCH_TEAMS_ARE_EQUALS);
            }
            var localEqualsBCF = localTeam.equals(BURGOS_CF);
            var visitorEqualsBCF = visitorTeam.equals(BURGOS_CF);
            if (!localEqualsBCF && !visitorEqualsBCF)
            {
                  throw new RequestParamException(INSERT_CONCRETEMATCH_TEAMS_NO_BCF);
            }
            var teamToTest = localEqualsBCF ? visitorTeam : localTeam;
            var areValidTeams = teamService.existsById(teamToTest);

            if (!areValidTeams)
            {
                  throw new RequestParamException(teamToTest + INSERT_CONCRETEMATCH_TEAMS_NO_EXISTS);
            }
            var concreteMatchSaved = concreteMatchRepository.save(concreteMatchMapper.toCreateNewConcreteMatch(concreteMatchDto));
            log.info("Se creo el partido correctamente");
            sendMailService.sendMailInfoStartNewFootballDay(localTeam + VERSUS + visitorTeam,playerService.allPlayerMail());
            return ResponseEntity.ok(concreteMatchMapper.toSimpleConcreteMatchDto(concreteMatchSaved));
      }

}
