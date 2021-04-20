package org.jeycode.dtos.concretematchdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jeycode.utilities.ApplicationExceptionUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@NoArgsConstructor
public class ConcreteMatchDtoToCreate implements ApplicationExceptionUtils
{

      @NotNull(message = TEAMNAME_NON_VALID)
      @NotBlank(message = TEAMNAME_NON_VALID)
      @Size(min = 3, max = 60, message = TEAMNAME_NON_VALID)
      protected String localTeam,visitorTeam;
}
