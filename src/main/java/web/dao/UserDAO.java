package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {

    public List<User> listUsers();

    public void saveUser(User user);

    public void addUser(User user);

    public User getUserById(long id);
    public void removeById(long id);

}
