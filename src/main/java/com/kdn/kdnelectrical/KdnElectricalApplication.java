package com.kdn.kdnelectrical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class KdnElectricalApplication {

	public static void main(String[] args) {
		SpringApplication.run(KdnElectricalApplication.class, args);
	}

}
