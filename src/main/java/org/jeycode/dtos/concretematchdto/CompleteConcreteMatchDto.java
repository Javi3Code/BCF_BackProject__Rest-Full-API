package org.jeycode.dtos.concretematchdto;

import java.util.List;

import org.jeycode.dtos.playerfootballmatchdto.JourneyPlayerFootballMatchDto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "Principalmente utilizado en el controlador de partidos para obtener el partido abierto.",
      value = "Complete - Partido DTO", parent = CompleteConcreteMatchDto.class)
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CompleteConcreteMatchDto extends ConcreteMatchDtoWithId
{

      private String resultOfConcreteMatch;
      private List<JourneyPlayerFootballMatchDto> lstOfPlayerFootBallMatch;
}
