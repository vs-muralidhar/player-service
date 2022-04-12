package com.minikube.vslocal.player.controller;

import com.minikube.vslocal.player.dto.Player;
import com.minikube.vslocal.player.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    PlayerService service;

    PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping("/healthz")
    public ResponseEntity<HttpStatus> healthCheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/player")
    public ResponseEntity<Integer> createPlayer(@RequestBody Player player) {
        Integer playerId = service.createPlayer(player);
        return ResponseEntity.ok(playerId);
    }

    @GetMapping("/players")
    public ResponseEntity<List<Player>> findAllPlayers() {
        List<Player> players = service.fetchAllPlayers();

        return ResponseEntity.ok(players);
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Player> findPlayerById(@PathVariable("id") int id) {
        Player player = new Player();

        return ResponseEntity.ok(player);
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<HttpStatus> updatePlayer(@PathVariable int id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}