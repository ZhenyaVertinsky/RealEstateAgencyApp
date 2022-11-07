package com.verta.service;

import com.verta.domain.Agent;
import com.verta.repository.agent.AgentRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

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

//    private final List<AgentRepositoryInterface> interfaces;

//    public AgentServiceImpl(AgentRepositoryInterface agentRepository) {
//        this.agentRepository = agentRepository;
//    }

    @Override
    public List<Agent> findAll() {

//        for (AgentRepositoryInterface anInterface : interfaces) {
//            System.out.println(anInterface);
//        }

        return agentRepository.findAll();
    }

    @Override
    public Map<String, Object> getAgentStats() {
        return agentRepository.getAgentsStats();
    }

    @Override
    public Agent creat(Agent object) {
        return agentRepository.create(object);
    }

    @Override
    public Agent findById(Long agentId) {
        return agentRepository.findById(agentId);
    }

    @Override
    public List<Agent> search(int limit, int offset) {
        return agentRepository.findAll(limit, offset);
    }

    //        @Autowired
//    //@Inject
//    public void setAgentRepository(@Qualifier("agentRepository") AgentRepositoryInterface agentRepository) {
//        this.agentRepository = agentRepository;
//    }
}
