package com.verta.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ConnectionPoolConfig.class, DatabaseProperties.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.verta")
public class ApplicationContextStarter {
}