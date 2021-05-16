package org.jeycode.dtos.playerfootballmatchdto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@ApiModel(description = "Es el DTO que se utiliza en los DTOs de jugadores.", value = "Completo - Apuesta DTO")
@Builder(builderMethodName = "of", buildMethodName = "get")
@Data
public class StandardPlayerFootballMatchDto
{

      private long playerFootBallMatchId;
      private String playerFootBallMatchResult;
      private short winnerSign,burgosCFGoals;
      private short matchPoints;
}
