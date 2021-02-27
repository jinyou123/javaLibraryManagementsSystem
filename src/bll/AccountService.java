package bll;

import bean.Account;

import java.sql.Connection;
import java.util.Set;

public interface AccountService {
    void insertAccount(Account account); //插入图书借阅记录
    Set<Account> getAllAccount();
    Account getAccountAllReaderNo(String readerNo); //所有的借阅记录
    Account getAccountByReaderNo(String readerNo);//一个人多大借阅记录
    void updateAccount(Account account);//更新图书借阅记录
}
