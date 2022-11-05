package com.verta.controller.springdata;

import com.verta.repository.springdata.AgentSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/agents")
public class AgentController {
    private final AgentSpringDataRepository repository;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

//        return new ResponseEntity<>(Collections.singletonMap("result", repository.findAll()), HttpStatus.OK);

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
    }
}