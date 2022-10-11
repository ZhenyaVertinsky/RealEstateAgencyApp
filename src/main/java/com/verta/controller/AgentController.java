package com.verta.controller;

import com.verta.controller.request.AgentCreateRequest;
import com.verta.controller.request.AgentSearchRequest;
import com.verta.domain.Agent;
import com.verta.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/agents")
public class AgentController {

    private final AgentService agentService;

    @GetMapping
    public ModelAndView findAllAgents() {
        List<Agent> agents = agentService.findAll();
        ModelAndView model = new ModelAndView();
        model.addObject("agent", "Zhenya");
        model.addObject("agents", agents);

        model.setViewName("agents");

        return model;
    }

    @GetMapping("/search")
    public ModelAndView findAllAgentsWithParams(@ModelAttribute AgentSearchRequest agentSearchRequest) {

        int verifiedLimit = Integer.parseInt(agentSearchRequest.getLimit());
        int verifiedOffset = Integer.parseInt(agentSearchRequest.getOffset());

        List<Agent> agents = agentService.search(verifiedLimit, verifiedOffset);

        ModelAndView model = new ModelAndView();
        model.addObject("agent", "Zhenya");
        model.addObject("agents", agents);

        model.setViewName("agents");

        return model;
    }
    @GetMapping("/{id}")
    public ModelAndView findAgentById(@PathVariable String id) {

        //We have added id parsing and number format checking
        long agentId = Long.parseLong(id);
        Agent agent = agentService.findById(agentId);

        ModelAndView model = new ModelAndView();
        model.addObject("agentName", agent.getAgentName());
        model.addObject("agent", agent);

        model.setViewName("agent");

        return model;
    }

    @PostMapping
    //Jackson
    public ModelAndView createAgent(@RequestBody AgentCreateRequest createRequest) {
        Agent agent = new Agent();
        agent.setAgentName(createRequest.getUserName());
        agent.setAgentSurname(createRequest.getSurname());
        agent.setBirthday(new Timestamp(new Date().getTime()));
        agent.setAgentPhone("375290000000");
        agent.setPercentReward(createRequest.getPercentReward());
        agent.setCreationDate(new Timestamp(new Date().getTime()));
        agent.setModificationDate(new Timestamp(new Date().getTime()));
        agent.setIsDeleted(false);

        agentService.creat(agent);

        List<Agent> agents = agentService.findAll();

        ModelAndView model = new ModelAndView();
        model.addObject("agent", "Zhenya");
        model.addObject("agents", agents);

        model.setViewName("agents");

        return model;

    }


}