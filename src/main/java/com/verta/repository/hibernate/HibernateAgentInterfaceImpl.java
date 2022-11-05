package com.verta.repository.hibernate;


import com.verta.domain.Agent;
import com.verta.domain.hibernate.HibernateAgent;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class HibernateAgentInterfaceImpl implements HibernateAgentInterface {

    private final SessionFactory sessionFactory;

//    private final EntityManagerFactory entityManagerFactory;

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

//        final String query = "select max(hb.percentReward) from HibernateAgent hb " +
//                "inner join HibernateShopOrder so on so.id = hb.id " +
//                "left join HibernateMedicalInfo mi on mi.id = hb.id " +
//                " where so.sum > 10 and so.sum < 800 ";

        final String query = "select hb from HibernateAgent hb " +
                " inner join HibernateMedicalInfo mi on mi.id = hb.id  " +
                " where hb.percentReward > (select avg(h.id) from HibernateAgent h) and " +
                " mi.bloodType = 2 " +
                " " ;


//        final String query = "select * from entity.agents";

        try (Session session = sessionFactory.openSession()) {

//            return session.createQuery("select hb from HibernateAgent hb", HibernateAgent.class).getResultList();
            return session.createQuery(query, HibernateAgent.class).getResultList();
        }
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        return entityManager.createQuery("select hb from HibernateAgent hb", HibernateAgent.class).getResultList();
    }

    @Override
    public List<HibernateAgent> findAll(int limit, int offset) {
        return null;
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

//    @Override
//    public Map<String, Object> getAgentsStats() {
//        return null;
//    }
    @Override
    public Object getAgentsStats() {
        final String query = "select entity.get_agents_stats_average_percent_reward(false)";

//        try (Session session = sessionFactory.openSession()) {
//            //return session.createNativeQuery(query, HibernateUser.class).getResultList(); - native query run possibility
//            return session.createNativeQuery(query).getSingleResult();
//        }
        return null;
    }

    @Override
    public Optional<Agent> findByLogin(String login) {
        return Optional.empty();
    }
}
