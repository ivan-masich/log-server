package net.masich.logserver.server.ui.dao.impl;

import net.masich.logserver.server.ui.dao.UserDao;
import net.masich.logserver.server.ui.dao.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<User> findAllUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return em.createQuery("from User where id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User findByEmail(String email) {
        return em.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User create(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

}
