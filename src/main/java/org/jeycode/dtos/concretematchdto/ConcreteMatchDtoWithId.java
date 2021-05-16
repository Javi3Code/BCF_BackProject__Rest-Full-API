package org.jeycode.dtos.concretematchdto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ApiModel(value = "Basic2 - Partido DTO", subTypes = {CompleteConcreteMatchDto.class}, parent = AbstractConcreteMatchDto.class)
@SuperBuilder(builderMethodName = "of", buildMethodName = "get")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConcreteMatchDtoWithId extends AbstractConcreteMatchDto
{

      protected long concreteMatchId;
}
