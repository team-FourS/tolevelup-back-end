package com.fours.tolevelup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TolevelupApplication {

	public static void main(String[] args) {
		SpringApplication.run(TolevelupApplication.class, args);
	}

}
