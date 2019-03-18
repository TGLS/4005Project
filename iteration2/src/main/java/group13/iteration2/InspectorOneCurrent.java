package group13.iteration2;

import java.util.ArrayList;
import java.util.List;

public class InspectorOneCurrent extends EventServer {
	private List<WorkStationCurrent> workStations;
	
	public InspectorOneCurrent(Simulation sim, String name) {
		super(name);
		this.sim = sim;
		workStations =  new ArrayList<WorkStationCurrent>();
		generateNextEvent();
	}
	
	public InspectorOneCurrent(Simulation sim) {
		this(sim, "Inspector 1");
	}

	public void handleEvent() {
		WorkStationCurrent target = null;
		for (WorkStationCurrent c : workStations) {
			Buffer curBuf = c.getBuffer();
			if (!curBuf.bufferFull() && (target == null || curBuf.getValue() < target.getBuffer().getValue())) {
				target = c;
			}
		}
		
		blocked = (target == null);
		
		if (target != null) {
			target.getBuffer().incrementBuffer();
			sim.incrementItemsComplete(this);
			addBlockedTime();
			generateNextEvent();
		}
	}
	
	public void addWorkStation(WorkStationCurrent stn) {
		workStations.add(stn);
	}
	
	private void generateNextEvent() {
		nextEventTime = sim.currentTime() + sim.getRandom().getInsp1Time();
	}
}
