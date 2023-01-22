package com.sk.scrollableResultsetGUIApplication1;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class PlayerFrame extends Frame implements ActionListener{
	private Button firstButton, lastButton, nextButton, prevButton;
	private Employee emp = null;
	PlayerService ps = null;
	
	public PlayerFrame() {
		this.setVisible(true);
		this.setSize(600, 600);
		this.setBackground(Color.DARK_GRAY);
		this.setTitle("Scrollable Resultset Player");
		this.setLayout(new FlowLayout());
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		firstButton = new Button("First");
		lastButton = new Button("Last");
		nextButton = new Button("Next");
		prevButton = new Button("Previous");
		
		Font f = new Font("consolas", Font.BOLD, 20);
		firstButton.setFont(f);
		lastButton.setFont(f);
		nextButton.setFont(f);
		prevButton.setFont(f);
		
		firstButton.addActionListener(this);
		lastButton.addActionListener(this);
		nextButton.addActionListener(this);
		prevButton.addActionListener(this);
		
		this.add(firstButton);
		this.add(nextButton);
		this.add(prevButton);
		this.add(lastButton);
		
		ps = new PlayerService();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String label = e.getActionCommand();
			emp = ps.getEmployeeDetails(label);
			repaint();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Font f = new Font("conolas", Font.BOLD, 20);
		g.setFont(f);
		if(emp == null) {
			g.drawString("No Record Found", 50, 150);
		}else {
			g.drawString("Employee Details:", 50, 150);
			g.drawString("Employee No : " + emp.getENO(), 60, 200);
			g.drawString("Employee Name : " + emp.getENAME(), 60, 230);
			g.drawString("Employee Salary : " + emp.getESAL(), 60, 260);
			g.drawString("Employee Address : " + emp.getEADDR(), 60, 290);
		}
	}
}
