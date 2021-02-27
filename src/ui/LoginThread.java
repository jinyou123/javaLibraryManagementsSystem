package ui;
import uti.MD5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
 /**
     * 登录线程
     */
    public class LoginThread extends Thread {
        private JFrame loginf;

        private JTextField t;

        public void run() {
            /*
             * 设置登录界面
             */
            loginf = new JFrame();
            loginf.setResizable(false);
            loginf.setLocation(300, 200);
            loginf.setSize(400, 150);
            loginf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginf.setTitle("图书管理系统" + " - 登录");

            t = new JTextField("Version " + "1.1.0" + "        By lyl");
            t.setHorizontalAlignment(JTextField.CENTER);
            t.setEditable(false);
            loginf.getContentPane().add(t, BorderLayout.SOUTH);

            JPanel loginp = new JPanel(new GridLayout(3, 2));
            loginf.getContentPane().add(loginp);

            JTextField t1 = new JTextField("登录名:");
            t1.setHorizontalAlignment(JTextField.CENTER);
            t1.setEditable(false);
            loginp.add(t1);

            final JTextField loginname = new JTextField("lyl");
            loginname.setHorizontalAlignment(JTextField.CENTER);
            loginp.add(loginname);

            JTextField t2 = new JTextField("密码:");
            t2.setHorizontalAlignment(JTextField.CENTER);
            t2.setEditable(false);
            loginp.add(t2);

            final JTextField loginPassword = new JTextField("ly123");
            loginPassword.setHorizontalAlignment(JTextField.CENTER);
            loginp.add(loginPassword);

            /*
             * 监听退出按钮(匿名内部类)
             */
            JButton b1 = new JButton("退  出");
            loginp.add(b1);
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            final JButton b2 = new JButton("登  录");
            loginp.add(b2);

            loginf.setVisible(true);

            /**
             * 监听器,监听"登录"Button的点击和TextField的回车
             */
            class ButtonListener implements ActionListener {
                private Socket s;

                public void actionPerformed(ActionEvent e) {
                    String username = loginname.getText();  //界面的登录名
                    String password = loginPassword.getText();
                    PreparedStatement pstmt = null;
                    String sql = "";
                    try {
                        Connection conn = null;
                        String uri = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=library";
                        String user = "sa";
                        String Password = "123456";
                        conn = DriverManager.getConnection(uri, user, Password); //连接代码
                        sql = "SELECT password FROM users WHERE username=?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, username);
                        ResultSet rs = pstmt.executeQuery();
                        if (rs.next()) {
                            String encodePassword = rs.getString("PASSWORD");
                            if (MD5.checkpassword(password, encodePassword)) {
                                System.out.println("登陆成功");
                            /*
                            隐藏登录界面
                            显示菜单窗口
                             */
                                loginf.setVisible(false);
                                MenuWindow libraryWindow = new MenuWindow(username);
                            } else {
                                System.out.println("登录失败");
                            }
                        }
                    } catch (SQLException ee) {
                        ee.printStackTrace();
                    } catch (NoSuchAlgorithmException ex) {
                        ex.printStackTrace();
                    } catch (UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                    } /*catch (UnknownHostException ex) {
                        ex.printStackTrace();
                    }*/
				/*
				1、根据用户去数据库把加密后的密码拿到
				SELECT password FROM users WHERE username='liwei';
				2、把登录界面输入的密码和数据库里加密后的进行比对（调用MD5类的checkpassword方法）
				 */
                }
            }
            ButtonListener bl = new ButtonListener();
            b2.addActionListener(bl);
            loginname.addActionListener(bl);
            loginPassword.addActionListener(bl);
        }
    }
