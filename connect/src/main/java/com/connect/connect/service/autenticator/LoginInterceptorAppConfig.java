package com.connect.connect.service.autenticator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorAppConfig implements WebMvcConfigurer{

	@Autowired
	private LoginInterceptor logininterceptor;

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logininterceptor)
            .excludePathPatterns(

                "/",
                "/logarInstituicao",
                "/cadastroInstituicao", 
                "/salvarInstituicao",   
            

                "/login",
                "/logar",
                "/cadastroProf",
                

                "/sair",
                "/sairProf", 
                

                "error",
                "/horarioProf"
            )
            .order(1); 
	}
	
}