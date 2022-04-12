package com.minikube.vslocal.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minikube.vslocal.player.controller.PlayerController;
import com.minikube.vslocal.player.dto.Player;
import com.minikube.vslocal.player.service.PlayerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @MockBean
    PlayerService service;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCheckHealth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/healthz"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void testCreatePlayer() throws Exception {
        Player player = new Player(1,"CR","forward");
        Mockito.when(service.createPlayer(Mockito.any(Player.class))).thenReturn(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/player")
                        .content(asJsonString(player))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void testGetAllPlayers() throws Exception {
        List<Player> players = new ArrayList<>();
        players.add(new Player(1, "CR","forward"));
        Mockito.when(service.fetchAllPlayers()).thenReturn(players);

        mockMvc.perform(MockMvcRequestBuilders.get("/players"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void testGetPlayerById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/player/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
