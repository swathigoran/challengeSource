package com.att;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AttApplication1Application extends SpringBootServletInitializer {

	/*public static void main(String[] args) {
		SpringApplication.run(AttApplication1Application.class, args);
	}*/
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(AttApplication1Application.class);
	    }

	    public static void main(String[] args) throws Exception {
	        SpringApplication.run(AttApplication1Application.class, args);
	    }
}
