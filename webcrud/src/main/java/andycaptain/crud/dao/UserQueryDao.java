package andycaptain.crud.dao;

import andycaptain.crud.model.UserQuery;

/**
 * Created by AndyCaptain on 23.08.2016.
 */
public interface UserQueryDao {
    void setQueryPattern(UserQuery query);
    String getQuery();

}
