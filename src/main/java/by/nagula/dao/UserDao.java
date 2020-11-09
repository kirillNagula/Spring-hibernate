package by.nagula.dao;

import by.nagula.entity.User;

public interface UserDao {

    void save(User user);
    User findByLogin(String login);
    boolean contains(String login);
    User findById(long id);
    void update(User user);
    void delete(long id);

}
