package re.edu.business.dao.account;

import re.edu.business.dao.AppDao;
import re.edu.business.model.Account;

public interface AccountDao extends AppDao {
    Account logIn(String username, String password);

}
