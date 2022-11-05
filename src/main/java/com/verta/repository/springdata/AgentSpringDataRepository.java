package com.verta.repository.springdata;

import com.verta.domain.hibernate.HibernateAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AgentSpringDataRepository extends CrudRepository<HibernateAgent, Long>,
        JpaRepository<HibernateAgent, Long>, PagingAndSortingRepository<HibernateAgent, Long> {

}