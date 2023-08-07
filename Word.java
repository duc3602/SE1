package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
	//text part = actual word
	//raw text = actual word(text part)+ prefix + suffix
	// words = valid + invalid word
	//ex: invalid word : ,se1021. ;  se,se, ...
	public static Set<String> stopWords = new HashSet<>();
	
	String word;
	public boolean isValid;
	public String textPart;
	public String suff;
	public String pref;
	public Word(String word) {
		this.word = word;
	}
	 public String getWord() {
	        return this.word;
	 }
	
	
	public boolean isKeyword() {
		if(textPart.isEmpty()|| !isValid || textPart == null)
			return false;
		return !stopWords.contains(textPart.toLowerCase());
	}
	 
	
	public String getPrefix() {
		if(pref == null) 
			return "";
		return pref;
	}
	public String getSuffix() {
		if(suff == null) 
			return "";
		return suff;
	}
	public String getText() {
		return textPart;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof String)
			return textPart.equalsIgnoreCase((String)o);
		if(o instanceof Word)
			return textPart.equalsIgnoreCase(((Word)o).getText());
		return false;
				
	}
	
	@Override
	public String toString() {	
	return pref + textPart + suff;
		
	}
	public static Word createWord(String rawText) {
		Word wd = new Word(rawText);
		
		Pattern p = Pattern.compile("^(\\W*)([a-zA-Z-']+)(\\W*)$");
		Matcher match = p.matcher(rawText);
		
		if(match.find()) {
			wd.pref = match.group(1);
			
			String keywd = match.group(2);
			
			if(keywd.charAt(keywd.length()-1) =='s' && keywd.charAt(keywd.length()-2)=='\'') {
				wd.suff = "'s"+ match.group(3) ;
				wd.textPart = keywd.substring(0,keywd.length()-2);
				
			}else { 
				wd.textPart = keywd;
				if(match.group(3)== null)
					wd.suff = "";
				else wd.suff = match.group(3);
			
			}
			
			wd.isValid= true;
		}else{
			wd.isValid = false;
			wd.textPart = rawText;
		}
		return wd;
		
	}
	public static boolean loadStopWords(String fileName) {
		try {
			File fl = new File(fileName);
			Scanner sc = new Scanner(fl);
			while(sc.hasNextLine()) {
				stopWords.add(sc.nextLine());
			}
			sc.close();
			return true;
		}catch(FileNotFoundException e) {
			return false;
		}
		
		
	}
	
	
}
