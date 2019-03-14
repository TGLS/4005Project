package group13.iteration2;

public class InspectorTwo extends EventServer {
	private Buffer bufferComp2;
	private Buffer bufferComp3;
	private int currentComp;
	
	public InspectorTwo(Simulation sim, int bufferSize, String name) {
		super(name);
		this.sim = sim;
		bufferComp2 = new Buffer(bufferSize);
		bufferComp3 = new Buffer(bufferSize);
		
		generateNextEvent();
	}
	
	public InspectorTwo(Simulation sim, int bufferSize) {
		this(sim, bufferSize, "Inspector 2");
	}
	
	public Buffer getBuffer2() {
		return bufferComp2;
	}
	
	public Buffer getBuffer3() {
		return bufferComp3;
	}

	public void handleEvent() {
		if (currentComp == 2) {
			if (bufferComp2.bufferFull()) {
				blocked = true;
			} else {
				blocked = false;
				bufferComp2.incrementBuffer();
			}
		} else {
			if (bufferComp3.bufferFull()) {
				blocked = true;
			} else {
				blocked = false;
				bufferComp3.incrementBuffer();
			}
		}
		
		if (!blocked) {
			sim.incrementItemsComplete(this);
			addBlockedTime();
			generateNextEvent();
		}
	}
	
	private void generateNextEvent() {
		currentComp = sim.getRandom().getNextComponentTypeInsp2();
		if (currentComp == 2) {
			nextEventTime = sim.currentTime() + sim.getRandom().getInsp22Time();
		} else {
			nextEventTime = sim.currentTime() + sim.getRandom().getInsp23Time();
		}
	}
	
	public void unblockEvent() {
		handleEvent();
	}
}
