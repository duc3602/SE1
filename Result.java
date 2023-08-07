package engine;

import java.util.ArrayList;
import java.util.List;

public class Result implements Comparable<Result> {

	public List<Match> matches = new ArrayList<>();
	public Doc d;

	public Result(Doc d, List<Match> matches) {
		this.matches = matches;
		this.d = d;
	}

	public List<Match> getMatches() {
		List<Match> re = new ArrayList<>();
		for (Match m : matches) {
			re.add(m);
		}
		return re;
	}

	public int getTotalFrequency() {
		int sum = 0;
		for (Match m : matches) {
			sum += m.getFreq();
		}
		return sum;
	}

	public double getAverageFirstIndex() {
		double ave = 0;
		int sum = 0;
		for (Match m : matches) {
			sum += m.getFirstInex();
		}
		ave = sum / (matches.size());
		return ave;
	}
	public Doc getDoc() {
		return d;
	}
	public String htmlHighlight() {
		String t = getDoc().combineTitle();
        String b = getDoc().combineBody();
        String[] wdTitle = t.split(" ");
        String[] wdBody = b.split(" ");
        
        //<h3> for title and <p> for body
        t = t.replace(t, "<h3>" + t + "</h3>");
        b = b.replace(b, "<p>" + b + "</p>");

        // <u> and <b> for matched word
        for (Match m : matches) {
            String w = m.getWord().getText();
            t = t.replaceAll("\\b" + w + "\\b", "<u>" + w + "</u>");
            b = b.replaceAll("\\b" + w + "\\b", "<b>" + w + "</b>");
            
            for (String string : wdTitle) {
                if (Character.isUpperCase(string.charAt(0)) && string.equalsIgnoreCase(w)){
                    w = w.substring(0, 1).toUpperCase() + w.substring(1);
                    t = t.replaceAll("\\b"  + w + "\\b", "<u>" + w + "</u>");
                }
            }
            for (String str : wdBody) {
                if (Character.isUpperCase(str.charAt(0)) && str.equalsIgnoreCase(w)){
                    w = w.substring(0, 1).toUpperCase() + w.substring(1);
                    b = b.replaceAll("\\b" + w + "\\b", "<b>" + w + "</b>");
                }
            }
        }
        return t + b;
    }

		
		
	@Override
	public int compareTo(Result o) {
		if (this.matches.size() == o.matches.size() && this.getTotalFrequency() == o.getTotalFrequency())
			return (int) o.getAverageFirstIndex() - (int) this.getAverageFirstIndex();
		if (this.matches.size() == o.matches.size())
			return this.getTotalFrequency() - o.getTotalFrequency();
		return this.matches.size() - o.matches.size();
	}

}
