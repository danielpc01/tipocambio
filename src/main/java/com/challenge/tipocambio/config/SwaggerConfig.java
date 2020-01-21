package com.challenge.tipocambio.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("file:gradle.properties")
@SuppressWarnings("all")
public class SwaggerConfig {

    @Value("${currentVersion}")
    private String currentVersion;

    @Value("${com.exchange.rate.swagger.enabled:false}")
    private boolean swaggerEnabled;

    @Value("${com.exchange.rate.scan.package}")
    private String scanPackage;

    @Value("${com.exchange.rate.path.selector}")
    private String pathSelector;

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(scanPackage))
                .paths(PathSelectors.ant(pathSelector))
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("ExchangeRate API")
                .description("Microservices for exchange rate")
                .version(currentVersion)
                .build();
    }

}
