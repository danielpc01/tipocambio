package com.challenge.tipocambio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {"com.challenge.tipocambio"})
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String LOGIN = "/tipocambio/api/v1/login";


    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(authorizationInterceptor).excludePathPatterns(LOGIN);
	}
    
}
