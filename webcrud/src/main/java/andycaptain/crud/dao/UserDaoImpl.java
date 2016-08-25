package andycaptain.crud.dao;

import andycaptain.crud.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndyCaptain on 20.08.2016.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private final String QUERY_COUNT_ID="select count(id) ";
    private final String QUERY_LIKE = "from User u where u.name like :pattern";
    private final String QUERY_NAME_EQUAL = "from User u where u.name = :pattern";
    private final String QUERY_ALL  = "from User order by id desc";
    private final String QUERY_SORT = "from User order by :column :direction";

    private SessionFactory sessionFactory;
    private List<User> userList = new ArrayList<>();


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }


    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, new Integer(id));

        if(user!=null){
            session.delete(user);
        }
    }

    public User getUserById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, new Integer(id));

        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_ALL);

        List<User> userList = query.getResultList();

        return userList;
    }


    @SuppressWarnings("unchecked")
    public List<User> listUsers(int first, int maxResult) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_ALL);

        query.setFirstResult( first );
        query.setMaxResults(maxResult);

        List<User> userList = query.getResultList();

        return userList;
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsersLike(String searchString, int first, int maxResult) {
        Session session = this.sessionFactory.getCurrentSession();

        Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_LIKE);
        query.setParameter("pattern", "%"+searchString+"%");


        query.setFirstResult( first );
        query.setMaxResults(maxResult);

        List<User> userList = query.getResultList();

        return userList;
    }



    @SuppressWarnings("unchecked")
    public int countUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_COUNT_ID+QUERY_ALL);

        return ((Long)query.uniqueResult()).intValue();
    }

    @SuppressWarnings("unchecked")
    public int countUsers(String searchQuery) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_COUNT_ID+QUERY_LIKE);
        query.setParameter("pattern", "%"+searchQuery+"%");

        return ((Long)query.uniqueResult()).intValue();
    }


    @SuppressWarnings("unchecked")
    public boolean isUserExist(String searchString) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = this.sessionFactory.getCurrentSession().createQuery(QUERY_COUNT_ID+QUERY_NAME_EQUAL);
        query.setParameter("pattern", searchString);

        long id = ((Long)query.uniqueResult()).intValue();

        if ( id > 0 )
            return true;
        else
            return false;
    }
}
