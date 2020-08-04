package uril.patternmatchingstring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;

public class Executor {

	private String[] keyArray;
	private String[] dictionary;
	private Map<String, ArrayList<WordMetric>> wordMetrics = new HashMap<String, ArrayList<WordMetric>>();
    private TreeMap<String, String> map = new TreeMap<String, String>();
    AhoCorasickDoubleArrayTrie<String> acdat = new AhoCorasickDoubleArrayTrie<String>();
    private static int numberOfCores = 4;
	
	public Executor(String[] keyArray, String[] dictionary) {
		super();
		this.keyArray = keyArray;
		this.dictionary = dictionary;
		prepareData();
	}
	
	private void prepareData() {
		prepareWordMetric();
		preparePatternTree();
	}
	
	private void prepareWordMetric() {
		
		// pls check HashMap putIfAbsent(key, value)
		for (String key : keyArray) {
			wordMetrics.put(key, new ArrayList<WordMetric>());
        }
	}
	
	private void preparePatternTree() {
		for (String key : keyArray)
        {
            map.put(key, key);
        }
		acdat.build(map);
	}
	
	
	public Map<String, ArrayList<WordMetric>> run() throws InterruptedException, ExecutionException {
		
		ExecutorService executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfCores);
		
		
		List< FutureTask<ArrayList<WordMetric>> >  wordProcessingTasks = new ArrayList<>();
		
		for (int i = 0; i < dictionary.length; i++) {
			CallableWordPatternProcessing callable = new CallableWordPatternProcessing(acdat, dictionary[i], i + 1);
			FutureTask<ArrayList<WordMetric>> wordProcessingTask = (FutureTask<ArrayList<WordMetric>>) executor.submit(callable);
			wordProcessingTasks.add(wordProcessingTask);
		}
		
		for (int i = 0; i < wordProcessingTasks.size(); i++) {
        	Future<ArrayList<WordMetric>> futureWordList = wordProcessingTasks.get(i);
        	ArrayList<WordMetric> wordList = futureWordList.get();
        	
        	for(WordMetric word: wordList) {
        		ArrayList<WordMetric> arrayW = wordMetrics.get(word.getPattern());
        		if(arrayW != null) {
	        		arrayW.add(word);
	        		wordMetrics.put(word.getPattern(), arrayW);
        		}        		
            }						
		}
		return wordMetrics;
	}
	
}
