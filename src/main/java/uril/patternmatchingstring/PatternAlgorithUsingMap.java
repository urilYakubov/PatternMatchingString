package uril.patternmatchingstring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatternAlgorithUsingMap {
	
	String dictionary;

	public PatternAlgorithUsingMap(String dictionary) {
		this.dictionary = dictionary;
	}
	
	public Map<String, List<Integer>> processOneLine(String str) {
		Map<String, List<Integer>> result = new HashMap<String, List<Integer>>();
		
		
		return result;
	}
	
	private boolean isSeparation(char ch) {
		if(ch == ' ' || ch == '.' || ch == '?' || ch == '!') {
			return true;
		}
		
		return false;
	}
	
	public Map<String, List<Integer>> run() {
		
		Map<String, List<Integer>> result = new HashMap<String, List<Integer>>();
		int startPosition = 0;
		
		while (startPosition < dictionary.length()) {
			
			while(startPosition < dictionary.length() && isSeparation(dictionary.charAt(startPosition)) ) {
				startPosition++;
			}
			
			if(startPosition >= dictionary.length()) {
				return result;
			}
			
			int endPosition = startPosition;
			while(endPosition < dictionary.length() && !isSeparation(dictionary.charAt(endPosition)) ) {
				endPosition++;
			}
			String word = dictionary.substring(startPosition, endPosition);
			if(!result.containsKey(word)) {
				result.put(word, new ArrayList<Integer>());
				List<Integer> list = result.get(word);
				list.add(startPosition + 1);
			} else {
				List<Integer> list = result.get(word);
				list.add(startPosition + 1);
			}			
			startPosition = endPosition;			
		}		
		
		return result;		
	}
	
	

}
