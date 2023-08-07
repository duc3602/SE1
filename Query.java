package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Query {
	public List<Word> keywords = new ArrayList<>() ;
	
	//store a list of keywords which extracted 
	//from a phrase that are needed to search
	public Query(String searchPhrase) {
		String[] wds = searchPhrase.split(" ");
		for(String w: wds) {
			Word wd = Word.createWord(w);
			if(wd.isKeyword()) {
				keywords.add(wd);
			}
		}
	}
	// get keywords
	public List<Word> getKeywords(){
		ArrayList<Word> re = new ArrayList<>();
		for(Word w: keywords) {
			re.add(w);
		}
		return re;
	}
	// check each keyword in a Doc
	public List<Match> matchAgainst(Doc d){
		List<Word> wds = d.getTitle();
		wds.addAll(d.getBody());
		List<Match> mats = new ArrayList<>();
		int freq, firstIndex;
		for(Word wd: keywords) {
			if(wds.contains(wd)) {
				freq = Collections.frequency(wds, wd);
				firstIndex = wds.indexOf(wd);
				Match mat = new Match(d, wd,freq,firstIndex);
				mats.add(mat);
			}
			
		}
		Collections.sort(mats);
		return mats;
		
	}

}
