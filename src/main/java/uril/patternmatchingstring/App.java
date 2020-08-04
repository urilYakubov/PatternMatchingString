package uril.patternmatchingstring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * Purpose of the program is to find patterns inside an array of strings using 
 * multithreading in order to improve performance of the task.
 */
public class App 
{
	
	public static void main( String[] args ) 
    {
        // Collect test data set
    	String[] keyArray = new String[]
                {
                        "Uril",
                        "Irena",
                        "Pinhas"               
                };
        
        final String[] dictionary = {"I know Uril is here. Pinhas is here. Uril", 
				"I know Irena is here. Pinhas is here. Irena",
				"I know Pinhas is here. Uril is here. Pinhas "};
        
        Executor executor = new Executor(keyArray, dictionary);
        Map<String, ArrayList<WordMetric>> metrics = null;
        
        try {
        	metrics = (Map<String, ArrayList<WordMetric>>) executor.run();
        } catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
        
        System.out.println("Irena size = " + metrics.get("Irena").size());
        System.out.println("Pinhas size = " + metrics.get("Pinhas").size());
        System.out.println("Uril size = " + metrics.get("Uril").size());
 
        final String dictionary2 = "I Uril Uril";
	    PatternAlgorithUsingMap patternAlgorithUsingMap = new PatternAlgorithUsingMap(dictionary2);
	    Map<String, List<Integer>> patterns = patternAlgorithUsingMap.run();
        
        
    }
}
