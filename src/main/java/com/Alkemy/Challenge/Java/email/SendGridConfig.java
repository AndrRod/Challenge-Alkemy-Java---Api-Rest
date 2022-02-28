package com.Alkemy.Challenge.Java.email;


import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:aplication.properties")
public class SendGridConfig {
    @Value("${api.key}")
    private String key;

    @Bean
    public SendGrid getSendrid(){
//        return new SendGrid(System.getenv(key));
        return new SendGrid(key);
    }

}
