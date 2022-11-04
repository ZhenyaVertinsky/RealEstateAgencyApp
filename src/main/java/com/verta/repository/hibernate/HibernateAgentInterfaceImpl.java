package com.verta.repository.hibernate;


import com.verta.domain.Agent;
import com.verta.domain.hibernate.HibernateAgent;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class HibernateAgentInterfaceImpl implements HibernateAgentInterface {

    private final SessionFactory sessionFactory;

    @Override
    public HibernateAgent findById(Long id) {
        return null;
    }

    @Override
    public Optional<HibernateAgent> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<HibernateAgent> findAll() {
        try (Session session = sessionFactory.openSession()) {

            return session.createQuery("select hb from HibernateAgent hb", HibernateAgent.class).getResultList();
        }
    }

    @Override
    public List<HibernateAgent> findAll(int limit, int offset) {
        try (Session session = sessionFactory.openSession()) {

            return session.createQuery("select hb from HibernateAgent hb", HibernateAgent.class).getResultList();
        }
    }

    @Override
    public HibernateAgent create(HibernateAgent object) {
        return null;
    }

    @Override
    public HibernateAgent update(HibernateAgent object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> getAgentsStats() {
        return null;
    }

    @Override
    public Optional<Agent> findByLogin(String login) {
        return Optional.empty();
    }
}
