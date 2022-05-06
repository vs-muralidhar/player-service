package com.minikube.vslocal.player;


import com.minikube.vslocal.player.dto.Player;
import com.minikube.vslocal.player.dto.PlayerDataModel;
import com.minikube.vslocal.player.repository.PlayerRepository;
import com.minikube.vslocal.player.service.PlayerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PlayerServiceTest {

    @Mock
    PlayerRepository repository;

    @InjectMocks
    PlayerService service;

    @Test
    public void testFetchAllPlayers() throws Exception {
        List<PlayerDataModel> players = new ArrayList<>();
        players.add(PlayerDataModel.builder().id(1).name("CR").position("forward").build());
        Mockito.when(repository.findAll()).thenReturn(players);

        Assertions.assertThat(service.fetchAllPlayers()).hasSizeGreaterThan(0);
    }

    @Test
    public void testCreatePlayer() {
        PlayerDataModel player = PlayerDataModel.builder().id(1).name("CR").position("forward").build();
        List<PlayerDataModel> players = new ArrayList<>();
        players.add(player);
        Mockito.when(repository.save(Mockito.any(PlayerDataModel.class)))
                .thenReturn(player);
        Assertions.assertThat(service.createPlayer(new Player(1,"CR","forward", "sample"))).isEqualTo(1);
    }

    @Test
    public void testGetPlayerById() throws Exception {
        PlayerDataModel player = PlayerDataModel.builder().id(1).name("CR").position("forward").build();

        Mockito.when(repository.getById(Mockito.anyInt())).thenReturn(player);

        Assertions.assertThat(player.toString()).isNotNull();
        Assertions.assertThat(service.fetchPlayerById(1).getId()).isEqualTo(1);
    }

}
