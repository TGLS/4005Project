package group13.iteration2;

import java.util.List;
import java.util.ArrayList;

public class WorkStation extends EventServer {
	private double muVal;
	private boolean constructing;
	private List<Buffer> watchBuffers;

	public WorkStation(Simulation sim, double muVal, String name) {
		super(name);
		this.constructing = false;
		this.sim = sim;
		this.muVal = muVal;
		watchBuffers = new ArrayList<Buffer>();
		nextEventTime = 0.0;
	}

	public void handleEvent() {
		if (constructing) {
			sim.incrementItemsComplete(this);
			constructing = false;
		}
		for (Buffer b : watchBuffers) {
			if (b.bufferEmpty()) {
				blocked = true;
				return;
			}
		}
		constructing = true;
		addBlockedTime();
		for (Buffer b : watchBuffers) {
			b.decrementBuffer();
		}
		
		blocked = false;
		
		generateNextEvent();
	}

	private void generateNextEvent() {
		nextEventTime = sim.currentTime() + sim.getRandom().getExponential(muVal);
	}
	
	public void addBuffer(Buffer buffer) {
		watchBuffers.add(buffer);
	}
}
