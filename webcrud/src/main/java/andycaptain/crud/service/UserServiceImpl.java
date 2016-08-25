package andycaptain.crud.service;

import andycaptain.crud.dao.UserDao;
import andycaptain.crud.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AndyCaptain on 20.08.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);

    }

    @Transactional
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }
    @Transactional
    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }
    @Transactional
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }
    @Transactional
    public List<User> listUsers() {
        return this.userDao.listUsers();
    }

    @Transactional
    public List<User> listUsers(int firstRec, int maxRecs) {
        return this.userDao.listUsers(firstRec,maxRecs);
    }

    @Transactional
    public List<User> listUsers(String searchRec, int firstRec, int maxRecs) {
        return this.userDao.listUsersLike(searchRec, firstRec,maxRecs);
    }

    @Transactional
    public int countUsers() {
        return this.userDao.countUsers();
    }
    @Transactional
    public int countUsers(String searchQuery) {
        return this.userDao.countUsers(searchQuery);
    }

    @Transactional
    public boolean isUserExist(String userName) {
        return this.userDao.isUserExist(userName);
    }
}
