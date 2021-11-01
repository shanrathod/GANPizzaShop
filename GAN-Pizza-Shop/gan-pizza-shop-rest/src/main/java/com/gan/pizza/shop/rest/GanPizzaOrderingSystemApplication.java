package com.gan.pizza.shop.rest;

import com.gan.pizza.shop.envProperties.EnvironmentProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GanPizzaOrderingSystemApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        //SpringApplication.run(GanPizzaOrderingSystemApplication.class, args);

        configureApplication(new SpringApplicationBuilder()).run(args);

        System.out.println("REST API is running!");

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(GanPizzaOrderingSystemApplication.class).initializers(new EnvironmentProperties());
    }

}
