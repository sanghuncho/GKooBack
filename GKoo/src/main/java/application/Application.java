package application;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = { "mypage", "motherj.noticeModule", "shippingService", "managementService", "warehouse" })
public class Application extends SpringBootServletInitializer  {
    private static final Logger LOGGER = LogManager.getLogger();

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
	public static void main(String[] args) throws Exception {
	    LOGGER.info("create spring App");
		SpringApplication.run(Application.class, args);
    }
}
