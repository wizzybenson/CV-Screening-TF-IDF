package com.ernest.controllers;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ernest.models.CV;
import com.ernest.models.Folder;
import com.ernest.models.Job;
import com.ernest.views.Index;
import com.ernest.views.Ranked;

public class JobController {

	private Job job;
	private Index view;
	
	public JobController(Job job,Index view) {
		this.job = job;
		this.view = view;
		this.view.addBtnChooseAFolderListener(new ChooseAFolderButtonListener());
		this.view.addBtnScreenForJobListener(new ScreenForJobListener());
	}
	
	
	class ChooseAFolderButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int selectFile=fc.showOpenDialog(JobController.this.view);
			 if(selectFile==JFileChooser.APPROVE_OPTION){    
			        File f=fc.getSelectedFile();    
			        String folderPath=f.getPath(); 
			        String folderName = f.getName();
			        Folder folder = new Folder(folderPath,folderName);
			        JobController.this.job.setFolder(folder);
			        JobController.this.view.setFolderName(folderName);
			        System.out.println(f.getName());
		        }
			       
			
			 }
		
		}
	
	
	class ScreenForJobListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			String skills = JobController.this.view.getSkills().trim();
			String personality = JobController.this.view.getPersonality().trim();
			String education = JobController.this.view.getEducation().trim();
			String experience = JobController.this.view.getExperience().trim();
			boolean isValid = true;
			if(skills.isEmpty() && personality.isEmpty() && education.isEmpty() && experience.isEmpty()) {
				isValid = false;
			}
			if(JobController.this.job.getFolder()==null || !isValid) {
				JobController.this.view.displayErrorMessage("No folder selected or job description is empty");
				return;
			}
			//System.out.println(" folder = "+JobController.this.job.getFolder());
			
			List<String> query = new ArrayList<>();
			if(!skills.isEmpty()) query.addAll(new ArrayList<String>(Arrays.asList(skills.split(" "))));
			if(!personality.isEmpty()) query.addAll(new ArrayList<String>(Arrays.asList(personality.split(" "))));
			if(!education.isEmpty()) query.addAll(new ArrayList<String>(Arrays.asList(education.split(" "))));
			if(!experience.isEmpty()) query.addAll(new ArrayList<String>(Arrays.asList(experience.split(" "))));
			
			JobController.this.job.setQuery(query);
			//System.out.println(JobController.this.job.getQuery());
			
			JobController.this.job.setEducation(education);
			JobController.this.job.setExperience(experience);
			JobController.this.job.setPersonality(personality);
			JobController.this.job.setSkills(skills);
			
			List<CV> rankedCVs = JobController.this.job.rank().getRankedCVs();
			
			
			
	        DefaultListModel<String> listModel = new DefaultListModel<>(); 
	        for(CV cv : rankedCVs) {
	        	listModel.addElement(cv.getPath());
	        }
	          Ranked f= new Ranked(listModel); 
	          f.addListOptionClickListener(JobController.this.new ListOptionListener());
	          f.setVisible(true);
	          
			
			
			
		}
	}
	
	class ListOptionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				
				JList source = (JList)e.getSource();
		    	
		       System.out.println(source.getSelectedValue().toString().trim());
		       if (Desktop.isDesktopSupported()) {
			    try {
			        File myFile = new File(source.getSelectedValue().toString().trim());
			        Desktop.getDesktop().open(myFile);
			    } catch (IOException ex) {
			        // no application registered for PDFs
			    }
			}
				return;
			}
			
			
			
		}
		
	}
}
