package com.ttps2023.CuentasClaras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class)
public class CuentasClarasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentasClarasApplication.class, args);
	}

}
