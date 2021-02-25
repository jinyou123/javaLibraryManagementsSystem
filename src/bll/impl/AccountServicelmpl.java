package bll.impl;

import bean.Account;
import bll.AccountService;
import dao.AccountDao;
import dao.LogDao;
import dao.impl.AccountDaolml;
import dao.impl.LogDaolmpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

public class AccountServicelmpl implements AccountService {

   AccountDao accountDao;
   LogDao logDao;

    public AccountServicelmpl(){
        accountDao = new AccountDaolml();
        logDao = new LogDaolmpl();
    }


    public void insertAccount(Account account) {
        Connection conn = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String uri="jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=library";
            String user="sa";
            String password="123456";
            conn=DriverManager.getConnection(uri,user,password); //连接代码

            Statement st = conn.createStatement();
            String sql = "select * from book";
            ResultSet rs = st.executeQuery(sql);
            System.out.println(rs);


            conn.setAutoCommit(false);

        }
        catch(Exception e){
            System.out.println(e);
        }


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
