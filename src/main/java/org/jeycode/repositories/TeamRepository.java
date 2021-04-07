package org.jeycode.repositories;

import org.jeycode.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,String>
{

      void deleteByTeamNameNot(String teamName);

}
