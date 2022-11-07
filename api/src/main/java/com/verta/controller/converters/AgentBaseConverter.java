package com.verta.controller.converters;

import com.verta.controller.request.AgentCreateRequest;
import com.verta.domain.hibernate.HibernateAgent;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class AgentBaseConverter <S, T> implements Converter<S, T> {

    public HibernateAgent doConvert(HibernateAgent agentForUpdate, AgentCreateRequest request) {

        agentForUpdate.setAgentName(request.getAgentName());
        agentForUpdate.setAgentSurname(request.getAgentSurname());
        agentForUpdate.setBirthday(request.getBirthday());
        agentForUpdate.setPercentReward(request.getPercentReward());

        /*System fields filling*/
        agentForUpdate.setModificationDate(new Timestamp(new Date().getTime()));
        agentForUpdate.setIsDeleted(false);

        return agentForUpdate;
    }
}