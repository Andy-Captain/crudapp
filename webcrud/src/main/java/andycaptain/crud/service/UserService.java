package andycaptain.crud.service;

import andycaptain.crud.model.User;

import java.util.List;

/**
 * Created by AndyCaptain on 20.08.2016.
 */
public interface UserService  {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public List<User> listUsers();

    public List<User> listUsers(int startRec, int linesPerPage);

    public List<User> listUsers(String query , int startRec, int linesPerPage);

    public int countUsers();

    public int countUsers(String query);

    public boolean isUserExist(String userName );
}
