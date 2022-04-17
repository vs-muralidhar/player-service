package com.minikube.vslocal.player.repository;

import com.minikube.vslocal.player.dto.TeamDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamDataModel, Integer> {
}
