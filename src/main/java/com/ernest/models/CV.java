package com.ernest.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class CV implements Comparable<CV> {
private String fileName;
private List<String> words;
private String path;
private double score;
private Folder folder;
private Map<String,JobTerm> JobQueryTerms;
/**
 * @param fileName
 * @param path
 */
public CV(String path, String fileName) {
	setFileName(fileName);
	setPath(path);
}

/**
 * inner class to hold statistics on each job term.
 */
class JobTerm {
	private  int termCount=0;
	private  double tf=0.0;
	private  double idf=0.0;
	private  double tf_idf=0.0;
	
	
	/**
	 * 
	 */
	public JobTerm() {
	}
	/**
	 * @return the termCount
	 */
	public int getTermCount() {
		return termCount;
	}
	/**
	 * @param termCount the termCount to set
	 */
	public void setTermCount(int termCount) {
		this.termCount = termCount;
	}
	/**
	 * @return the tf
	 */
	public double getTf() {
		return tf;
	}
	/**
	 * @param tf the tf to set
	 */
	public void setTf(double tf) {
		this.tf = tf;
	}
	/**
	 * @return the idf
	 */
	public double getIdf() {
		return idf;
	}
	/**
	 * @param idf the idf to set
	 */
	public void setIdf(double idf) {
		this.idf = idf;
	}
	/**
	 * @return the tf_idf
	 */
	public double getTf_idf() {
		return tf_idf;
	}
	/**
	 * @param tf_idf the tf_idf to set
	 */
	public void setTf_idf(double tf_idf) {
		this.tf_idf = tf_idf;
	}
	
}


/**
 * @return the fileName
 */
public String getFileName() {
	return fileName;
}


/**
 * @param fileName the fileName to set
 */
public void setFileName(String fileName) {
	this.fileName = fileName;
}


/**
 * @return the words
 */
public List<String> getWords() {
	
	return words;
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
 * @param words the words to set
 */
public void setWords(List<String> words) {
	
	this.words = words;
	
}


/**
 * @return the totalWordsInCV
 */
public int getTotalWordsInCV() {
	return words.size();
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
 * @return the score
 */
public double getScore() {
	return score;
}


/**
 * @param score the score to set
 */
public void setScore(double score) {
	this.score = score;
}

/**
 * @param calc the score to set
 */
public void CalculateScore(Job job) {
	tf(job);
	idf(job);
	tf_idf(job);
	double score = 0.0;
	for(Map.Entry<String,JobTerm> entry : JobQueryTerms.entrySet()) {
		score+=entry.getValue().tf_idf;
	}
	setScore(score);
}

public void initJobQueryTerms(Job job) {
	JobQueryTerms = new HashMap<String,JobTerm>();
	for(String term : job.getQuery()) {
		
		JobQueryTerms.put(term, this.new JobTerm());
		
	}
}

public void termCount(Job job) {
	
	for(String term : job.getQuery()) {
		
		int counter = 0;
		
		for(String word : words) {
			if(word.toLowerCase().trim().equals(term.toLowerCase().trim())) {
				counter++;
				
			}
		}
		System.out.println(counter);
		JobQueryTerms.get(term).termCount = counter; 
	}
	
	
	
}

private void tf(Job job) {
	
	
	for(String term : job.getQuery()) {
		
		JobQueryTerms.get(term).tf = (double)(JobQueryTerms.get(term).termCount)/this.getTotalWordsInCV();
	
	}
	
}

private void idf(Job job) {

		this.folder.idf(job);
		for(String term : job.getQuery()) {
			
			JobQueryTerms.get(term).idf = this.folder.getIdf().get(term);
			System.out.println("term "+this.folder.getIdf().get(term));
		}
}

private void tf_idf(Job job) {
	for(String term : job.getQuery()) {
		JobQueryTerms.get(term).tf_idf = JobQueryTerms.get(term).tf*JobQueryTerms.get(term).idf;
		System.out.println("JobQueryTerms.get(term)  "+JobQueryTerms.get(term).tf+"  * "+JobQueryTerms.get(term).idf);
	}
}

public void extractTexts() {
	try {
		PdfReader reader = new PdfReader(this.path);
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);
		TextExtractionStrategy strategy;
		words = new ArrayList<String>();
		for(int i = 1;i<=reader.getNumberOfPages();i++) {
			strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
			String wordString = strategy.getResultantText();
			
			if(!wordString.isEmpty()) words.addAll(new ArrayList<String>(Arrays.asList(wordString.split(" "))));
			
			
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



/**
 * @return the jobQueryTerms
 */
public Map<String, JobTerm> getJobQueryTerms() {
	return JobQueryTerms;
}


/**
 * @param jobQueryTerms the jobQueryTerms to set
 */
public void setJobQueryTerms(Map<String, JobTerm> jobQueryTerms) {
	JobQueryTerms = jobQueryTerms;
}




@Override
public int compareTo(CV other) {
	
	return ((Double)this.getScore()).compareTo(other.getScore());
}




}
