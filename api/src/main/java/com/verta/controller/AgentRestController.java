package com.verta.controller;

import com.verta.controller.request.AgentCreateRequest;
import com.verta.controller.request.AgentSearchRequest;
import com.verta.domain.SearchCriteria;
import com.verta.domain.Agent;
import com.verta.repository.hibernate.HibernateAgentInterface;
import com.verta.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // include @Controller and @ResponseBody
@RequiredArgsConstructor
@RequestMapping("/rest/agents")
public class AgentRestController {

    private final AgentService agentService;

    private final HibernateAgentInterface agentRepository;


    @GetMapping
    @RequestMapping("/hibernate")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findAllHibernateAgents() {

        return new ResponseEntity<>(Collections.singletonMap("result", agentRepository.findAll(1, 1)), HttpStatus.OK);

//        return new ResponseEntity<>(Collections.singletonMap("result", agentRepository.findAll()), HttpStatus.OK);

//        return new ResponseEntity<>(Collections.singletonMap("result", agentRepository.getAgentsStats()), HttpStatus.OK);

//        return Collections.singletonMap("result", agentService.findAll());
    }

    @GetMapping
    @RequestMapping("/hibernate/criteria")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findAllHibernateAgents(@ModelAttribute SearchCriteria criteria) {

        return new ResponseEntity<>(Collections.singletonMap("result", agentRepository.criteriaAPITest(criteria)), HttpStatus.OK);

        //return Collections.singletonMap("result", userService.findAll());
    }

    // first example
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Agent> findAllAgents() {
//       return agentService.findAll();
//    }

//     second example
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Map<String, Object> findAllAgents() {
//        return Collections.singletonMap("result", agentService.findAll());
//    }


    // third example
    @GetMapping
    @Secured("ROLE_ADMIN")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findAllAgents() {

        return new ResponseEntity<>(Collections.singletonMap("result", agentService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> findAllAgentsWithParams(@ModelAttribute AgentSearchRequest agentSearchRequest) {

        int verifiedLimit = Integer.parseInt(agentSearchRequest.getLimit());
        int verifiedOffset = Integer.parseInt(agentSearchRequest.getOffset());

        List<Agent> agents = agentService.search(verifiedLimit, verifiedOffset);

        Map<String, Object> model = new HashMap<>();
        model.put("agent", "Victor");
        model.put("agents", agents);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findAgentById(@PathVariable String id) {

        //We have added id parsing and number format checking
        long agentId = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("agent", agentService.findById(agentId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createAgent(@RequestBody AgentCreateRequest createRequest) {
        Agent agent = new Agent();
        agent.setAgentName(createRequest.getAgentName());
        agent.setAgentSurname(createRequest.getAgentSurname());
        agent.setBirthday(new Timestamp(new Date().getTime()));
        agent.setAgentPhone("375290000000");
        agent.setPercentReward(createRequest.getPercentReward());
        agent.setCreationDate(new Timestamp(new Date().getTime()));
        agent.setModificationDate(new Timestamp(new Date().getTime()));
        agent.setIsDeleted(false);

        agentService.creat(agent);

        List<Agent> agents = agentService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("agent", agent.getAgentName());
        model.put("agents", agents);


        return new ResponseEntity<>(model, HttpStatus.CREATED);

    }

}

