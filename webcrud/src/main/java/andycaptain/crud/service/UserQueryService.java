package andycaptain.crud.service;

import andycaptain.crud.dao.UserQueryDao;
import andycaptain.crud.model.UserQuery;

/**
 * Created by AndyCaptain on 23.08.2016.
 */
public interface UserQueryService {
    void setQueryPattern(UserQuery query);
    String getQuery();

}
