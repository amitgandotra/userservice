package com.ibps.openapi.dao;

import com.ibps.openapi.domain.User;
import com.ibps.openapi.repositories.jpa.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserDao {
    @Autowired UserRepository userRepository;
    @PersistenceContext
    private EntityManager em;
    private static final Log log = LogFactory.getLog(UserDao.class);

    @Transactional(readOnly = true)
    @Cacheable("serviceCategoryParamCache")
    public User findUserByName(String username) {

        log.info("calling findUserByName -Checking if this username " + username + " exists");
        String sql = "SELECT u.* " +
                " FROM user u" +
                " WHERE u.username=:username";

        Query query = em.createNativeQuery(sql, User.class);
        query.setParameter("username", username);

        if (!query.getResultList().isEmpty()) {
            return (User) query.getResultList().get(0);
        }

        return null;
    }
    @Transactional(readOnly = true)
    public boolean hasUser(String username) {
        log.info("calling hasUser -Checking if this username " + username + " exists");
        User user = findUserByName(username);
        if (user != null) {
            return true;
        }
        return false;
    }
}
