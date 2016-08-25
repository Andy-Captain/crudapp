package andycaptain.crud.model;

import javax.persistence.Entity;

/**
 * Created by AndyCaptain on 23.08.2016.
 */

public class UserQuery {
    private String searchString;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
