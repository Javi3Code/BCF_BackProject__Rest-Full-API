package org.jeycode.dtos.teamsdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jeycode.utilities.ApplicationExceptionUtils;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Utilizado tanto para entrada como salida en el controlador de equipos.", value = "Equipo DTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamDto implements ApplicationExceptionUtils
{

      @NotNull(message = TEAMNAME_NON_NULL)
      @NotBlank(message = TEAMNAME_NON_BLANK)
      @Size(min = 3, max = 60, message = TEAMNAME_NON_VALID_SIZE)
      String teamName;
}
