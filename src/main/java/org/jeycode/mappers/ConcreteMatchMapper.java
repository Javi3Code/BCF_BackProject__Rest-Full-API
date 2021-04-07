package org.jeycode.mappers;

import org.jeycode.dtos.concretematchdto.AbstractConcreteMatchDto;
import org.jeycode.dtos.concretematchdto.CompleteConcreteMatchDto;
import org.jeycode.dtos.concretematchdto.ConcreteMatchDtoToCreate;
import org.jeycode.dtos.concretematchdto.ConcreteMatchDtoToUpdate;
import org.jeycode.dtos.concretematchdto.ConcreteMatchDtoWithId;
import org.jeycode.models.ConcreteMatch;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {PlayerFootballMatchMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ConcreteMatchMapper
{

      ConcreteMatch toCreateNewConcreteMatch(ConcreteMatchDtoToCreate concreteMatchDto);

      ConcreteMatch toUpdateConcreteMatch(ConcreteMatchDtoToUpdate concreteMatchDto);

      /*
       * 
       */

      AbstractConcreteMatchDto toSimpleConcreteMatchDto(ConcreteMatch concreteMatch);

//      ConcreteMatch concreteMatchDtoWithIdToConcreteMatch(ConcreteMatchDtoWithId concreteMatchDto);

      ConcreteMatchDtoWithId toConcreteMatchDtoWithId(ConcreteMatch concreteMatch);

//      ConcreteMatch completeConcreteMatchDtoToConcreteMatch(CompleteConcreteMatchDto concreteMatchDto);

      CompleteConcreteMatchDto toCompleteConcreteMatchDto(ConcreteMatch concreteMatch);

      ConcreteMatchDtoToUpdate toConcreteMatchDtoToUpdate(ConcreteMatch openConcreteMatch);
}
