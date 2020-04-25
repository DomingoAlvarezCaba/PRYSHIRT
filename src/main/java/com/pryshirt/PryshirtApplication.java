package com.pryshirt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.pryshirt.config.FileStorageProperties;

@EnableConfigurationProperties({FileStorageProperties.class})
@SpringBootApplication
public class PryshirtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PryshirtApplication.class, args);
	}
}
