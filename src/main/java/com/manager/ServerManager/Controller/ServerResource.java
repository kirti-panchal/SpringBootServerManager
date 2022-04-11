package com.manager.ServerManager.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.ServerManager.enumerations.ServerSatus;
import com.manager.ServerManager.models.Response;
import com.manager.ServerManager.models.Server;
import com.manager.ServerManager.repo.ServerRepo;
import com.manager.ServerManager.service.implementatioin.ServerServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
public class ServerResource {
	
	private final ServerServiceImpl serverServiceImpl;
		
	@GetMapping("/list")
	public ResponseEntity<Response> getServers() throws InterruptedException{
		//TimeUnit.SECONDS.sleep(3);
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("servers", serverServiceImpl.list(30)))
				.message("Servers List")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
		);
	}
	
	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException{
		Server s = serverServiceImpl.ping(ipAddress);
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("server", s))
				.message(s.getStatus() == ServerSatus.SERVER_UP ? "Ping Successful" : "Ping Failed")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
		);
	}
	
	@PostMapping("/save")
	//@valid will check for the validation we added during server class creation in our case
	// it is the ipAddress will not be empty
	public ResponseEntity<Response> createServer(@RequestBody @Valid Server s){
		//serverServiceImpl.create(s)
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				//Since service Impl return the created server we are using it directly
				.data(Map.of("server", serverServiceImpl.create(s)))
				.message("Server Creates")
				.status(HttpStatus.CREATED)
				.statusCode(HttpStatus.CREATED.value())
				.build()
		);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") long id){
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				//Since service Impl return the created server we are using it directly
				.data(Map.of("server", serverServiceImpl.get(id)))
				.message("Server with id {}"+id)
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
		);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") long id){
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				//Since service Impl return the created server we are using it directly
				.data(Map.of("deleted", serverServiceImpl.delete(id)))
				.message("Server with id "+id+" deleted")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
		);
	}
	
	
	@PostMapping("/update")
	//@valid will check for the validation we added during server class creation in our case
	// it is the ipAddress will not be empty
	public ResponseEntity<Response> updateServer(@RequestBody @Valid Server s){
		//serverServiceImpl.create(s)
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				//Since service Impl return the created server we are using it directly
				.data(Map.of("server", serverServiceImpl.update(s)))
				.message("Server Updated")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
		);
	}

	//Path should match Path from serverServiceImpl
	@GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
		return Files.readAllBytes(Paths.get("D:/SpringBoot/Projects/ServerManager/images/"+fileName));
	}
}
