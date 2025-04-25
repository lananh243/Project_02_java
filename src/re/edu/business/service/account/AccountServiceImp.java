package re.edu.business.service.account;

import re.edu.business.dao.account.AccountDao;
import re.edu.business.dao.account.AccountDaoImp;
import re.edu.business.model.Account;

public class AccountServiceImp implements AccountService {
    public static Account userLogin;
    private final AccountDao accountDao;

    public AccountServiceImp() {
        accountDao = new AccountDaoImp();
    }

    @Override
    public boolean logIn(String username, String password) {
        Account account = accountDao.logIn(username, password);
        if (account != null) {
            userLogin = account;
            return true;
        }
        return false;
    }
}
