package com.connect.connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectApplication {

    public static void main(String[] args) {
        // --- DEBUG: IMPRIMINDO NO CONSOLE ---
        System.out.println("==================================================");
        System.out.println("====> TENTANDO INICIAR COM USUARIO: admin_connect");
        System.out.println("==================================================");

        // Força a configuração
        System.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/connect?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSL=false");
        System.setProperty("spring.datasource.username", "admin_connect");
        System.setProperty("spring.datasource.password", "123456");
        
        System.setProperty("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
        System.setProperty("spring.jpa.hibernate.ddl-auto", "update");

        SpringApplication.run(ConnectApplication.class, args);
        System.setProperty("spring.datasource.username", "root");
        System.setProperty("spring.datasource.password", "123456"); // Agora a senha do banco é essa!
    }
}
