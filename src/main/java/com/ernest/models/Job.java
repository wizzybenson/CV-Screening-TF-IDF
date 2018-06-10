package com.ernest.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Job {
	private String description;
	private String education;
	private String skills;
	private String personality;
	private String experience;
	private Folder folder;
	private Set<String> Query;
	private List<CV> rankedCVs;
	
	
	/**
	 * 
	 */
	public Job() {
		
	}
	/**
	 * @param description
	 * @param education
	 * @param skills
	 * @param personality
	 * @param experience
	 */
	public Job(String description, String education, String skills, String personality, String experience) {
		
		this.description = description;
		this.education = education;
		this.skills = skills;
		this.personality = personality;
		this.experience = experience;
	}
	/**
	 * @return the folder
	 */
	public Folder getFolder() {
		return folder;
	}
	/**
	 * @param folder the folder to set
	 */
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}
	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}
	/**
	 * @return the skills
	 */
	public String getSkills() {
		return skills;
	}
	/**
	 * @param skills the skills to set
	 */
	public void setSkills(String skills) {
		this.skills = skills;
	}
	/**
	 * @return the personality
	 */
	public String getPersonality() {
		return personality;
	}
	/**
	 * @param personality the personality to set
	 */
	public void setPersonality(String personality) {
		this.personality = personality;
	}
	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	

	/**
	 * @return the ranked CVs
	 */
	public Job rank() {
		this.folder.buildCVs(this);
		for(CV cv : this.folder.getCVs()) {
			cv.CalculateScore(this);
			System.out.println(cv.getScore());
		}
		rankedCVs = new ArrayList<CV>(this.folder.getCVs()); 
		Collections.sort(rankedCVs);
		setRankedCVs(rankedCVs);
		return this;
		
	}
	
	
	
	/**
	 * @return the ranked
	 */
	public List<CV> getRankedCVs() {
		
		return rankedCVs;
	}
	/**
	 * @param ranked the ranked to set
	 */
	private void setRankedCVs(List<CV> rankedcv) {
		this.rankedCVs = rankedcv;
	}
	/**
	 * @return the query
	 */
	public Set<String> getQuery() {
		
		return Query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(List<String> query) {
		Query = new HashSet<String>(query);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Job [description=" + description + ", education=" + education + ", skills=" + skills + ", personality="
				+ personality + ", experience=" + experience + "]";
	}
	
	
	

}
