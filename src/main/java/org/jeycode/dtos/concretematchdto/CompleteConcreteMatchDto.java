package org.jeycode.dtos.concretematchdto;

import java.util.List;

import org.jeycode.dtos.playerfootballmatchdto.JourneyPlayerFootballMatchDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get") 
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CompleteConcreteMatchDto extends ConcreteMatchDtoWithId
{
      private String resultOfConcreteMatch;
      private List<JourneyPlayerFootballMatchDto> lstOfPlayerFootBallMatch;
}
