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
            JPanel pl = new JPanel(new BorderLayout());
            pl.add(cb);
            JPanel p = new JPanel(new BorderLayout());
            p.add(pl, BorderLayout.WEST);
            p.add(tf);
            f.getContentPane().add(p, BorderLayout.SOUTH);
            f.getContentPane().add(sp);
            f.setVisible(true);

        }
}

