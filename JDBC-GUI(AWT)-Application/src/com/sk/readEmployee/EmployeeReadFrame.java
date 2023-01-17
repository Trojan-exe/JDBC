package com.sk.readEmployee;

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

@SuppressWarnings("serial")
public class EmployeeReadFrame extends Frame implements ActionListener{
	Label l1;
	TextField tf1;
	Button b;
	Employee emp = null;
	int eno;
	
	public EmployeeReadFrame() {
		this.setVisible(true);
		this.setSize(500,500);
		this.setTitle("Employee Read Frame");
		this.setLayout(new FlowLayout());
		this.setBackground(Color.DARK_GRAY);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		l1 = new Label("Employee ID    ");
		tf1 = new TextField(20);
		b = new Button("Search");
		b.addActionListener(this);
		
		Font f = new Font("consolas", Font.BOLD, 15);
		l1.setFont(f);
		tf1.setFont(f);
		b.setFont(f);
		
		this.add(l1);
		this.add(tf1);
		this.add(b);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			eno = Integer.parseInt(tf1.getText());
			EmployeeReadService  empService = new EmployeeReadService();
			emp = empService.read(eno);
			repaint();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Font f = new Font("consolas", Font.BOLD, 15);
		g.setFont(f);
		if(emp == null)
			g.drawString("Employee with ID " + eno + " does not exist!!!", 50, 350);
		else
		{
			g.drawString("Employee ID : " + emp.getENO(), 60, 150);
			g.drawString("Employee Name : " + emp.getENAME(), 60, 170);
			g.drawString("Employee Salary : " + emp.getESAL(), 60, 190);
			g.drawString("Employee Address : " + emp.getEADDR(), 60, 210);
		}
	}
}
