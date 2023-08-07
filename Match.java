package engine;

public class Match implements Comparable<Match>{
	
	public Doc d;
	public Word w;
	public int freq;
	public int firstIndex;
	public Match(Doc d, Word w, int freq, int firstIndex) {
		this.d = d;
		this.w = w;
		this.freq = freq;
		this.firstIndex = firstIndex;
	}
	
	public int getFreq() {
		return freq;
	}
	
	public int getFirstInex() {
		return firstIndex;
	}
	public Word getWord() {
		return w;
	}

	@Override
	public int compareTo(Match o) {
		// TODO Auto-generated method stub
		return this.getFirstInex() - o.getFirstInex();
	}
	

}
