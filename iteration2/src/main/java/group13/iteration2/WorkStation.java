package group13.iteration2;

import java.util.List;
import java.util.ArrayList;

public class WorkStation extends EventServer {
	private double muVal;
	private List<Buffer> watchBuffers;

	public WorkStation(Simulation sim, double muVal, String name) {
		super(name);
		this.sim = sim;
		this.muVal = muVal;
		watchBuffers = new ArrayList<Buffer>();
	}

	public void handleEvent() {
		for (Buffer b : watchBuffers) {
			if (b.bufferEmpty()) {
				blocked = true;
				return;
			}
		}
		
		blocked = false;
		sim.incrementItemsComplete(this);
		addBlockedTime();
		for (Buffer b : watchBuffers) {
			b.decrementBuffer();
		}
		
		generateNextEvent();
	}

	private void generateNextEvent() {
		nextEventTime = sim.currentTime() + sim.getRandom().getExponential(muVal);
	}

	public void unblockEvent() {
		handleEvent();
	}
	
	public void addBuffer(Buffer buffer) {
		watchBuffers.add(buffer);
	}

}
