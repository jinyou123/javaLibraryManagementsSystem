package bll;

import bean.Account;

import java.sql.Connection;
import java.util.Set;

public interface AccountService {
    void insertFlight(Account account);
    Set<Account> getAllAccount();
    Account getAccountByBookNo(String bookNo);
    Account getAccountByReaderNo(String readerNo);
    void updateAccount(Account account);
}
