package org.jeycode.dtos.playerdto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class PlayerDtoToUpdate extends PlayerDtoToCreate
{

      @NotNull(message = NON_VALID_ID)
      @Positive(message = NON_VALID_ID)
      protected long playerId;
}
