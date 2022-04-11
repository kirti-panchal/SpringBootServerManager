package com.manager.ServerManager;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.manager.ServerManager.enumerations.ServerSatus;
import com.manager.ServerManager.models.Server;
import com.manager.ServerManager.repo.ServerRepo;

@SpringBootApplication
public class ServerManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerManagerApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null,"192.168.1.38","My PC","30mb","Personal Computer","http://localhost:8080/server/image/s1.jpg",ServerSatus.SERVER_UP));
			serverRepo.save(new Server(null,"142.250.191.36","Google","30mb","Personal Computer","http://localhost:8080/server/image/s1.jpg",ServerSatus.SERVER_UP));
			serverRepo.save(new Server(null,"64.233.160.0","google 2","30mb","Personal Computer","http://localhost:8080/server/image/s1.jpg",ServerSatus.SERVER_DOWN));
			serverRepo.save(new Server(null,"198.38.96.0","Netflix","30mb","Personal Computer","http://localhost:8080/server/image/s1.jpg",ServerSatus.SERVER_UP));
		};
	}
	
	/*
	 * @Bean public CorsFilter corsFilter() { UrlBasedCorsConfigurationSource
	 * urlBasedCorsConfiguration = new UrlBasedCorsConfigurationSource();
	 * CorsConfiguration corsConfiguration = new CorsConfiguration();
	 * corsConfiguration.setAllowCredentials(true);
	 * corsConfiguration.setAllowedOrigins(Arrays.asList("http//localhost:3000",
	 * "http//localhost:4200"));
	 * 
	 * urlBasedCorsConfiguration.registerCorsConfiguration("/**",
	 * corsConfiguration);
	 * 
	 * return new CorsFilter((CorsConfigurationSource) urlBasedCorsConfiguration); }
	 */

}
