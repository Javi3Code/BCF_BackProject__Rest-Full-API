package org.jeycode.dtos.playerdto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class PlayerDtoWithId extends AbstractPlayerDto
{
      protected long playerId;
}
