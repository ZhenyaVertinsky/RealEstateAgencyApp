package com.verta.security;


import com.verta.domain.Agent;
import com.verta.domain.Role;
import com.verta.repository.agent.AgentRepositoryInterface;
import com.verta.repository.jdbctemplate.RoleRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.Optional;
import java.util.stream.Collectors;


@Configuration
@RequiredArgsConstructor
public class AgentSecurityService implements UserDetailsService {

    private final AgentRepositoryInterface agentRepository;

    private final RoleRepositoryInterface roleRepository;

    @Override
    public UserDetails loadUserByUsername(String agentName) throws UsernameNotFoundException {
        try {
            /*Find agent in DB*/
            Optional<Agent> searchResult = agentRepository.findByLogin(agentName);

            if (searchResult.isPresent()) {
                Agent agent = searchResult.get();

                /*We are creating Spring Security Agent object*/

                return new org.springframework.security.core.userdetails.User(
                        agent.getLogin(),
                        agent.getPassword(),
//                        ["ROLE_AGENT", "ROLE_ADMIN"]
                        AuthorityUtils.commaSeparatedStringToAuthorityList(
                                roleRepository.findRolesByAgentId(agent.getId())
                                        .stream()
                                        .map(Role::getRoleName)
                                        //.map(SystemRoles::name)
                                        .collect(Collectors.joining(","))
                        )
                );
            } else {
                throw new UsernameNotFoundException(String.format("No agent found with login '%s'.", agentName));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Agent with this login not found");
        }
    }
}