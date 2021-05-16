package org.jeycode.dtos.playerfootballmatchdto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@ApiModel(
      description = "Es el DTO que se retorna en las operaciones GET y POST en el controlador de apuestas. También se utiliza en los DTOs de los partidos.",
      value = "Basic - Apuesta DTO")
@Builder(builderMethodName = "of", buildMethodName = "get")
@Data
public class JourneyPlayerFootballMatchDto
{

      protected String playerNick,playerFootBallMatchResult;
      protected int matchPoints;
}
