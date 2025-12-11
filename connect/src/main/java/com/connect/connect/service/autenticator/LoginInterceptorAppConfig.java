package com.connect.connect.service.autenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor logininterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logininterceptor)
            .excludePathPatterns(
                // Estilos
                "/components/styles/bootstrap.min.css",
                "/components/styles/styles.css",
                "/components/scripts/bootstrap.bundle.min.js", 
                "/components/styles/error.css",
                "/styles/**", "/images/**", "/scripts/**",

                // Rotas Instituição/Home
                "/",
                "/home",
                "/logarInstituicao",
                "/cadastroInstituicao", 
                "/salvarInstituicao",   
            
                // Rotas Professor
                "/login",
                "/logar",
                "/login-prof",
                "/logar-prof",
                "/cadastroProf",
                "/cadastro-prof",
                "/salvar-prof",
                "/sair",
                "/sairProf", 
                
                // --- ROTAS DE ALUNO (NOVAS) ---
                "/cadastrar-aluno", // <--- LIBERADO
                "/salvar-aluno",    // <--- LIBERADO
                "/login-aluno",     // <--- LIBERADO
                "/logar-aluno",     // <--- LIBERADO
                "/sair-aluno",      // <--- LIBERADO
                
                "/error",
                "/horarioProf"
            )
            .order(1); 
    }
}