package ui;

import bean.Account;
import bll.AccountService;
import bll.impl.AccountServicelmpl;

import java.sql.*;
import java.util.Scanner;
import java.util.Set;

public class MainUI {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String uri = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=library";
            String user = "sa";
            String password = "123456";
            conn = DriverManager.getConnection(uri, user, password); //连接代码
        } catch (Exception e) {
            e.printStackTrace();
        }

            Scanner sc = new Scanner(System.in); // 接受键盘输入
            while (true) {
                System.out.println("请输入相应的数字进行操作：");
                System.out.println("按1，录入借阅信息");
                System.out.println("按2，显示所有借阅图书信息");
                System.out.println("按3，查询借阅信息");

                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("请输入图书编号");
                    String bookNo = sc.next();

                    try {
                        Statement st = conn.createStatement(); //创建Statement对象--操作增删改查SQL语句
                        String sql = "select BookNo from book where BookNo=?" ;//执行一个SQL语句
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, bookNo);
                        ResultSet rs = pstmt.executeQuery();
                        /*String result = "";
                        while(rs.next()){
                            result = rs.getString(1);
                            System.out.println("查询结果：" + rs.getString(1));
                        }
                        System.out.println("判断条件："+ rs.next());*/
                        //判断是否有这本书
                        if( rs.next()==true){
                            System.out.println("请输入读者编号");
                            String readerNo = sc.next();
                            System.out.println("请输入借阅时间");
                            String loanTime = sc.next();
                            System.out.println("请输入归还时间");
                            String returnTime = sc.next();
                        }
                        else
                            System.out.println("没有这本书！");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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