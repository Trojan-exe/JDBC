package com.sk.createEmployee;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class EmployeeFrame extends Frame implements ActionListener {
	Label l1, l2, l3, l4;
	TextField tf1, tf2, tf3, tf4;
	Button b;
	String status = "";
	
	
	
	public EmployeeFrame() {
		this.setVisible(true);
		this.setSize(500,500);
		this.setTitle("Employee Create Frame");
		this.setLayout(new FlowLayout());
		this.setBackground(Color.DARK_GRAY);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		l1 = new Label("Employee Number    ");
		l2 = new Label("Employee Name      ");
		l3 = new Label("Employee Salary    ");
		l4 = new Label("Employee Address   ");
		
		tf1 = new TextField(20);
		tf2 = new TextField(20);
		tf3 = new TextField(20);
		tf4 = new TextField(20);
		
		b = new Button("ADD");
		b.addActionListener(this);
		
		Font f = new Font("consolas", Font.BOLD, 15);
		l1.setFont(f);
		l2.setFont(f);
		l3.setFont(f);
		l4.setFont(f);
		
		tf1.setFont(f);
		tf2.setFont(f);
		tf3.setFont(f);
		tf4.setFont(f);
		
		b.setFont(f);
		
		this.add(l1);
		this.add(tf1);
		this.add(l2);
		this.add(tf2);
		this.add(l3);
		this.add(tf3);
		this.add(l4);
		this.add(tf4);
		this.add(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int eno = Integer.parseInt(tf1.getText());
			String ename = tf2.getText();
			float esal = Float.parseFloat(tf3.getText());
			String eaddr = tf4.getText();
			
			EmployeeService empService = new EmployeeService();
			status = empService.add(eno, ename, esal, eaddr);
			repaint();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Font f = new Font("consolas", Font.BOLD, 15);
		g.setFont(f);
		g.drawString("Status : " + status, 50, 350);
	}

}
