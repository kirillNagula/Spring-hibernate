package by.nagula.service;

import by.nagula.dao.UserDao;
import by.nagula.entity.User;
import by.nagula.exception.UserAlreadyExistException;
import by.nagula.exception.UserNotExistInDbException;
import by.nagula.exception.WrongLginException;
import by.nagula.exception.WrongPasswordException;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user){
        if (userDao.contains(user.getLogin())) {
            throw new UserAlreadyExistException("This login not unique");
        } else {
            userDao.save(user);
        }
    }

    public User find(String login){
        if (userDao.contains(login)){
            return userDao.findByLogin(login);
        } else {
            throw new UserNotExistInDbException("No user with this login");
        }

    }

    public boolean checkPassword(User user, String password){
        if (user.getPassword().equals(password)){
            return true;
        } else {
            throw new WrongPasswordException("Wrong password");
        }
    }

    public void updateUser(User user){
        if (userDao.contains(user.getLogin())){
            if (user.getId() == userDao.findByLogin(user.getLogin()).getId()){
                userDao.update(user);
            } else{
                throw new WrongLginException("You can't use this login");
            }
        } else {
            userDao.update(user);
        }
    }

    public void deleteUser(long id){
        userDao.delete(id);
    }
}
