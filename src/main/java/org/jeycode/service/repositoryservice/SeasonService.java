package org.jeycode.service.repositoryservice;

import org.jeycode.service.genericservice.SendMailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
            var allPlayerMail = playerService.allPlayerMail();
            if (allPlayerMail.length != 0)
            {
                  sendMailService.sendSeasonRestartInfoMail(allPlayerMail,playerService.getPlayerWinnerNick());
                  log.info("Éxisten usuarios a los que avisar.");
            }
            log.info("Aún no existen usuarios a los que avisar.");
            return ALL;
      }

}
