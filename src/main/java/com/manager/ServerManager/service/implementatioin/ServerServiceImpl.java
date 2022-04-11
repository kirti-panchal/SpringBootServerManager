package com.manager.ServerManager.service.implementatioin;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.manager.ServerManager.models.Server;
import com.manager.ServerManager.repo.ServerRepo;
import com.manager.ServerManager.service.ServerService;
import com.manager.ServerManager.enumerations.ServerSatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService{
	
	private final ServerRepo serverRepo;

	@Override
	public Server create(Server server) {
		// TODO Auto-generated method stub
		log.info("Saving the name of the server = {}" , server.getName());
		server.setImageUrl(setImageUrl());
		return serverRepo.save(server);
	}

	@Override
	public Collection<Server> list(int limit) {
		// TODO Auto-generated method stub
		log.info("FEtching all Servers");
		return serverRepo.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Server get(Long id) {
		// TODO Auto-generated method stub
		log.info("Getting the server instance by id = {}", id);		
		return serverRepo.getById(id);
	}

	@Override
	public Server update(Server server) {
		// TODO Auto-generated method stub
		log.info("Updating server = {}", server.getName());
		//Going to save the changes if the server with id is present
		//Else if id is null a new entity is created and saved
		return serverRepo.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		log.info("deleting server by id = {}",id);
		serverRepo.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public Server ping(String ipAddress) throws IOException {
		// TODO Auto-generated method stub
		log.info("Server Ping happening for the server with IP= {}", ipAddress);
		Server server = serverRepo.findByIpAddress(ipAddress);
		InetAddress address = InetAddress.getByName(ipAddress);
		server.setStatus(address.isReachable(1000)? ServerSatus.SERVER_UP : ServerSatus.SERVER_DOWN);
		if(server != null) {
			serverRepo.save(server);
		}		
		return server;
	}
	
	private String setImageUrl() {
		String[] names = {"s1.jpg","s2","s3","s4"};
		//return ServletUriComponentsBuilder.fromCurrentContextPath().path("server/image/"+ names[new Random().nextInt(4)]).toUriString();
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("server/image/"+ names[0]).toUriString();
	}

}
