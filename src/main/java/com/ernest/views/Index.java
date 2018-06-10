package com.ernest.views;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.FlowLayout;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Index extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField experience;
	private JTextField education;
	private JTextField personality;
	private JTextField skills;
	private JFileChooser fc;
	private JButton btnScreenForJob;
	private JButton btnChooseAFolder;
	private JLabel folderName;

	
	/**
	 * Create the frame.
	 */
	public Index() {
		setAlwaysOnTop(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 0, 185, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFiltrationType = new JLabel("Filtration Type");
		lblFiltrationType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFiltrationType.setBounds(0, 13, 185, 50);
		lblFiltrationType.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblFiltrationType);
		
		JRadioButton rdbtnByKeyWords = new JRadioButton("By Key Words");
		rdbtnByKeyWords.setBounds(8, 72, 107, 25);
		panel.add(rdbtnByKeyWords);
		buttonGroup.add(rdbtnByKeyWords);
		
		JRadioButton rdbtnByAGroup = new JRadioButton("By a group of words");
		rdbtnByAGroup.setBounds(5, 102, 143, 25);
		panel.add(rdbtnByAGroup);
		buttonGroup.add(rdbtnByAGroup);
		
		JRadioButton rdbtnByTfidfAlgorithm = new JRadioButton("By TF-IDF algorithm");
		rdbtnByTfidfAlgorithm.setBounds(5, 133, 143, 25);
		panel.add(rdbtnByTfidfAlgorithm);
		buttonGroup.add(rdbtnByTfidfAlgorithm);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 358, 592, 202);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblExperience = new JLabel("Experience");
		lblExperience.setBounds(24, 53, 113, 16);
		panel_1.add(lblExperience);
		
		JLabel lblPersonality = new JLabel("Personality");
		lblPersonality.setBounds(24, 121, 113, 19);
		panel_1.add(lblPersonality);
		
		JLabel lblSkills = new JLabel("Skills");
		lblSkills.setBounds(24, 164, 94, 25);
		panel_1.add(lblSkills);
		
		JLabel lblEducation = new JLabel("Education");
		lblEducation.setBounds(24, 82, 113, 16);
		panel_1.add(lblEducation);
		
		experience = new JTextField();
		experience.setBounds(187, 50, 382, 22);
		panel_1.add(experience);
		experience.setColumns(10);
		
		education = new JTextField();
		education.setBounds(187, 79, 382, 22);
		panel_1.add(education);
		education.setColumns(10);
		
		personality = new JTextField();
		personality.setBounds(187, 119, 382, 22);
		panel_1.add(personality);
		personality.setColumns(10);
		
		skills = new JTextField();
		skills.setBounds(187, 161, 382, 22);
		panel_1.add(skills);
		skills.setColumns(10);
		
		JLabel lblCreateJobDescription = new JLabel("Create Job Description");
		lblCreateJobDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateJobDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCreateJobDescription.setBounds(122, 13, 371, 27);
		panel_1.add(lblCreateJobDescription);
		
		btnScreenForJob = new JButton("Screen the selected folder for Job");
		btnScreenForJob.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		btnScreenForJob.setBounds(216, 611, 335, 85);
		contentPane.add(btnScreenForJob);
		
		btnChooseAFolder = new JButton("Choose a folder of CVs");
		btnChooseAFolder.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnChooseAFolder.setBounds(359, 49, 247, 77);
		contentPane.add(btnChooseAFolder);
		
		folderName = new JLabel("");
		folderName.setBounds(511, 186, 225, 51);
		contentPane.add(folderName);
		
		JLabel lblNameOfSelected = new JLabel("Name of selected Folder : ");
		lblNameOfSelected.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNameOfSelected.setBounds(263, 186, 247, 51);
		contentPane.add(lblNameOfSelected);
		
		
		
		
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	public void addBtnScreenForJobListener(ActionListener listenForFolderSelection) {
		btnScreenForJob.addActionListener(listenForFolderSelection);
	}

	public void addBtnChooseAFolderListener(ActionListener listenForFolderSelection) {
		btnChooseAFolder.addActionListener(listenForFolderSelection);
	}

	
	/**
	 * @return the folderName
	 */
	public JLabel getFolderName() {
		return folderName;
	}

	/**
	 * @param folderName the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName.setText(folderName);
	}

	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience.getText();
	}


	/**
	 * @return the education
	 */
	public String getEducation() {
		return education.getText();
	}

	

	/**
	 * @return the personality
	 */
	public String getPersonality() {
		return personality.getText();
	}

	

	/**
	 * @return the skills
	 */
	public String getSkills() {
		return skills.getText();
	}
}
