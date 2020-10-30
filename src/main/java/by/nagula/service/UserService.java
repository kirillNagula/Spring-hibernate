package by.nagula.service;

import by.nagula.dao.UserDao;
import by.nagula.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void save(User user){
        userDao.save(user);
    }

    public User find(String login){
        return userDao.findByLogin(login);
    }
}
