package com.minikube.vslocal.player.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "team")
public class TeamDataModel {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "team_name")
    private String name;

    @OneToMany(mappedBy = "team")
    private List<PlayerDataModel> players;
}
