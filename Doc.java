package engine;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Doc {
	
	private String title;
	private String body;
	public Doc(String content) {
		if (content.contains(".txt")){
            try {
                File fl = new File("docs/"+content);
                Scanner sc = new Scanner(fl);
                title = sc.nextLine();
                body = sc.nextLine();
                sc.close();
            } catch (IOException e){
                System.out.println("Can't find files!");
                e.printStackTrace();
            }
        }
        else {
            String[] part = content.split("\n");
            title = part[0];
            body = part[1];
        }
		
	}

	public List<Word> getTitle(){
		ArrayList<Word> re = new ArrayList<>();
		String[] w = title.split(" ");
		for(String word: w) {
			Word words = Word.createWord(word);
			re.add(words);
		}
		return re;
	}
	
	public List<Word> getBody(){
		ArrayList<Word> re = new ArrayList<>();
		String[] w = body.split(" ");
		for(String word: w) {
			Word words = Word.createWord(word);
			re.add(words);
		}
		return re;
	}
	private String combine(List<Word> list){
        StringBuilder string = new StringBuilder();
        for (Word wd : list) {
            string.append(wd.getWord()).append(" ");
        }
        string = new StringBuilder(string.toString().trim());
        return string.toString();
    }

    public String combineTitle() {
        return combine(getTitle());
    }
    public String combineBody() {
        return combine(getBody());
    }

	
	public boolean equals(Object o) {
		if(getTitle().equals(((Doc) o).getTitle()))
			return true;
		if(getBody().equals(((Doc) o).getBody()))
			return true;
		return false;
	}

}
