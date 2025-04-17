package re.edu.business.dao.account;

import re.edu.business.dao.AppDao;

public interface AccountDao extends AppDao {
    boolean logIn(String username, String password);

}
