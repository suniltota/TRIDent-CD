package com.actualize.mortgage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * This class initiates the service for generation of JSON Object from MISMO XML and vice versa for closing disclosure
 * @author sboragala
 *
 */
@SpringBootApplication
public class TriDentCdApplication{
	
	private static final Logger LOG = LogManager.getLogger(TriDentCdApplication.class);

	public static void main(String[] args) {
		LOG.info("Initiated TRIDENT-CD service");
		SpringApplication.run(TriDentCdApplication.class, args);
	}
}
