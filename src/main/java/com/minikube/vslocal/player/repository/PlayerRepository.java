package com.minikube.vslocal.player.repository;

import com.minikube.vslocal.player.dto.PlayerDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerDataModel, Integer> {

}
