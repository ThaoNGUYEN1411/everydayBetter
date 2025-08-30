package co.simplon.everydaybetterbusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EverydaybetterBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(EverydaybetterBusinessApplication.class, args);
    }
}
