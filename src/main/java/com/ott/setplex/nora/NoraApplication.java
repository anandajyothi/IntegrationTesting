package com.ott.setplex.nora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ott.setplex.nora.config.ConfigProperties;

@SpringBootApplication
public class NoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoraApplication.class, args);
	}

	@Bean
	public ConfigProperties getConfigBean() {
		return new ConfigProperties();
	}
}
