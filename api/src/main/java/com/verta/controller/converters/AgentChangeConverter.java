package com.verta.controller.converters;

import com.verta.controller.request.AgentChangeRequest;
import com.verta.domain.hibernate.HibernateAgent;
import com.verta.repository.springdata.AgentSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AgentChangeConverter extends AgentBaseConverter<AgentChangeRequest, HibernateAgent> {

    private final AgentSpringDataRepository repository;

    @Override
    public HibernateAgent convert(AgentChangeRequest source) {

        Optional<HibernateAgent> agent = repository.findById(source.getId());
        return doConvert(agent.get(), source);
    }
}