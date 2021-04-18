package org.jeycode.service.repositoryservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeycode.dtos.playerdto.PlayerDtoToCreate;
import org.jeycode.dtos.playerdto.PlayerDtoToUpdate;
import org.jeycode.execptionsmanaged.GenericBackendException;
import org.jeycode.execptionsmanaged.RequestParamException;
import org.jeycode.execptionsmanaged.UniqueConstraintException;
import org.jeycode.mappers.PlayerMapper;
import org.jeycode.models.Player;
import org.jeycode.repositories.PlayerRepository;
import org.jeycode.service.genericservice.SendMailService;
import org.jeycode.service.genericservice.utils.PlayerSalvable;
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
public class PlayerService implements RestServiceUtils
{

      private final PlayerRepository playerRepository;
      private final PlayerMapper playerMapper;
      private final SendMailService sendMailService;

      private final PlayerDto[] playerDtos = PlayerDto.values();

      public ResponseEntity<?> insertOnePlayer(PlayerDtoToCreate playerDto)
      {
            PlayerSalvable playerSalvable = this::tryToSave;

            return tryToSaveOrUpdate(playerSalvable,playerDto);

      }

      public ResponseEntity<?> updateOnePlayer(PlayerDtoToUpdate playerDto)
      {
            PlayerSalvable playerSalvable = this::tryToUpdate;

            return tryToSaveOrUpdate(playerSalvable,playerDto);
      }

      public boolean resetOfPlayerStateToInitial()
      {
            try
            {
                  playerRepository.resetToInitialState();
                  log.info("Se han reseteado los puntos de todos los jugadores");
                  return true;
            }
            catch (Exception ex)
            {
                  log.error(UPDATE_PLAYERS_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,UPDATE_PLAYERS_SERV_UNKNOWN_ERROR);
            }
      }

      public ResponseEntity<?> getAllPlayers(int level)
      {
            try
            {
                  var playerMapperToUse = playerDtos[level];
                  var lstOfPlayers = playerRepository.findAll()
                                                     .stream()
                                                     .map(pl-> playerMapperToUse.map(playerMapper,pl))
                                                     .collect(Collectors.toList());

                  log.info("Se han obtenido todos los jugadores de la database.");
                  return ResponseEntity.ok(lstOfPlayers);

            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                  log.error(FDSERVICE_PARAM_TYPE_OF_DATA_NOT_VALID,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,GET_PLFM_SERV_UNKNOWN_ERROR);
            }
            catch (Exception ex)
            {
                  log.error(GET_PLAYERS_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,GET_PLFM_SERV_UNKNOWN_ERROR);
            }
      }

      public ResponseEntity<?> deleteOnePlayer(PlayerDtoToUpdate playerDto)
      {
            try
            {
                  var playerId = playerDto.getPlayerId();
                  var playerToDeleteExists = playerRepository.existsById(playerId);
                  if (!playerToDeleteExists)
                  {
                        throw new RequestParamException(DELETE_PLAYER_REQUEST_NO_EXISTS);
                  }
                  playerRepository.deleteById(playerId);
                  log.info("Se borró el jugador correctamente");
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
                  var errorMsg = DELETE_TEAMS_SERV_UNKNOWN_ERROR;
                  log.error(errorMsg,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,errorMsg);
            }
      }

      public Optional<Player> findByPlayerNick(String playerNick)
      {
            return playerRepository.findByPlayerNick(playerNick);
      }

      public List<Player> findAll()
      {
            return playerRepository.findAll();
      }

      public List<Player> findAllByPlayerIdNotIn(List<Long> lstOfPlayersWithPlfmOpen)
      {
            return playerRepository.findAllByPlayerIdNotIn(lstOfPlayersWithPlfmOpen);
      }

      public String[] allPlayerMail()
      {
            return playerRepository.allPlayerMail();
      }

      public String getPlayerWinnerNick()
      {
            return playerRepository.findFirstByOrderByPlayerTotalPointsDesc()
                                   .getPlayerNick();
      }

      /*
       * =============================private methods=============================
       */

      private ResponseEntity<?> tryToSaveOrUpdate(PlayerSalvable playerSalvable,PlayerDtoToCreate playerDto)
      {
            try
            {
                  return playerSalvable.saveOrUpdate(playerDto);

            }
            catch (GenericBackendException ex)
            {
                  var exMsg = ex.getMessage();
                  log.error(exMsg,ex);
                  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exMsg);
            }
            catch (Exception ex)
            {
                  log.error(INSERT_OR_UPDATE_PLAYER_SERV_UNKNOWN_ERROR,ex);
                  throw new ResponseStatusException(HttpStatus.CONFLICT,INSERT_OR_UPDATE_PLAYER_SERV_UNKNOWN_ERROR);
            }
      }

      private ResponseEntity<?> tryToSave(PlayerDtoToCreate plDto)
      {
            var playerWithThisNickIsPresent = playerRepository.existsByPlayerNick(plDto.getPlayerNick());
            if (playerWithThisNickIsPresent)
            {
                  throw new UniqueConstraintException(INSERT_PLAYER_SERV_NICK_REPEATED);
            }

            var playerSaved = playerRepository.save(playerMapper.toCreateNewPlayer(plDto));
            log.info("Se añadió el jugador correctamente");
            sendMailService.sendRegistrationMail(playerSaved.getPlayerMail(),playerSaved.getPlayerNick());
            return ResponseEntity.ok(playerMapper.toSimplePlayerDto(playerSaved));
      }

      private ResponseEntity<?> tryToUpdate(PlayerDtoToCreate plDto)
      {

            var playerToUpdte = (PlayerDtoToUpdate)plDto;
            var idOfPlayerToUpdate = playerToUpdte.getPlayerId();
            var player = playerRepository.findById(idOfPlayerToUpdate)
                                         .orElseThrow(()-> new UniqueConstraintException(UPDATE_PLAYER_SERV_NOT_EXISTS));

            var playerWithThisNickIsPresent = playerRepository.existsByPlayerNickAndPlayerIdNot(plDto.getPlayerNick(),idOfPlayerToUpdate);
            if (playerWithThisNickIsPresent)
            {
                  throw new UniqueConstraintException(UPDATE_PLAYER_SERV_NICK_REPEATED);
            }

            player.setPlayerNick(playerToUpdte.getPlayerNick());
            player.setPlayerMail(playerToUpdte.getPlayerMail());

            var playerSaved = playerRepository.save(player);
            log.info("Se actualizó el jugador correctamente");
            return ResponseEntity.ok(playerMapper.toSimplePlayerDtoWithId(playerSaved));
      }

}
