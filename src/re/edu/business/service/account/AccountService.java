package re.edu.business.service.account;

import re.edu.business.service.AppService;

public interface AccountService extends AppService {
    boolean logIn(String username, String password);
}
