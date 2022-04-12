package com.minikube.vslocal.player;

import com.minikube.vslocal.player.controller.PlayerController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlayerServiceApplicationTests {

	@Autowired
	PlayerController playerController;

	@Test
	void contextLoads() {
		Assertions.assertThat(playerController).isNotNull();
	}

}
