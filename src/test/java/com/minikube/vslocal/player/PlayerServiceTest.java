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
    public void testFetchAllPlayers() {
        List<PlayerDataModel> players = new ArrayList<>();
        players.add(new PlayerDataModel(1, "CR", "forward"));
        Mockito.when(repository.findAll()).thenReturn(players);

        Assertions.assertThat(service.fetchAllPlayers()).hasSizeGreaterThan(0);
    }

    @Test
    public void testCreatePlayer() {

        Mockito.when(repository.save(Mockito.any(PlayerDataModel.class)))
                .thenReturn(new PlayerDataModel(1, "CR", "forward"));
        Assertions.assertThat(service.createPlayer(new Player(1,"CR","forward"))).isEqualTo(1);
    }

}
