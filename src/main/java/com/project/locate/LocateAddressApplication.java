package com.project.locate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class LocateAddressApplication {

	public static void main(String[] args) {
		log.info("- Invoking Spting boot services -");
		SpringApplication.run(LocateAddressApplication.class, args);
	}
}
