package com.ernest;

import java.awt.EventQueue;

import com.ernest.controllers.JobController;
import com.ernest.models.Job;
import com.ernest.views.Index;


public class App 
{
    public static void main( String[] args )
    {
    	Job job = new Job();
    	Index view = new Index();
    	JobController jobController = new JobController(job,view);
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
