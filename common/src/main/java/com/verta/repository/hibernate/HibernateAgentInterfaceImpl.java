package com.verta.repository.hibernate;


import com.verta.domain.Agent;
import com.verta.domain.SearchCriteria;
import com.verta.domain.hibernate.HibernateAgent;
import com.verta.domain.hibernate.HibernateAgent_;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class HibernateAgentInterfaceImpl implements HibernateAgentInterface {

//    private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;

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

//        try (Session session = sessionFactory.openSession()) {
//
////            return session.createQuery("select hb from HibernateAgent hb", HibernateAgent.class).getResultList();
//            return session.createQuery(query, HibernateAgent.class).getResultList();
//        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select hb from HibernateAgent hb", HibernateAgent.class).getResultList();
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
//        final String query = "select entity.get_agents_stats_average_percent_reward(false)";

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

    @Override
    public Object criteriaAPITest(SearchCriteria criteria) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //1. Get Builder for Criteria object
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HibernateAgent> query = cb.createQuery(HibernateAgent.class); //here select, where, orderBy, having
        Root<HibernateAgent> root = query.from(HibernateAgent.class); //here params  select * from m_users -> mapping

        /*type of future params in prepared statement*/
        ParameterExpression<String> param = cb.parameter(HibernateAgent_.agentName.getJavaType());
        ParameterExpression<Long> agentSearchParam = cb.parameter(HibernateAgent_.id.getJavaType());

        /*Provide access to fields in class that mapped to columns*/
        Expression<Long> id = root.get(HibernateAgent_.id);
        Expression<String> name = root.get(HibernateAgent_.agentName);
        Expression<String> surname = root.get(HibernateAgent_.agentSurname);

        /*SQL Query customizing*/
        query
                .select(root)
                .distinct(true)
                .where(
                        cb.or(
                                cb.like(name, param),  //agentName like param
                                cb.like(surname, param)  //agentSurname like param
                        ),
                        cb.and(
                                cb.gt(id, agentSearchParam), //>0
                                cb.not(id.in(40L, 50L)) //in (40,50)
                        )
//                        ,
//                        cb.between(
//                                root.get(TestUser_.birthDate),
//                                new Timestamp(new Date().getTime()),
//                                new Timestamp(new Date().getTime())
//                        )
                )
                .orderBy(cb.asc(root.get(HibernateAgent_.id)));

        TypedQuery<HibernateAgent> resultQuery = entityManager.createQuery(query); //prepared statement on hql
        resultQuery.setParameter(param, StringUtils.join("%", criteria.getAgentName(), "%"));
        resultQuery.setParameter(agentSearchParam, criteria.getLowerBoundUserId());
        return resultQuery.getResultList();
    }
}
