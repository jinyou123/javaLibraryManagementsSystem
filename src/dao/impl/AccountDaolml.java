package dao.impl;

import bean.Account;
import dao.AccountDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class AccountDaolml implements AccountDao  {
    @Override
    public void insertFlight(Account account, Connection conn) throws SQLException {

        String  sql="INSERT INTO account VALUES(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,account.getBookNo());
        ps.setString(2,account.getReaderNo());
        ps.setString(3,account.getLoanTime());
        ps.setString(4,account.getReturnTime());
        ps.executeUpdate();

    }

    @Override
    public Set<Account> getAllAccount() {
        return null;
    }

    @Override
    public Account getAccountByBookNo(String bookNo) {
        return null;
    }

    @Override
    public Account getAccountByReaderNo(String readerNo) {
        return null;
    }

    @Override
    public void updateAccount(Account account) {

    }
}
