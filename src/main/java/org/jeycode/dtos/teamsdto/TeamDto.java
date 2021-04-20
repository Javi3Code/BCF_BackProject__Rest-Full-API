package org.jeycode.dtos.teamsdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jeycode.utilities.ApplicationExceptionUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamDto implements ApplicationExceptionUtils
{
      @NotNull(message = TEAMNAME_NON_VALID)
      @NotBlank(message = TEAMNAME_NON_VALID)
      @Size(min = 3, max = 60, message = TEAMNAME_NON_VALID)
      String teamName;
}
