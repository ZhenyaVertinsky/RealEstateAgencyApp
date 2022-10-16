package com.verta.controller;

import com.verta.controller.request.AgentSearchRequest;
import com.verta.domain.Agent;
import com.verta.repository.agent.AgentRepositoryInterface;
import com.verta.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // include @Controller and @ResponseBody
@RequiredArgsConstructor
@RequestMapping("/rest/agents")
public class AgentRestController {

    private final AgentService agentService;

    // first example
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Agent> findAllAgents() {
//       return agentService.findAll();
//    }

    // second example
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Map<String, Object> findAllAgents() {
//        return Collections.singletonMap("result", agentService.findAll());
//    }

    // third example
    @GetMapping
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
        model.put("agent", "Zhenya");
        model.put("agents", agents);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}

