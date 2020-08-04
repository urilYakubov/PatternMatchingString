package uril.patternmatchingstring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import uril.patternmatchingstring.Executor;
import uril.patternmatchingstring.WordMetric;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
        
        String[] keyArray = new String[]
            {
                    "Uril",
                    "Irena",
                    "Pinhas"               
            };
     
        String[] dictionary = {"I know Uril is here. Pinhas is here. Uril", 
				"I know Irena is here. Pinhas is here. Irena",
				"I know Pinhas is here. Uril is here. Pinhas "};
     
        Executor executor = new Executor(keyArray, dictionary);
        Map<String, ArrayList<WordMetric>> metrics = null;
        
        try {
        	metrics = (Map<String, ArrayList<WordMetric>>) executor.run();
        } catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }  
        
        checkIrena(metrics.get("Irena"));
        checkPinhas(metrics.get("Pinhas"));
        checkUril(metrics.get("Uril"));
    }
    
    
    public void checkIrena(ArrayList<WordMetric> metric) {
    	assertEquals(metric.size(), 2);
    }
    
    public void checkPinhas(ArrayList<WordMetric> metric) {
    	assertEquals(metric.size(), 4);
    }
    
    public void checkUril(ArrayList<WordMetric> metric) {
    	assertEquals(metric.size(), 3);
    }
    
    public void testPatternAlgorithmUsingMap() 
    {
    	final String dictionary2 = "I Uril Uril";
	    PatternAlgorithUsingMap patternAlgorithUsingMap = new PatternAlgorithUsingMap(dictionary2);
	    Map<String, List<Integer>> patterns = patternAlgorithUsingMap.run();
	    
	    List<Integer> list = patterns.get("I");
	    assertEquals(list.size(), 1);
	    
	    list = patterns.get("Uril");
	    assertEquals(list.size(), 2);
	    
    }
}
