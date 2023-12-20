package com.ttps2023.CuentasClaras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CuentasClarasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentasClarasApplication.class, args);
	}

}
