package andycaptain.crud.dao;

import andycaptain.crud.model.User;

import java.util.List;


/**
 * Created by AndyCaptain on 19.08.2016.
 */
public interface UserDao {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public List<User> listUsers();
    public List<User> listUsers( int startRec , int linesByPage );
    public List<User> listUsersLike( String searchString, int startRec, int linesByPage );
    public boolean isUserExist( String searchString );

    public int countUsers();
    public int countUsers(String searchQuery);



}
