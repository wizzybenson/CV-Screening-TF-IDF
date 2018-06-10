package com.ernest.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

public class Ranked extends JFrame {

	private JPanel contentPane;
	private JList jList;

	
	public Ranked(DefaultListModel<String> listModel) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//contentPane.add(jList);
		setContentPane(contentPane);
		
		jList = new JList(listModel);
		contentPane.add(jList, BorderLayout.NORTH);
	}
	
	public void addListOptionClickListener(ListSelectionListener listener) {
		jList.addListSelectionListener(listener);
		
	}
	
	
	/**
	 * @return the jList
	 */
	public JList getjList() {
		return jList;
	}

	/**
	 * @param jList the jList to set
	 */
	public void setjList(JList jList) {
		this.jList = jList;
	}

	/**
	 * @return the contentPane
	 */
	public JPanel getMyContentPane() {
		return contentPane;
	}

	
	

}
