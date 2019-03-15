package group13.iteration2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
	
    private static final int TOTAL_RUNS = 10000000;

	public static void main( String[] args )
    {	
		runSimulation("Default Simulation", new DefaultBuilder());
		runSimulation("Alternative Simulation", new AlternativeBuilder());
		runSimulation("Alternative 2 Simulation", new AlternativeTwoBuilder());
    }
    
    public static void runSimulation(String simName, SimulationBuilder builder) {
    	System.out.println(simName);
    	System.out.println("Property Name, Average, Standard Deviation");
    	HashMap<String, List<Double>> blockageTime = new HashMap<String, List<Double>>();
    	HashMap<String, List<Integer>> itemsComplete = new HashMap<String, List<Integer>>();
    	HashMap<String, List<Integer>> highWaterMarks = new HashMap<String, List<Integer>>();
        for (int n = 0; n < TOTAL_RUNS; n++) {
        	Simulation sim = builder.buildSimulation();
            sim.run();
            sim.enterBlockageTime(blockageTime);
            sim.enterItemsComplete(itemsComplete);
            sim.enterHighWaterMarks(highWaterMarks);
        }
        
        printBlockageStatisitics(blockageTime);
        printCompleteStatisitics(itemsComplete);
        printWatermarkStatisitics(highWaterMarks);
    }
	
	private static void printWatermarkStatisitics(HashMap<String, List<Integer>> highWaterMarks) {
		for (String s : highWaterMarks.keySet()) {
			double average = 0.0;
			double stdev = 0.0;
			for (Integer i : highWaterMarks.get(s)) {
				average += (double) i;
				stdev += ((double) i) * ((double) i);
			}
			average = average / TOTAL_RUNS;
			stdev = stdev / TOTAL_RUNS - average * average;
			System.out.println(s + " High Water Mark, " + average + ", " + stdev);
		}
	}

	private static void printCompleteStatisitics(HashMap<String, List<Integer>> itemsComplete) {
		for (String s : itemsComplete.keySet()) {
			double average = 0.0;
			double stdev = 0.0;
			for (Integer i : itemsComplete.get(s)) {
				average += (double) i;
				stdev += ((double) i) * ((double) i);
			}
			average = average / TOTAL_RUNS;
			stdev = stdev / TOTAL_RUNS - average * average;
			System.out.println(s + " Total Items Complete, " + average + ", " + stdev);
		}
	}

	private static void printBlockageStatisitics(HashMap<String, List<Double>> blockageTime) {
		for (String s : blockageTime.keySet()) {
			double average = 0.0;
			double stdev = 0.0;
			for (Double d : blockageTime.get(s)) {
				average += d;
				stdev += d * d;
			}
			average = average / TOTAL_RUNS;
			stdev = stdev / TOTAL_RUNS - average * average;
			System.out.println(s + " Blocked Time, " + average + ", " + stdev);
		}
	}
}
