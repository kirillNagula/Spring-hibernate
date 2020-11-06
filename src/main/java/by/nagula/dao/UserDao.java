package by.nagula.dao;

import by.nagula.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        System.out.println(user);
        User user1 = findById(user.getId());
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.createQuery("UPDATE User user SET user.name = :name ," +
                "user.login = :login ," +
                "user.password = :password " +
                "where user.id = :id").
                setParameter("id", user.getId()).
                setParameter("login", user.getLogin()).
                setParameter("password", user.getPassword()).
                setParameter("name", user.getName()).
                executeUpdate();
        currentSession.createQuery("update PhoneNumber  set number =: telephone where id =: id").
                setParameter("telephone", user.getNumber().getNumber()).
                setParameter("id", user1.getNumber().getId()).
                executeUpdate();
        currentSession.createQuery("update Address set city =:city," +
                "street =:street," +
                "number =:number " +
                "where id =:id").
                setParameter("city", user.getAddress().getCity()).
                setParameter("street", user.getAddress().getStreet()).
                setParameter("number", user.getAddress().getNumber()).
                setParameter("id", user1.getAddress().getId()).
                executeUpdate();
    }

    public User findById(long id){
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(User.class, id);
    }

    public void delete(long id){
        System.out.println(id);
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("delete User where id= :id").setParameter("id", id);
        System.out.println(query.executeUpdate());

    }
}
