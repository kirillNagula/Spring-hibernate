package by.nagula.dao;

import by.nagula.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;


    public void save(User user){
        sessionFactory.getCurrentSession().save(user);
    }

    public User findByLogin(String login){
        Session currentSession = sessionFactory.getCurrentSession();
        return (User) currentSession.createQuery("from User where login = :login").
                setParameter("login", login).
                getSingleResult();
    }

    public boolean contains(String login){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User where login =:login").
                setParameter("login", login).getResultList().size() > 0;
    }

    public void update(User user){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.createQuery("UPDATE User SET name = :name ," +
                "login = :login ," +
                "password = :password ," +
                "number.number = :telephone ," +
                "address.city = :city ," +
                "address.street = :street ," +
                "address.number = :number " +
                "where id = :id").
                setParameter("id", user.getId()).
                setParameter("login", user.getLogin()).
                setParameter("password", user.getPassword()).
                setParameter("name", user.getName()).
                setParameter("telephone", user.getNumber().getNumber()).
                setParameter("city", user.getAddress().getCity()).
                setParameter("street", user.getAddress().getStreet()).
                setParameter("number", user.getAddress().getNumber());
    }

    public User containsById(long id){
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(User.class, id);
    }

    public void delete(long id){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.createQuery("delete User where id= :id").setParameter("id", id);

    }
}
