package org.jeycode.service.repositoryservice.utils;

import org.jeycode.dtos.playerdto.PlayerDtoToCreate;
import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface PlayerSalvable
{

      ResponseEntity<?> saveOrUpdate(PlayerDtoToCreate playerDto);

}
