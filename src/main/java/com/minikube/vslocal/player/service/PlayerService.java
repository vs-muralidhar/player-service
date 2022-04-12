package com.minikube.vslocal.player.service;

import com.minikube.vslocal.player.dto.Player;
import com.minikube.vslocal.player.dto.PlayerDataModel;
import com.minikube.vslocal.player.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    PlayerRepository repository;

    PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> fetchAllPlayers() {
        List<PlayerDataModel> players = repository.findAll();
        return players.stream().map(x -> new Player(x.getId(), x.getName(), x.getPosition())).collect(Collectors.toList());
    }

    public Integer createPlayer(Player player) {
        PlayerDataModel playerDataModel = new PlayerDataModel(player.getId(), player.getName(), player.getPosition());
        return repository.save(playerDataModel).getId();
    }
}
