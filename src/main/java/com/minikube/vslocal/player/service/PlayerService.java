package com.minikube.vslocal.player.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minikube.vslocal.player.dto.Player;
import com.minikube.vslocal.player.dto.PlayerDataModel;
import com.minikube.vslocal.player.dto.RandomQuote;
import com.minikube.vslocal.player.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
        List<PlayerDataModel> players = repository.findAll();
        return players.stream().map(x -> new Player(x.getId(), x.getName(), x.getPosition(), null)).collect(Collectors.toList());
    }

    public Integer createPlayer(Player player) {
        log.info("createPlayer");
        PlayerDataModel playerDataModel = PlayerDataModel.builder().id(player.getId()).name(player.getName()).position(player.getPosition()).build();
        List<PlayerDataModel> players = new ArrayList<>();
        players.add(playerDataModel);
        return repository.save(playerDataModel).getId();
    }

    public Player fetchPlayerById(int id) throws IOException, InterruptedException {
        log.info("fetchPlayerById");

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(new StringBuilder().append("https://api-thirukkural.vercel.app/api?num=").append(String.valueOf(id)).toString()))
                .GET().build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        RandomQuote randomQuote = objectMapper.readValue(httpResponse.body(), RandomQuote.class);
        log.info(randomQuote.getTam_exp());

        PlayerDataModel playerDataModel = repository.getById(id);
        return new Player(playerDataModel.getId(), playerDataModel.getName(), playerDataModel.getPosition(), randomQuote.getEng());

    }
}
