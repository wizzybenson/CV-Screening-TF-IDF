package com.ernest.models;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Folder {
private String path;
private String folderName;
private List<CV> CVs;
private int NumberOfDocument;
private Map<String,Double> idf;
private List<CV> filteredCV;
private FilterType filterBy;
private Map<String,Integer> SumTermCount;


/**
 * 
 */
public Folder() {
}
/**
 * @param path
 * @param folderName
 */
public Folder(String path, String folderName) {
	this.path = path;
	this.folderName = folderName;
}
/**
 * @return the path
 */
public String getPath() {
	return path;
}
/**
 * @param path the path to set
 */
public void setPath(String path) {
	this.path = path;
}
/**
 * @return the folderName
 */
public String getFolderName() {
	return folderName;
}
/**
 * @param folderName the folderName to set
 */
public void setFolderName(String folderName) {
	this.folderName = folderName;
}
/**
 * @return the cVs
 */
public List<CV> getCVs() {
	return CVs;
}
/**
 * @param cVs the cVs to set
 */
public void setCVs(List<CV> cVs) {
	CVs = cVs;
}

/**
 * @return the filteredCVs
 */
public List<CV> filter() {
	return filteredCV;
}
/**
 * @return the filterBy
 */
public FilterType getFilterBy() {
	return filterBy;
}
/**
 * @param filterBy the filterBy to set
 */
public void setFilterBy(FilterType filterBy) {
	this.filterBy = filterBy;
}


/**
 * @return the sumTermCount
 */
public Map<String, Integer> getSumTermCount() {
	return SumTermCount;
}
/**
 * @param sumTermCount the sumTermCount to set
 */
public void setSumTermCount(Job job) {
	Map<String, Integer> sumTermCount = new HashMap<>();
	SumTermCount = sumTermCount;
}

public void idf(Job job) {
	if(idf!=null)return;
	Map<String,Double> idfMap = new HashMap<>();
	for(String term : job.getQuery()) {
		double idf = 0.0;
		int NumberOfCvWithTerm = 0;
		for(CV cv : this.getCVs()) {
			
			
			if(cv.getJobQueryTerms().get(term).getTermCount()>0) {
				NumberOfCvWithTerm++;
			}
			System.out.println(cv.getJobQueryTerms().get(term).getTermCount());
		}
		idf =1+ Math.log10((double)this.NumberOfDocument/(1+NumberOfCvWithTerm));
		System.out.println(" idf = "+idf+ " NumberDoc = "+this.NumberOfDocument);
		idfMap.put(term, idf);
	}
	
	
	setIdf(idfMap);
	
	
}
/**
 * @return the numberOfDocument
 */
public int getNumberOfDocument() {
	return NumberOfDocument;
}
/**
 * @param numberOfDocument the numberOfDocument to set
 */
public void setNumberOfDocument(int numberOfDocument) {
	NumberOfDocument = numberOfDocument;
}
/**
 * @return the idf
 */
public Map<String, Double> getIdf() {
	return idf;
}
/**
 * @param idf the idf to set
 */
private void setIdf(Map<String, Double> idf) {
	this.idf = idf;
}

/**
 * 
 */
public void buildCVs(Job job) {
	if(this.path == null) {
		return;
	}
	List<CV> cVs = new ArrayList<>();
	File folderDir = new File(this.path);
	File[] files = folderDir.listFiles();
	int len = files.length;
	setNumberOfDocument(len);
	for(File file : files) {
		CV cv = new CV(file.getPath(),file.getName());
		cv.setFolder(this);
		cv.initJobQueryTerms(job);
		cv.extractTexts();
		cv.termCount(job);
		cVs.add(cv);
		
	}
	
	
	setCVs(cVs);
}


}
