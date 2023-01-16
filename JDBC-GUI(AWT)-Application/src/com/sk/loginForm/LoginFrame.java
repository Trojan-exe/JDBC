package com.sk.loginForm;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends Frame implements ActionListener {
	Label l1, l2;
	TextField tf1, tf2;
	Button bt;
	String status = "";
	
	public LoginFrame() {
		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("Login Frame");
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new FlowLayout());
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		l1 = new Label("User Name  ");
		l2 = new Label("Password   ");
		tf1 = new TextField(20);
		tf2 = new TextField(20);
		tf2.setEchoChar('*');
		bt = new Button("Login");
		bt.addActionListener(this);
		
		Font f = new Font("consolas", Font.BOLD, 15);
		l1.setFont(f);
		l1.setForeground(Color.WHITE);
		l2.setFont(f);
		l2.setForeground(Color.WHITE);
		tf1.setFont(f);
		tf1.setForeground(Color.BLACK);
		tf2.setFont(f);
		tf2.setForeground(Color.BLACK);
		bt.setFont(f);
		
		
		this.add(l1);
		this.add(tf1);
		this.add(l2);
		this.add(tf2);
		this.add(bt);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String uname = tf1.getText();
		String upwd = tf2.getText();
		UserService userService = new UserService();
		status = userService.checkLogin(uname, upwd);
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		Font f = new Font("consolas", Font.BOLD, 25);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("Status : " + status, 50, 250);
	}

}
