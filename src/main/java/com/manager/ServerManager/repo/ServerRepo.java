package com.manager.ServerManager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.manager.ServerManager.models.Server;

public interface ServerRepo extends JpaRepository<Server, Long>{
	Server findByIpAddress(String ipAddress);
}
