package com.design4music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableSwagger2
public class TradeserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(TradeserverApplication.class, args);
	}
}
