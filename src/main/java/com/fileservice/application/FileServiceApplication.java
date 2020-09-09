package com.fileservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.fileservice.configuration.FileStorageProperties;

@SpringBootApplication
@ComponentScan({ "com.fileservice" })
@EnableConfigurationProperties({FileStorageProperties.class})
public class FileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileServiceApplication.class, args);
	}

}
