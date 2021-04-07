package org.jeycode.mappers;

import org.jeycode.dtos.rulesdto.RulesDto;
import org.jeycode.models.Rules;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RulesMapper  
{
      RulesDto toRulesDto(Rules rules); 
      
      Rules toRules(RulesDto rulesDto);  
}
