package com.verta.controller;

import com.verta.domain.Agent;
import com.verta.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequiredArgsConstructor

public class AgentController {

    private final AgentService agentService;

    @RequestMapping(method = RequestMethod.GET, value = "/agents")
    public ModelAndView findAllAgent() {
        List<Agent> agents = agentService.findAll();

        ModelAndView model = new ModelAndView();
        model.addObject("agent", "Zhenya");
        model.addObject("agent", agents);

        model.setViewName("hello");

        return model;
    }
}