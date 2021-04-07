package org.jeycode.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {@Index(columnList = "player_nick")})
public class Player
{

      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      @Column(name = "player_id")
      private long playerId;

      @Column(name = "player_nick", length = 60, nullable = false, unique = true)
      private String playerNick;

      @Column(name = "player_mail", length = 60, nullable = false)
      private String playerMail;

      @Column(name = "player_totalpoints", nullable = false)
      private int playerTotalPoints;

      @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "player", fetch = FetchType.LAZY)
      private List<PlayerFootballMatch> lstOfPlayerFootBallMatch;
}
