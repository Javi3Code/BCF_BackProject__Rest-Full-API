package org.jeycode.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlayerFootballMatch
{

      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      @Column(name = "pfm_id")
      private long playerFootBallMatchId;

      @Column(name = "pfm_result", nullable = false, length = 5)
      private String playerFootBallMatchResult;

      @Column(name = "pfm_sign", nullable = false)
      private short winnerSign;

      @Column(name = "pfm_bcfgoals", nullable = false)
      private short burgosCFGoals;

      @ManyToOne(optional = false)
      @JoinColumn(nullable = false)
      private Player player;

      @ManyToOne(optional = false)
      @JoinColumn(nullable = false)
      private ConcreteMatch concreteMatch;

      @Column(name = "pfm_matchpoints")
      private short matchPoints;

}
