package uril.patternmatchingstring;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;

public class CallableWordPatternProcessing implements Callable<ArrayList<WordMetric>> {

	String dictionary;
	int lineNumber;
	AhoCorasickDoubleArrayTrie<String> acdat;
	
	public CallableWordPatternProcessing(AhoCorasickDoubleArrayTrie<String> acdat, String dictionary, int lineNumber) {
		this.dictionary = dictionary;
		this.lineNumber = lineNumber;
		this.acdat = acdat;
	}
	
	private ArrayList<WordMetric> convertToWordMetric(List<AhoCorasickDoubleArrayTrie.Hit<String>> wordList) {
		
		ArrayList<WordMetric> wordMetrics = new ArrayList<WordMetric>();
		
		for(AhoCorasickDoubleArrayTrie.Hit<String> str: wordList) {
			WordMetric wordMetric = new WordMetric(str.value, lineNumber, str.begin, str.end);
			wordMetrics.add(wordMetric);        	
        }
		
		return wordMetrics;
		
	}

	@Override
	public ArrayList<WordMetric> call() throws Exception {
		// Build an AhoCorasickDoubleArrayTrie
        // Test it
        List<AhoCorasickDoubleArrayTrie.Hit<String>> wordList = this.acdat.parseText(dictionary);
        
        return convertToWordMetric(wordList);
		
	}
}
