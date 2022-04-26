package com.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Note: http://localhost:8100/swagger-ui/index.html to access the swagger
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(buildApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.learning.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger")
				.description("Transaction Api")
				.version("1.0.0").build();
	}
}
