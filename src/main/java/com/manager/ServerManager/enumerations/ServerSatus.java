package com.manager.ServerManager.enumerations;

public enum ServerSatus {
	SERVER_UP("SERVER_UP"),
	SERVER_DOWN("SERVER_DOWN");
	 
	private final String status;
	
	ServerSatus(String status){
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
