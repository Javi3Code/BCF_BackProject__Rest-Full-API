package org.jeycode.dtos.playerdto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
public class AbstractPlayerDto
{
      protected String playerNick, playerMail;
      protected int playerTotalPoints;
}
