package com.gkoo.app;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 *
 * @author sanghuncho
 *
 * @since  24.12.2018
 *
 */
//@SpringBootApplication(scanBasePackages = { "mypage", "motherj.noticeModule", "shippingService", "managementService", "warehouse", "com.gkoo.controller" })
@SpringBootApplication(scanBasePackages = { "com.gkoo.controller", "com.gkoo.data",  "com.gkoo.service", "com.gkoo.service.impl", "com.gkoo.repository", "com.gkoo.repository.impl"})
public class Application extends SpringBootServletInitializer  {
    private static final Logger LOGGER = LogManager.getLogger();

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
	public static void main(String[] args) throws Exception {
	    LOGGER.info("create gkoo App");
		SpringApplication.run(Application.class, args);
    }
}
