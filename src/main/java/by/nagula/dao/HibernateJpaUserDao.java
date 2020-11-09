package by.nagula.dao;

import by.nagula.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository(value = "hibJpa")
@Transactional
public class HibernateJpaUserDao implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }


    @Override
    public User findByLogin(String login) {
        return entityManager.createNamedQuery("findByLogin",User.class).
                setParameter("login", login).
                getSingleResult();
    }

    @Override
    public boolean contains(String login) {
        return entityManager.createNamedQuery("findByLogin",User.class).
                setParameter("login", login).
                getResultList().size() > 0;
    }

    @Override
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        User user1 = findById(user.getId());
        entityManager.createQuery("UPDATE User user SET user.name = :name ," +
                "user.login = :login ," +
                "user.password = :password " +
                "where user.id = :id").
                setParameter("id", user.getId()).
                setParameter("login", user.getLogin()).
                setParameter("password", user.getPassword()).
                setParameter("name", user.getName()).
                executeUpdate();
        entityManager.createQuery("update PhoneNumber  set number =: telephone where id =: id").
                setParameter("telephone", user.getNumber().getNumber()).
                setParameter("id", user1.getNumber().getId()).
                executeUpdate();
        entityManager.createQuery("update Address set city =:city," +
                "street =:street," +
                "number =:number " +
                "where id =:id").
                setParameter("city", user.getAddress().getCity()).
                setParameter("street", user.getAddress().getStreet()).
                setParameter("number", user.getAddress().getNumber()).
                setParameter("id", user1.getAddress().getId()).
                executeUpdate();
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }
}
