package re.edu.business.service.account;

import re.edu.business.dao.account.AccountDao;
import re.edu.business.dao.account.AccountDaoImp;

public class AccountServiceImp implements AccountService {
    private final AccountDao accountDao;

    public AccountServiceImp() {
        accountDao = new AccountDaoImp();
    }

    @Override
    public boolean logIn(String username, String password) {
        return accountDao.logIn(username, password);
    }
}
