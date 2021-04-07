package org.jeycode.repositories;

import java.util.Optional;

import org.jeycode.models.ConcreteMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcreteMatchRepository extends JpaRepository<ConcreteMatch,Long>
{

      boolean existsByLocalTeamAndVisitorTeam(@Param("localTeam") String localTeam,@Param("visitorTeam") String visitorTeam);

      Optional<ConcreteMatch> findByResultOfConcreteMatchIsNull();

      int deleteByResultOfConcreteMatchIsNull();

}
