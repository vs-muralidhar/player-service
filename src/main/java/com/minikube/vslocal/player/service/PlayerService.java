package com.minikube.vslocal.player.service;

import com.minikube.vslocal.player.dto.Player;
import com.minikube.vslocal.player.dto.PlayerDataModel;
import com.minikube.vslocal.player.dto.TeamDataModel;
import com.minikube.vslocal.player.repository.PlayerRepository;
import com.minikube.vslocal.player.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    PlayerRepository repository;
    TeamRepository teamRepository;

    PlayerService(PlayerRepository repository, TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }

    public List<Player> fetchAllPlayers() {
        List<PlayerDataModel> players = repository.findAll();
        return players.stream().map(x -> new Player(x.getId(), x.getName(), x.getPosition())).collect(Collectors.toList());
    }

    public Integer createPlayer(Player player) {
        PlayerDataModel playerDataModel = PlayerDataModel.builder().id(player.getId()).name(player.getName()).position(player.getPosition()).build();
        List<PlayerDataModel> players = new ArrayList<>();
        players.add(playerDataModel);
        TeamDataModel teamDataModel = TeamDataModel.builder().id(1).name("M-Utd").players(players).build();
        teamRepository.save(teamDataModel);
        return repository.save(playerDataModel).getId();
    }
}
