package andycaptain.crud.service;

import andycaptain.crud.dao.UserQueryDao;
import andycaptain.crud.model.UserQuery;
import org.springframework.stereotype.Service;

/**
 * Created by AndyCaptain on 23.08.2016.
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private UserQueryDao userQueryDao;
    public void setUserQueryDao(UserQueryDao userQueryDao) {
        this.userQueryDao = userQueryDao;
    }

    @Override
    public void setQueryPattern(UserQuery query) {
        userQueryDao.setQueryPattern(query);
    }

    @Override
    public String getQuery() {
        return userQueryDao.getQuery();
    }
}
