package com.example.backend_verwaltungssoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = {"com.example.backend_verwaltungssoftware.Repositories"})
@EntityScan(basePackages = {"com.example.backend_verwaltungssoftware.Entities", "com.example"})
//@ComponentScan(basePackageClasses = User_Controller.class)
public class BackEndVerwaltungssoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndVerwaltungssoftwareApplication.class, args);
    }

}
