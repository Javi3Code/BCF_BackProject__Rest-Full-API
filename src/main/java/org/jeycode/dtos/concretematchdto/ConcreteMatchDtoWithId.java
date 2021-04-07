package org.jeycode.dtos.concretematchdto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConcreteMatchDtoWithId extends AbstractConcreteMatchDto
{

      protected long concreteMatchId;
}
