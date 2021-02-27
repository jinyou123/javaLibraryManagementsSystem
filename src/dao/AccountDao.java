package dao;

import bean.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;


public interface AccountDao {
    void insertFlight(Account account, Connection con) throws SQLException;
    Set<Account>getAllAccount() throws  SQLException;
    Account getAccountAllReaderNo(String readerNo) throws SQLException; //所有的借阅记录
    Account getAccountByReaderNo(String readerNo);
    void updateAccount(Account account);

}
