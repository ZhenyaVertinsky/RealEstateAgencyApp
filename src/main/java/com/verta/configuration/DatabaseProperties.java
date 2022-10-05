package com.verta.configuration;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
@PropertySource("classpath:database.properties") // Read from this file all information
public class DatabaseProperties {

    @Value("${POSTGRES_DRIVER_NAME}") //SpEL - Spring Expression language
    private String driverName;

    @Value("${DATABASE_URL}")
    private String url;

    @Value("${DATABASE_PORT}")
    private String port;

    @Value("${DATABASE_NAME}")
    private String name;

    @Value("${DATABASE_LOGIN}")
    private String login;

    @Value("${DATABASE_PASSWORD}")
    private String password;

}
