package org.jeycode.dtos.playerfootballmatchdto;

import lombok.Builder;
import lombok.Data;

@Builder(builderMethodName = "of", buildMethodName = "get")
@Data
public class JourneyPlayerFootballMatchDto
{
      protected String playerNick, playerFootBallMatchResult;
      protected int matchPoints;
}
