package org.jeycode.service.genericservice;

import java.util.List;

import org.jeycode.models.ConcreteMatch;
import org.jeycode.models.PlayerFootballMatch;
import org.jeycode.service.genericservice.utils.RestServiceUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PointCalculationService implements RestServiceUtils
{

      public void calculatePoints(List<PlayerFootballMatch> lstOfPlayerFootBallMatch,ConcreteMatch openConcreteMatch)
      {
            var result = openConcreteMatch.getResultOfConcreteMatch();

            lstOfPlayerFootBallMatch.forEach(plfm->
                  {
                        var plfmResult = plfm.getPlayerFootBallMatchResult();
                        var matchPoints = plfmResult != null ? getPointsToSet(plfm,openConcreteMatch,result,plfmResult) : 0;
                        plfm.setMatchPoints(matchPoints);
                        var player = plfm.getPlayer();
                        var totalPoints = player.getPlayerTotalPoints();
                        player.setPlayerTotalPoints(totalPoints + matchPoints);

                  });
            log.info("Se calcularon los puntos de cada jugador");

      }

      private short getPointsToSet(PlayerFootballMatch plfm,ConcreteMatch openConcreteMatch,String result,String plfmResult)
      {
            if (plfmResult.equals(result))
            {
                  return DFL_RESULT_POINTS;
            }
            var goals = result.split(RESULT_DELIMITER);
            var localGoals = Short.parseShort(goals[0]);
            var visitorGoals = Short.parseShort(goals[1]);
            short bcfGoals = getBcfGoals(openConcreteMatch,localGoals,visitorGoals);
            if (plfm.getBurgosCFGoals() == bcfGoals)
            {
                  return DFL_GOALS_POINTS;
            }
            var sign = getSign(localGoals,visitorGoals);
            return plfm.getWinnerSign() == sign ? DFL_SIGN_POINTS : 0;
      }
}
