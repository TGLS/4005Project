package group13.iteration2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {
	private double clock;
	private double maxSimulationTime;
	private RandomDistribution rand;
	private Map<EventServer, Double> blockageTime;
	private Map<EventServer, Integer> totalItemsComplete;
	private List<EventServer> allEventServers;
	private Map<String, Buffer> allBuffers;
	
	public Simulation(double maxSimulationTime) {
		clock = 0.0;
		this.maxSimulationTime = maxSimulationTime;
		setRand(new RandomDistribution());
		blockageTime = new HashMap<EventServer, Double>();
		totalItemsComplete = new HashMap<EventServer, Integer>();
		allEventServers = new ArrayList<EventServer>();
		allBuffers = new HashMap<String, Buffer>();
	}
	
	public Simulation() {
		this(1000);
	}
	
	public static Simulation testSimulation() {
		Simulation retVal = new Simulation();
		retVal.setRand(new DummyRandom());
		InspectorOneCurrent a1 = new InspectorOneCurrent(retVal);
		InspectorTwo a2 = new InspectorTwo(retVal, 2);
		WorkStationCurrent w1 = new WorkStationCurrent(retVal, 2, 5, "Workstation 1");
		WorkStationCurrent w2 = new WorkStationCurrent(retVal, 2, 7, "Workstation 2");
		WorkStationCurrent w3 = new WorkStationCurrent(retVal, 2, 20, "Workstation 3");
		a1.addWorkStation(w1);
		a1.addWorkStation(w2);
		a1.addWorkStation(w3);
		w2.addBuffer(a2.getBuffer2());
		w3.addBuffer(a2.getBuffer3());
		retVal.addServer(a1);
		retVal.addServer(a2);
		retVal.addServer(w1);
		retVal.addServer(w2);
		retVal.addServer(w3);
		retVal.maxSimulationTime = 60;
		return retVal;
	}
	
	public void addBuffer(String name, Buffer buffer) {
		allBuffers.put(name, buffer);
	}

	public void addServer(EventServer e) {
		allEventServers.add(e);
		blockageTime.put(e, (double) 0);
		totalItemsComplete.put(e, 0);
	}
	
	public void run() {
		while (!step());
		for (EventServer s : allEventServers) {
			s.addBlockedTime();
		}
	}
	
	public String toString() {
		String retVal = "";
		for (EventServer s : allEventServers) {
			retVal += s + ", " + blockageTime.get(s) + ", " + totalItemsComplete.get(s) + "\n";
		}
		return retVal;
	}
	
	// Are we done?
	private boolean step() {
		// We start with the soonest event as the end time of the simulation.
		// That way if all event times are bigger than simulation time, 
		double soonestEventTime = maxSimulationTime;
		EventServer nextEventTarget = null;
		for (EventServer s : allEventServers) {
			if (!s.nextEventBlocked() && soonestEventTime > s.getNextEventTime()) {
				nextEventTarget = s;
				soonestEventTime = s.getNextEventTime();
			}
		}
		
		// Now that we know which event will happen soonest, we move the clock up to that time
		clock = soonestEventTime;
		
		// If this is null, then we reached maxSimulationTime
		if (nextEventTarget == null) {
			return true;
		}
		
		nextEventTarget.handleEvent();
		
		// Then handle any events that may have been unblocked.
		boolean noChange = false;
		while (!noChange) {
			noChange = true;
			for (EventServer s : allEventServers) {
				if (s.nextEventBlocked()) {
					if (s.unblockEvent()) {
						noChange = false;
					}
				}
			}
		}
		
		return false;
	}
	
	public double currentTime() {
		return clock;
	}

	public RandomDistribution getRandom() {
		return getRand();
	}

	public void addBlockageTime(EventServer eventServer, double blockedTime) {
		double newTotal = blockageTime.get(eventServer) + blockedTime;
		blockageTime.put(eventServer, newTotal);
	}
	
	public void incrementItemsComplete(EventServer eventServer) {
		totalItemsComplete.put(eventServer, totalItemsComplete.get(eventServer) + 1);
	}

	public void enterBlockageTime(HashMap<String, List<Double>> output) {
		for (EventServer s : allEventServers) {
			if (!output.containsKey(s.toString())) {
				output.put(s.toString(), new ArrayList<Double>());
			}
			output.get(s.toString()).add(blockageTime.get(s));
		}
	}
	
	public void enterItemsComplete(HashMap<String, List<Integer>> output) {
		for (EventServer s : allEventServers) {
			if (!output.containsKey(s.toString())) {
				output.put(s.toString(), new ArrayList<Integer>());
			}
			output.get(s.toString()).add(this.totalItemsComplete.get(s));
		}
	}

	public void enterHighWaterMarks(HashMap<String, List<Integer>> output) {
		for (String s : allBuffers.keySet()) {
			if (!output.containsKey(s)) {
				output.put(s, new ArrayList<Integer>());
			}
			output.get(s).add(this.allBuffers.get(s).getHighWaterMark());
		}	
	}

	/**
	 * @return the rand
	 */
	public RandomDistribution getRand() {
		return rand;
	}

	/**
	 * @param rand the rand to set
	 */
	public void setRand(RandomDistribution rand) {
		this.rand = rand;
	}
}
