package org.jeycode.dtos.playerfootballmatchdto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ApiModel(subTypes = {PlayerFootballMatchDtoCompleteToUpdate.class}, parent = PlayerFootballmatchDtoToCreate.class)
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlayerFootballMatchDtoToUpdate extends PlayerFootballmatchDtoToCreate
{

      @NotNull(message = NON_VALID_ID_NULL)
      @Positive(message = NON_VALID_ID_VALUE)
      private long playerFootBallMatchId;

      @NotNull(message = PFMSIGN_CANT_BE_NULL)
      @Positive(message = PFMSIGN_NON_VALID)
      @Max(value = 2, message = PFMSIGN_NON_VALID)
      private short winnerSign;

      @NotNull(message = PFMBCFGOALS_CANT_BE_NULL)
      @Positive(message = PFMBCFGOALS_NON_VALID)
      @Max(value = 20, message = PFMBCFGOALS_NON_VALID)
      private short burgosCFGoals;

      @NotNull(message = PFMPOINTS_CANT_BE_NULL)
      @Positive(message = PFMPOINTS_NON_VALID)
      private short matchPoints;
}
