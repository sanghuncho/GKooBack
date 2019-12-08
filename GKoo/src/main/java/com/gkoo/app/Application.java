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
@SpringBootApplication(scanBasePackages = { "mypage", "motherj.noticeModule", "shippingService", "managementService", "warehouse",
            "com.gkoo.controller", "com.gkoo.repository", "com.gkoo.service", "com.gkoo.repository.impl", "com.gkoo.service.impl", "com.gkoo.db", "com.gkoo.data", "com.gkoo.controller.test" })
public class Application extends SpringBootServletInitializer  {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
	public static void main(String[] args) throws Exception {
	    LOGGER.info("create gkoo App");
		SpringApplication.run(Application.class, args);
    }
}