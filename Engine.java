package engine;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Engine {
	Doc[] allFiles;
	
	public int loadDocs(String dirname){
		File fo = new File(dirname);
		File[] listOfFiles = fo.listFiles();
		int count =0;
		allFiles = new Doc[listOfFiles.length];
		for(File fl : listOfFiles) {
			allFiles[count] =  new Doc(fl.getName());
			count++;
		}
		return count;
	}

	public Doc[] getDocs() {
		return allFiles ;
	}
	
	public List<Result> search(Query q) {
		List<Result> re = new ArrayList<>();
		List<Match> matches = new ArrayList<>();
		for(Doc doc: allFiles) {
			matches = q.matchAgainst(doc);
			if(matches.size()>0) {
				Result r = new Result(doc,matches);
				re.add(r);
			}
		}
		re.sort(Collections.reverseOrder());
		return re;
		
	}
	
	public String htmlResult(List<Result> results) {
		StringBuilder re = new StringBuilder();
        for (Result result : results){
            re.append(result.htmlHighlight());
        }
        return re.toString();
    }

}
