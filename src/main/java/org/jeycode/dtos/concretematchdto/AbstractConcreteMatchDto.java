package org.jeycode.dtos.concretematchdto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "Es el DTO que se retorna en la operacion POST en el controlador de partidos.", value = "Basic - Partido DTO",
      subTypes = {ConcreteMatchDtoWithId.class})
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
public class AbstractConcreteMatchDto
{

      protected String localTeam,visitorTeam;
}
