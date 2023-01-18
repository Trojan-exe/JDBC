package com.sk.dynamicQueryGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextArea;

@SuppressWarnings("serial")
public class DynamicQueryFrame extends Frame implements ActionListener {
	Label l;
	TextArea ta;
	Button b;
	dynamicQueryGuiService dynamicQueryService = null;
	boolean status;
	
	public DynamicQueryFrame() {
		dynamicQueryService = new dynamicQueryGuiService();
		
		this.setVisible(true);
		this.setSize(500,500);
		this.setTitle("Dynamic SQL Query Frame");
		this.setLayout(new FlowLayout());
		this.setBackground(Color.DARK_GRAY);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		l = new Label("Query    ");
		ta = new TextArea(5, 50);
		b = new Button("EXECUTE");
		b.addActionListener(this);
		
		Font f = new Font("consolas", Font.BOLD, 20);
		l.setFont(f);
		ta.setFont(f);
		b.setFont(f);
		
		this.add(l);
		this.add(ta);
		this.add(b);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String query = ta.getText();
			status = dynamicQueryService.execute(query);
			repaint();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Font f = new Font("consolas", Font.BOLD, 20);
		g.setFont(f);
		if(status == true){
			ArrayList<String> data = dynamicQueryService.getData();
			g.drawString(data.get(0), 50, 250);
			int y = 290;
			for(int i = 1; i < data.size(); i++, y+=40)
				g.drawString(data.get(i), 50, y);
		}else {
			int rc = dynamicQueryService.getRowCount();
			g.drawString("Row Count : " + rc, 50, 500);
		}
	}
}
