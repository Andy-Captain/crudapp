package andycaptain.crud.dao;

import andycaptain.crud.model.UserQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by AndyCaptain on 23.08.2016.
 */
@Repository
public class UserQueryDaoImpl implements UserQueryDao {
    private String resultQuery="";


    @Override
    public void setQueryPattern(UserQuery query) {
        resultQuery=query.getSearchString();
    }

    @Override
    public String getQuery() {
        return resultQuery;
    }
}
