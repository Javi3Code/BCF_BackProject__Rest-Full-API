package org.jeycode.utilities;

import org.jeycode.dtos.playerdto.PlayerDtoToCreate;
import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface PlayerSalvable
{

      ResponseEntity<?> saveOrUpdate(PlayerDtoToCreate playerDto);

}
