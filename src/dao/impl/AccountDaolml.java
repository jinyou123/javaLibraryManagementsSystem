package dao.impl;

import bean.Account;
import dao.AccountDao;

import java.sql.*;
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
    public Account getAccountAllReaderNo(String readerNo) throws SQLException {
        Connection conn = null;
        String uri = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=library";
        String user = "sa";
        String password = "123456";
        conn = DriverManager.getConnection(uri, user, password); //连接代码
        Statement st = conn.createStatement(); //创建Statement对象--操作增删改查SQL语句
        String sql = "select * from account";//执行一个SQL语句
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
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
