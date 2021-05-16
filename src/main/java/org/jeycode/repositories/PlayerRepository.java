package org.jeycode.repositories;

import java.util.List;
import java.util.Optional;

import org.jeycode.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long>
{

      boolean existsByPlayerNick(String playerNick);

      Optional<Player> findByPlayerNick(String playerNick);

      boolean existsByPlayerNickAndPlayerIdNot(String playerNick,long idOfPlayerToUpdate);

      @Modifying(clearAutomatically = true) @Query(value = "UPDATE Player p SET p.player_TotalPoints = 0", nativeQuery = true)
      void resetToInitialState();

      @Query(value = "Select player_mail from player", nativeQuery = true)
      String[] allPlayerMail();

      Player findFirstByOrderByPlayerTotalPointsDesc();

      List<Player> findAllByPlayerIdNotIn(List<Long> ids);

//      @Query(value = "Select player_nick from player where player_totalpoints = ?", nativeQuery = true)
      List<Player> findByPlayerTotalPoints(@Param("playerTotalPoints")int playerTotalPoints);

}
