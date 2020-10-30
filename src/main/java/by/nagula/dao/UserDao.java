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
//        if (containsByLogin(login)){
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.createNativeQuery("SELECT * FROM users WHERE login = :login", User.class).
                    setParameter("login", login).getSingleResult();
//        }
//        return new User();
    }

//    private boolean containsByLogin(String login){
//        Session currentSession = sessionFactory.getCurrentSession();
//       return currentSession.createNativeQuery("SELECT * FROM users WHERE login = :login", User.class).
//                setParameter("login", login).getSingleResult() != null;
//    }
}
