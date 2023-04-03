package com.appliance;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Appliance control Documentation",
				description = "Spring Boot REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Siva",
						email = "siva.nadupuru@gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.example.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Appliance control Documentation"

		)
)
public class ApplianceControlApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApplianceControlApiApplication.class, args);
	}

}
