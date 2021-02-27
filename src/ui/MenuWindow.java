package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.*;
import java.sql.*;

public class MenuWindow {
        private String name;
        JComboBox cb;//组合框
        private JFrame f;
        JTextArea ta;
        private JTextField tf;
        DatagramSocket ds;

        public MenuWindow(String name/*, DatagramSocket ds*/) {
            this.ds = ds;
            this.name = name;
            f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(600, 400);
            f.setTitle("图书管理系统菜单");
            f.setLocation(300, 200);
            ta = new JTextArea();
            JScrollPane sp = new JScrollPane(ta);
            ta.setEditable(false);
            tf = new JTextField();
            tf.addKeyListener(
                    new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {

                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                                String messageTo= (String) cb.getSelectedItem();
                            }
                        }

                        @Override
                        public void keyReleased(KeyEvent e) {

                        }
                    }
            );
            cb = new JComboBox();
            cb.addItem("All");
            JButton jb = new JButton("传文件");
            JPanel pl = new JPanel(new BorderLayout());
            pl.add(cb);
            pl.add(jb, BorderLayout.WEST);
            JPanel p = new JPanel(new BorderLayout());
            p.add(pl, BorderLayout.WEST);
            p.add(tf);
            f.getContentPane().add(p, BorderLayout.SOUTH);
            f.getContentPane().add(sp);
            f.setVisible(true);

            //开启收消息线程
            GetMessage getMessage = new GetMessage(this);
            getMessage.start();

        /*
        提示XXX进入聊天室
         */
            showXXXIntoChatRoom();

        /*
        提示XXX正在聊天室
         */
            showXXXInChatRoom();
        }
        public void sendAll() {
            String uri = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=library";
            String user = "sa";
            String Password = "123456";
            PreparedStatement pstmt = null;
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(uri,user, Password);
                String sql = "SELECT username,ip,port FROM users WHERE status='online'";
                pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("USERNAME");
                    String ip = rs.getString("IP");
                    int port = rs.getInt("PORT");
                    System.out.println(ip);
                    System.out.println(port);
                    byte[] ipB = new byte[4];

                    String ips[] = ip.split("\\.");
                    for (int i = 0; i < ips.length; i++) {
                        ipB[i] = (byte) Integer.parseInt(ips[i]);
                    }
                    if (!username.equals(name)) {
                        String message = tf.getText();
                        byte[] m = message.getBytes();
                        DatagramPacket dp = new DatagramPacket(m, m.length);
                        dp.setAddress(InetAddress.getByAddress(ipB));
                        dp.setPort(port);
                        DatagramSocket ds = new DatagramSocket();
                        ds.send(dp);//投递
                    }
                }
            } catch (SQLException | UnknownHostException | SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void showXXXInChatRoom() {
            String uri = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=library";
            String user = "sa";
            String password = "123456";
            PreparedStatement pstmt = null;
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(uri, user, password);
                String sql = "SELECT username,ip,port FROM users WHERE status='online' AND username!=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,name);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("USERNAME");
                    String message = username + "正在聊天室";
                    ta.append(message);
                    cb.addItem(username);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void showXXXIntoChatRoom() {
            String uri = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=library";
            String user = "sa";
            String password = "123456";
            PreparedStatement pstmt = null;
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(uri, user, password);
                String sql = "SELECT username,ip,port FROM users WHERE status='online'";
                pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("USERNAME");
                    String ip = rs.getString("IP");
                    int port = rs.getInt("PORT");
                    System.out.println(ip);
                    System.out.println(port);
                    byte[] ipB = new byte[4];

                    String ips[] = ip.split("\\.");
                    for (int i = 0; i < ips.length; i++) {
                        ipB[i] = (byte) Integer.parseInt(ips[i]);
                    }
                    if (!username.equals(name)) {
                        String message = name + "进入了聊天室";
                        byte[] m = message.getBytes();
                        DatagramPacket dp = new DatagramPacket(m, m.length);
                        dp.setAddress(InetAddress.getByAddress(ipB));
                        dp.setPort(port);
                        DatagramSocket ds = new DatagramSocket();
                        ds.send(dp);//投递
                    }
                }
            } catch (SQLException | UnknownHostException | SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
