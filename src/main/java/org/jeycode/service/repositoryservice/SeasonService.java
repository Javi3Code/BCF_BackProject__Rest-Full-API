package org.jeycode.service.repositoryservice;

import org.jeycode.service.genericservice.SendMailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SeasonService
{

      private final TeamService teamService;
      private final PlayerService playerService;
      private final ConcreteMatchService concreteMatchService;
      private final SendMailService sendMailService;
      private static final boolean ALL = true;

      public boolean tryToResetSeason()
      {
            teamService.tryToDeleteOneOrMoreTeams(ALL,null);
            playerService.resetOfPlayerStateToInitial();
            concreteMatchService.deleteConcreteMatch(ALL);
            sendMailService.sendSeasonRestartInfoMail(playerService.allPlayerMail(),playerService.getPlayerWinnerNick());
            return ALL;
      }

}
