package com.springboot.bike;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Tag(name="SPRINGBOOT CRUD OPERATIONS")
//@EntityScan("com.springboot.bike.entities")
public class BikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeApplication.class, args);
	}

}
