package com.verta;

import com.verta.domain.Agent;
import com.verta.repository.agent.AgentRepositoryInterface;
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


        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext("com.verta");

        AgentRepositoryInterface agentRepository = annotationConfigApplicationContext.
                getBean(AgentRepositoryInterface.class);

        for (Agent agent : agentRepository.findAll()) {
            System.out.println(agent);
        }
    }
}
