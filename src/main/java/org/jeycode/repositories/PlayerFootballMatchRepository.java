package org.jeycode.repositories;

import java.util.List;

import org.jeycode.models.ConcreteMatch;
import org.jeycode.models.PlayerFootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerFootballMatchRepository extends JpaRepository<PlayerFootballMatch,Long>
{

      List<PlayerFootballMatch> findAllByConcreteMatchAndPlayerFootBallMatchResultIsNotNull(ConcreteMatch openConcreteMatch);

      List<PlayerFootballMatch> findAllByConcreteMatch(ConcreteMatch openConcreteMatch);
      
      


}
