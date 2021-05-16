package org.jeycode.dtos.concretematchdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jeycode.utilities.ApplicationExceptionUtils;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "Es el DTO que se espera en la operacion POST en el controlador de partidos.", value = "CR - Partido DTO",
      subTypes = {ConcreteMatchDtoToUpdate.class})
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@NoArgsConstructor
public class ConcreteMatchDtoToCreate implements ApplicationExceptionUtils
{

      @NotNull(message = TEAMNAME_NON_NULL)
      @NotBlank(message = TEAMNAME_NON_BLANK)
      @Size(min = 3, max = 60, message = TEAMNAME_NON_VALID_SIZE)
      protected String localTeam,visitorTeam;
}
