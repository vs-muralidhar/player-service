package com.minikube.vslocal.player.service;

import com.minikube.vslocal.player.dto.Player;
import com.minikube.vslocal.player.dto.PlayerDataModel;
import com.minikube.vslocal.player.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PlayerService {

    PlayerRepository repository;

    PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> fetchAllPlayers() {
        log.info("fetchAllPlayers");
        HttpClient httpClient = HttpClient.newBuilder().build();
        List<PlayerDataModel> players = repository.findAll();
        return players.stream().map(x -> new Player(x.getId(), x.getName(), x.getPosition())).collect(Collectors.toList());
    }

    public Integer createPlayer(Player player) {
        log.info("createPlayer");
        PlayerDataModel playerDataModel = PlayerDataModel.builder().id(player.getId()).name(player.getName()).position(player.getPosition()).build();
        List<PlayerDataModel> players = new ArrayList<>();
        players.add(playerDataModel);
        return repository.save(playerDataModel).getId();
    }
}
