package org.jeycode.dtos.concretematchdto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
public class AbstractConcreteMatchDto
{

      protected String localTeam,visitorTeam;
}
