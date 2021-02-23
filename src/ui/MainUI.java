package ui;

import bean.Account;
import bll.AccountService;
import bll.impl.AccountServicelmpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Set;

public class MainUI {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动加载成功");
            String uri = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=library";
            String user = "sa";
            String password = "123456";
            conn = DriverManager.getConnection(uri, user, password); //连接代码
            Statement st = conn.createStatement();
            String sql = "select * from book";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getObject(1)+""+rs.getObject(2));
            }
            System.out.println();
            ;
        } catch (Exception e) {
            System.out.println("错误");
            System.out.println(e);


            Scanner sc = new Scanner(System.in); // 接受键盘输入
            while (true) {
                System.out.println("请输入相应的数字进行操作：");
                System.out.println("按1，录入借阅信息");
                System.out.println("按2，显示所有借阅图书信息");
                System.out.println("按3，查询借阅信息");


                int choice = sc.nextInt();

                if (choice == 1) {
                    System.out.println("请输入图书编号");
                    String BookNo = sc.next();
                    System.out.println("请输入读者编号");
                    String readerNo = sc.next();
                    System.out.println("请输入借阅时间");
                    String loanTime = sc.next();
                    System.out.println("请输入归还时间");
                    String returnTime = sc.next();
                } else if (choice == 2) {
                    AccountService accountService = new AccountServicelmpl();
                    Set<Account>allAccount = accountService.getAllAccount();
                    for (Account account:allAccount){
                        System.out.println(account);
                    }

                }else if(choice==3){
                    System.out.println("输入相应都编号选择您要查询借阅信息都方式：");
                    System.out.println("按1，按图书编号查询");
                    System.out.println("按2，按读者编号查询");
                }


            }
        }
    }
}