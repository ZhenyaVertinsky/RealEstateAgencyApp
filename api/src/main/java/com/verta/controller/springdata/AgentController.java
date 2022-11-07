package com.verta.controller.springdata;

import com.verta.controller.request.AgentCreateRequest;
import com.verta.controller.request.RoleRequest;
import com.verta.domain.Agent;
import com.verta.domain.Gender;
import com.verta.domain.hibernate.Credentials;
import com.verta.domain.hibernate.HibernateAgent;
import com.verta.domain.hibernate.HibernateRole;
import com.verta.repository.jdbctemplate.RoleRepositoryInterface;
import com.verta.repository.springdata.AgentSpringDataRepository;
import com.verta.repository.springdata.RolesSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/agents")
public class AgentController {
    private final AgentSpringDataRepository repository;

    private final RoleRepositoryInterface roleRepository;

    private final RolesSpringDataRepository rolesSpringDataRepository;

    private final ConversionService converter;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

//        return new ResponseEntity<>(Collections.singletonMap("result", repository.findAll()), HttpStatus.OK);

        return new ResponseEntity<>(Collections.singletonMap("result",
//                repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
                repository.findByIsDeletedOrderByIdDesc(false)), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> testEndpointSearchQuery(@RequestParam("id") Long agentId, @RequestParam("gender") String gender) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findByIdAndGender(agentId, Gender.valueOf(gender))), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> createAgent(@RequestBody AgentCreateRequest createRequest) {

//        HibernateAgent agent = new HibernateAgent();
//        agent.setAgentName(createRequest.getAgentName());
//        agent.setAgentSurname(createRequest.getAgentSurname());
//        agent.setBirthday(new Timestamp(new Date().getTime()));
//        agent.setAgentPhone("375290000000");
//        agent.setPercentReward(createRequest.getPercentReward());
//        agent.setCreationDate(new Timestamp(new Date().getTime()));
//        agent.setModificationDate(new Timestamp(new Date().getTime()));
//        agent.setIsDeleted(false);
//
////        agent.setAgentLogin(RandomStringUtils.randomAlphabetic(10));
////        agent.setAgentPassword(RandomStringUtils.randomAlphabetic(10));
//
//        Credentials credentials = new Credentials(
//                RandomStringUtils.randomAlphabetic(10),
//                RandomStringUtils.randomAlphabetic(10)
//        );
//
//        agent.setCredentials(credentials);


        RoleRequest roleRequest = new RoleRequest();

        HibernateRole convertTest = converter.convert(roleRequest, HibernateRole.class);


        HibernateAgent agent = converter.convert(createRequest, HibernateAgent.class);
//
//        HibernateAgent createdAgent = repository.save(agent);
        HibernateAgent createdAgent = repository.save(setRoles(agent));

        repository.createRoleRow(createdAgent.getId(), roleRepository.findById(2L).getId());

        Map<String, Object> model = new HashMap<>();
//        model.put("agents", createdAgent);
        model.put("agents", repository.findById(createdAgent.getId()).get());

        return new ResponseEntity<>(model, HttpStatus.CREATED);

    }

    private HibernateAgent setRoles(HibernateAgent agent) {
        Set<HibernateRole> roles = agent.getRoles();

        Set<HibernateRole> updatedRoles = new HashSet<>();

        if (!CollectionUtils.isEmpty(roles)) {
            updatedRoles.addAll(roles);
        }
        updatedRoles.add(rolesSpringDataRepository.findById(2L).get());
        updatedRoles.add(rolesSpringDataRepository.findById(3L).get());

        agent.setRoles(updatedRoles);

        return agent;
    }

}