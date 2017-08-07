package com.data.webcrawler.application;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.data.webcrawler.application.impl.WebcrawlerImpl;

@SpringBootApplication
@ComponentScan(basePackages="com.data.webcrawler.*")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages ={ "com.data.webcrawler.repo"})
@EntityScan(basePackages ={ "com.data.webcrawler.entity"})
public class WebcrawlerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebcrawlerApplication.class);
	
	
	public static void main(String[] args) {
		LOGGER.info("Application start");
/*		SpringApplication app= new SpringApplication(WebcrawlerApplication.class);
	    app.run(args);
	    app.close();*/
	    
	    SpringApplication.run(WebcrawlerApplication.class, args).close();

	}
	
	@Autowired
	private WebcrawlerImpl webcrawlerImpl;

	@PostConstruct
	public void initApp(){
		webcrawlerImpl.startCrawling();
	}
	
	
}
