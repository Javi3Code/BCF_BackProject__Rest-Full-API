package org.jeycode.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConcreteMatch
{

      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private long concreteMatchId;

      @Column(nullable = false, length = 50)
      private String localTeam;

      @Column(nullable = false, length = 50)
      private String visitorTeam;

      @Column(length = 5)
      private String resultOfConcreteMatch;

      @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "concreteMatch", fetch = FetchType.LAZY)
      private List<PlayerFootballMatch> lstOfPlayerFootBallMatch;
}
