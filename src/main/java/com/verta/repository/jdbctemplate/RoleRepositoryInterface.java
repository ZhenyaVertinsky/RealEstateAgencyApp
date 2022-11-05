package com.verta.repository.jdbctemplate;

import com.verta.domain.Role;
import com.verta.repository.CRUDRepository;

import java.util.List;

public interface RoleRepositoryInterface extends CRUDRepository<Long, Role> {

    List<Role> findRolesByAgentId(Long agentId);
}
