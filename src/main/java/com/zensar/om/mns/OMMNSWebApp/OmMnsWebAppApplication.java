package com.zensar.om.mns.OMMNSWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
@ComponentScan("com.zensar.om.mns.*")
@EnableJpaRepositories("com.zensar.om.mns.repository")
@EntityScan("com.zensar.om.mns.*")
@EnableAutoConfiguration
@EnableScheduling
public class OmMnsWebAppApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(OmMnsWebAppApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(OmMnsWebAppApplication.class);
	}

}
