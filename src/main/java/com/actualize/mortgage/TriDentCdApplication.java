package com.actualize.mortgage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
/**
 * This class initiates the current class
 * @author sboragala
 *
 */
@SpringBootApplication(scanBasePackages = "com.actualize.closingdisclosure")
@ImportResource("classpath:config.xml")
public class TriDentCdApplication extends org.springframework.boot.context.web.SpringBootServletInitializer{
	
	private static final Logger LOG = LogManager.getLogger(TriDentCdApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TriDentCdApplication.class, args);
	}
}
