package org.jeycode.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rules
{

      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      @Column(name = "rules_id")
      private long rulesId;

      @Column(name = "result_points", nullable = false)
      private int resultPoints;

      @Column(name = "sign_points", nullable = false)
      private int signPoints;

      @Column(name = "goalsBCF_points", nullable = false)
      private int goalsBCFPoints;
}
