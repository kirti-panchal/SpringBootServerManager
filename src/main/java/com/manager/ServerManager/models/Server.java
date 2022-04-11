package com.manager.ServerManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.manager.ServerManager.enumerations.ServerSatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Server {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	@NotEmpty(message = "Ip Address cannot be empty or null")
	private String ipAddress;	
	private String name;
	private String memory;
	private String type;
	private String imageUrl;
	private ServerSatus status;	
	
}
