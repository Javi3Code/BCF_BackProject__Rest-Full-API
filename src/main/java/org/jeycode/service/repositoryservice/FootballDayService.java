package org.jeycode.service.repositoryservice;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jeycode.dtos.concretematchdto.ConcreteMatchDtoToUpdate;
import org.jeycode.dtos.playerdto.PlayerDtoWithId;
import org.jeycode.dtos.playerfootballmatchdto.JourneyPlayerFootballMatchDto;
import org.jeycode.dtos.playerfootballmatchdto.PlayerFootballmatchDtoToCreate;
import org.jeycode.execptionsmanaged.GenericBackendException;
import org.jeycode.execptionsmanaged.RequestParamException;
import org.jeycode.mappers.ConcreteMatchMapper;
import org.jeycode.mappers.PlayerFootballMatchMapper;
import org.jeycode.mappers.PlayerMapper;
import org.jeycode.models.ConcreteMatch;
import org.jeycode.models.Player;
import org.jeycode.models.PlayerFootballMatch;
import org.jeycode.repositories.PlayerFootballMatchRepository;
import org.jeycode.service.components.PointCalculationComponent;
import org.jeycode.service.genericservice.SendMailService;
import org.jeycode.service.genericservice.utils.RestServiceUtils;
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
public class FootballDayService implements RestServiceUtils
{

      private final PlayerFootballMatchRepository plfmRepository;
      private final PlayerService playerService;
      private final ConcreteMatchService concreteMatchService;
      private final PointCalculationComponent pointCalculationService;
      private final SendMailService sendMailService;
      private final PlayerFootballMatchMapper plfmMapper;
      private final PlayerMapper playerMapper;
      private final ConcreteMatchMapper concreteMatchMapper;
      private final FootballDayTypeOfData[] fdTypeOfData = FootballDayTypeOfData.values();

