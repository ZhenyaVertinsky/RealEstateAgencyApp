package com.verta.repository.springdata;

import com.verta.domain.hibernate.HibernateRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesSpringDataRepository extends JpaRepository<HibernateRole, Long> {

    @Cacheable("roles")
    @Query(value = "select r from HibernateRole r")
    List<HibernateRole> findAllCustom();
}
