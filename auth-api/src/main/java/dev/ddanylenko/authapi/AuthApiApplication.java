package dev.ddanylenko.authapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class AuthApiApplication {

	private static final Logger log = LoggerFactory.getLogger(AuthApiApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(AuthApiApplication.class, args);
		log.info("Active profiles: {}", Arrays.toString(ctx.getEnvironment().getActiveProfiles()));
	}

}
