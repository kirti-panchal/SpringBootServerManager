package com.manager.ServerManager.models;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
//Will not add null properties to the Object
@JsonInclude(content = Include.NON_NULL)
public class Response {
	protected LocalDateTime timeStamp;
	protected int statusCode;
	protected HttpStatus status;
	protected String reason;
	protected String message;
	protected String devMessage;
	protected Map<?,?> data; 
	
}
