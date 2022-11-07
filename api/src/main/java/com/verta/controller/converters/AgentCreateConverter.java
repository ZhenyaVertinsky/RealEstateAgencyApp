package com.verta.controller.converters;

import com.verta.controller.request.AgentCreateRequest;
import com.verta.domain.hibernate.Credentials;
import com.verta.domain.hibernate.HibernateAgent;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class AgentCreateConverter extends AgentBaseConverter<AgentCreateRequest, HibernateAgent> {

    @Override
    public HibernateAgent convert(AgentCreateRequest source) {

        HibernateAgent hibernateAgent = new HibernateAgent();

        hibernateAgent.setCreationDate(new Timestamp(new Date().getTime()));

        Credentials credentials = new Credentials(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)
        );

        hibernateAgent.setCredentials(credentials);

        return doConvert(hibernateAgent, source);
    }
}