      public ResponseEntity<?> insertOnePlayerResult(PlayerFootballmatchDtoToCreate plfmDto)
      {
            try
            {
                  var plfm = tryToSaveResult(plfmDto);
                  log.info("Se ha añadido la porra correctamente");
                  return ResponseEntity.ok(plfmMapper.toJourneyPlayerFootballMatchDto(plfm));
            }
            catch (GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exMsg);
            }
            catch (Exception ex)
            {
                  log.error(INSERT_PLFM_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,INSERT_PLFM_SERV_UNKNOWN_ERROR);
            }
      }

      public ResponseEntity<?> getFootballDayData(int typeOfData)
      {
            try
            {
                  var openConcreteMatch = getOpenConcreteMatch(GET_PLFM_DATA_FORBIDDEN);
                  var dataLevel = fdTypeOfData[typeOfData];
                  var dataRequired = getData(openConcreteMatch,dataLevel);
                  log.info("Se han obtenido todos los datos requeridos.");
                  return dataRequired;

            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                  log.error(GET_PLAYERS_PARAM_LEVEL_NOT_VALID,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,GET_PLAYERS_PARAM_LEVEL_NOT_VALID);
            }
            catch (

            GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exMsg);
            }
            catch (Exception ex)
            {
                  log.error(GET_PLAYERS_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,GET_PLAYERS_SERV_UNKNOWN_ERROR);
            }
      }

      public ResponseEntity<?> endTheFootballDay(ConcreteMatchDtoToUpdate concreteMatch)
      {
            try
            {
                  var openConcreteMatch = getOpenConcreteMatch(GET_PLFM_DATA_FORBIDDEN);
                  openConcreteMatch.setResultOfConcreteMatch(concreteMatch.getResultOfConcreteMatch());
                  var openConcreteMatchMapped = concreteMatchMapper.toConcreteMatchDtoToUpdate(openConcreteMatch);
                  if (!openConcreteMatchMapped.equals(concreteMatch))
                  {
                        throw new RequestParamException(UPDATE_FOOTBALLDAY_CM_NOT_VALID);
                  }
                  var lstOfPlayerFootBallMatch = openConcreteMatch.getLstOfPlayerFootBallMatch();
                  pointCalculationService.calculatePoints(lstOfPlayerFootBallMatch,openConcreteMatch);
                  var concreteMatchUpdated = concreteMatchService.save(openConcreteMatch);
                  log.info("Se han finalizado el partido y actualizado todos los puntos");
                  sendMailService.sendEndTheFootballDayMail(lstOfPlayerFootBallMatch,playerService.allPlayerMail());
                  return ResponseEntity.ok(concreteMatchMapper.toCompleteConcreteMatchDto(concreteMatchUpdated));
            }
            catch (GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exMsg);
            }
            catch (Exception ex)
            {
                  log.error(UPDATE_FOOTBALLDAY_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,UPDATE_FOOTBALLDAY_SERV_UNKNOWN_ERROR);
            }
      }

      /*
       * =============================private methods=============================
       */
      private PlayerFootballMatch tryToSaveResult(PlayerFootballmatchDtoToCreate plfmDto)
      {
            var openConcreteMatch = getOpenConcreteMatch(INSERT_PLFM_FORBIDDEN);

            var playerNick = plfmDto.getPlayerNick();
            var player = playerService.findByPlayerNick(playerNick)
                                      .orElseThrow(()-> new RequestParamException(INSERT_PLFM_PLAYERNICK_NOT_EXISTS));

            Predicate<PlayerFootballMatch> thisPlayerHasResult = plfm-> plfm.getConcreteMatch()
                                                                            .equals(openConcreteMatch);
            var hasResult = player.getLstOfPlayerFootBallMatch()
                                  .stream()
                                  .anyMatch(thisPlayerHasResult);
            if (hasResult)
            {
                  throw new RequestParamException(playerNick + INSERT_PLFM_PLAYER_HAS_RESULT);
            }

            var plfm = buildPLFMatch(plfmDto,openConcreteMatch,player);

            plfmRepository.save(plfm);
            return plfm;
      }

      private PlayerFootballMatch buildPLFMatch(PlayerFootballmatchDtoToCreate plfmDto,ConcreteMatch openConcreteMatch,Player player)
      {
            var goals = plfmDto.getPlayerFootBallMatchResult()
                               .split(RESULT_DELIMITER);
            var localGoals = Short.parseShort(goals[0]);
            var visitorGoals = Short.parseShort(goals[1]);

            var plfm = plfmMapper.toCreateNewPlayerFootballmatch(plfmDto);
            plfm.setPlayer(player);
            plfm.setConcreteMatch(openConcreteMatch);
            var bcfGoals = getBcfGoals(openConcreteMatch,localGoals,visitorGoals);
            var sign = getSign(localGoals,visitorGoals);
            plfm.setBurgosCFGoals(bcfGoals);
            plfm.setWinnerSign(sign);
            return plfm;
      }

      private ConcreteMatch getOpenConcreteMatch(String errorMessage)
      {
            return concreteMatchService.findByResultOfConcreteMatchIsNull()
                                       .orElseThrow(()-> new RequestParamException(errorMessage));
      }

      private ResponseEntity<?> getData(ConcreteMatch openConcreteMatch,FootballDayTypeOfData typeOfData)
      {
            switch (typeOfData)
            {
                  case PLAYERS_WITHOUT_RESULT:
                        return ResponseEntity.ok(getDataPlayersWithoutResult(openConcreteMatch));
                  case PLAYERS_WITH_RESULT_DATA_TABLE:
                        return ResponseEntity.ok(getDataPlayersWithResult(openConcreteMatch));
                  default:
                        var mapOfdata = Map.of(FOOTBALLDAY_PWT_DATA,getDataPlayersWithoutResult(openConcreteMatch),FOOTBALLDAY_DT_DATA,
                                               getDataPlayersWithResult(openConcreteMatch));
                        return ResponseEntity.ok(mapOfdata);
            }
      }

      private List<PlayerDtoWithId> getDataPlayersWithoutResult(ConcreteMatch openConcreteMatch)
      {

            Function<Player,PlayerDtoWithId> mapToPlayerDto = pl-> playerMapper.toSimplePlayerDtoWithId(pl);
            var lstOfPlayersWithPlfmOpen = plfmRepository.findAllByConcreteMatch(openConcreteMatch)
                                                         .stream()
                                                         .map(plfm-> plfm.getPlayer()
                                                                         .getPlayerId())
                                                         .collect(Collectors.toList());

            List<Player> lstOfPlayersToFilter = lstOfPlayersWithPlfmOpen.isEmpty() ? playerService.findAll() :
                  playerService.findAllByPlayerIdNotIn(lstOfPlayersWithPlfmOpen);

            return lstOfPlayersToFilter.stream()
                                       .map(mapToPlayerDto)
                                       .collect(Collectors.toList());
      }

      private List<JourneyPlayerFootballMatchDto> getDataPlayersWithResult(ConcreteMatch concreteMatch)
      {
            return mapData(plfmRepository.findAllByConcreteMatchAndPlayerFootBallMatchResultIsNotNull(concreteMatch));
      }

//      private List<JourneyPlayerFootballMatchDto> getDataPlayers(ConcreteMatch concreteMatch)
//      {
//            return mapData(plfmRepository.findAllByConcreteMatch(concreteMatch));
//      }

      private List<JourneyPlayerFootballMatchDto> mapData(List<PlayerFootballMatch> data)
      {
            return data.stream()
                       .map(plfm-> plfmMapper.toJourneyPlayerFootballMatchDto(plfm))
                       .collect(Collectors.toList());
      }
}
