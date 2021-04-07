package org.jeycode.dtos.playerfootballmatchdto;

import lombok.Builder;
import lombok.Data;

@Builder(builderMethodName = "of", buildMethodName = "get")
@Data
public class StandardPlayerFootballMatchDto
{

      private long playerFootBallMatchId;
      private String playerFootBallMatchResult;
      private short winnerSign,burgosCFGoals;
      private short matchPoints;
}
