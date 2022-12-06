package com.horner.chooser;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChooserApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
//		SpringApplication.run(ChooserApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ChooserApplication.class);
	    builder.headless(false);
	    ConfigurableApplicationContext context = builder.run(args);
	    
	    ServerProperties serverProperties = context.getBean(ServerProperties.class);

	    int serverPort = serverProperties.getPort();
	    MyTrayIcon m = new MyTrayIcon(context, serverPort);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ChooserApplication.class);
    }

}
