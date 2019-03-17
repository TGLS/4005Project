package group13.iteration2;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.*;

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
     * Test Simulation
     */
    public void testApp()
    {
        Simulation sim = Simulation.testSimulation();
        sim.run();
        HashMap<String, List<Double>> blockageTime = new HashMap<String, List<Double>>();
        HashMap<String, List<Integer>> itemsComplete = new HashMap<String, List<Integer>>();
        sim.enterBlockageTime(blockageTime);
        sim.enterItemsComplete(itemsComplete);
        System.out.println(sim);
        assertTrue(blockageTime.get("Inspector 1").get(0) == 32.0);
        assertTrue(itemsComplete.get("Inspector 1").get(0) == 27);
        
        assertTrue(blockageTime.get("Inspector 2").get(0) == 30.0);
        assertTrue(itemsComplete.get("Inspector 2").get(0) == 11);
        
        assertTrue(blockageTime.get("Workstation 1").get(0) == 1.0);
        assertTrue(itemsComplete.get("Workstation 1").get(0) == 11);
        
        assertTrue(blockageTime.get("Workstation 2").get(0) == 18.0);
        assertTrue(itemsComplete.get("Workstation 2").get(0) == 6);
        
        assertTrue(blockageTime.get("Workstation 3").get(0) == 5.0);
        assertTrue(itemsComplete.get("Workstation 3").get(0) == 2);
    }
}
