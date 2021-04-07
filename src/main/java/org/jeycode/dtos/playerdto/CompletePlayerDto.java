package org.jeycode.dtos.playerdto;

import java.util.List;

import org.jeycode.dtos.playerfootballmatchdto.StandardPlayerFootballMatchDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class CompletePlayerDto extends PlayerDtoWithId
{

      protected List<StandardPlayerFootballMatchDto> lstOfPlayerFootBallMatch;

}
