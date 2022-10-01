package com.verta;

import com.verta.domain.Agent;
import com.verta.repository.agent.AgentRepositoryInterface;
import com.verta.service.AgentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    public static void main(String[] args) {
        // At the beginning of main ClassPathXmlApplicationContext read all from application-context.xml create
        // Beans and put in application-context.xml all created Beans
//        ClassPathXmlApplicationContext ClassPathXmlApplicationContext = new ClassPathXmlApplicationContext
//                ("classpath:application-context.xml");
//
//        Agent agent1 = ClassPathXmlApplicationContext.getBean("agent1", Agent.class);
//        Agent agent2 = ClassPathXmlApplicationContext.getBean("agent2", Agent.class);
//
//        System.out.println(agent1);
//        System.out.println(agent2);


        // We have a class which work with annotation
        // We scan package com.verta
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext("com.verta");

//        AgentRepositoryInterface agentRepository = annotationConfigApplicationContext.
//                getBean(AgentRepositoryInterface.class);
//
//        for (Agent agent : agentRepository.findAll()) {
//            System.out.println(agent);
//        }

        // We have a class which implements AgentService - which have annotation @Service - it is a Bean
        // Next we say "context give us spring Bean, and we call on him a method"
        AgentService agentService = annotationConfigApplicationContext.getBean(AgentService.class);

        for (Agent agent : agentService.findAll()) {
            System.out.println(agent);
        }


    }
}