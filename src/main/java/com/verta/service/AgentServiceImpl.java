package com.verta.service;

import com.verta.domain.Agent;
import com.verta.repository.agent.AgentRepository;
import com.verta.repository.agent.AgentRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    // Java annotation
//    @Inject
//    @Named("agentRepository")

    // Spring annotation
//    @Autowired // We made inject
//    @Qualifier("agentRepository")

    // "final" need to link not change when we do inject
    private final AgentRepositoryInterface agentRepository;

//    public AgentServiceImpl(AgentRepositoryInterface agentRepository) {
//        this.agentRepository = agentRepository;
//    }

    @Override
    public List<Agent> findAll() {
        return agentRepository.findAll();
    }

//        @Autowired
//    //@Inject
//    public void setAgentRepository(@Qualifier("agentRepository") AgentRepositoryInterface agentRepository) {
//        this.agentRepository = agentRepository;
//    }
}
