package com.connect.connect.service.autenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InstituicaoInterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    private InstituicaoLoginInterceptor instituicaoLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(instituicaoLoginInterceptor)
                .addPathPatterns("/**") 
                .excludePathPatterns(
                    "/", 
                    "/logarInstituicao", 
                    "/cadastroInstituicao", 
                    "/salvarInstituicao",   
                    
                    "/sair",
                    "/sairProf", 
                    

                    "/error",
                    "/horarioProf" 

                ) 
                .order(0); 
    }
}