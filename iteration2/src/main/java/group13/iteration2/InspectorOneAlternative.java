package group13.iteration2;

public class InspectorOneAlternative extends EventServer {
	private Buffer bufferComp1;
	
	public InspectorOneAlternative(Simulation sim, int bufferSize, String name) {
		this(sim, new Buffer(bufferSize), name);
	}
	
	public InspectorOneAlternative(Simulation sim, Buffer bufferComp1, String name) {
		super(name);
		this.sim = sim;
		this.bufferComp1 = bufferComp1;
		generateNextEvent();
	}
	
	public InspectorOneAlternative(Simulation sim, int bufferSize) {
		this(sim, bufferSize, "Inspector 1");
	}
	
	public Buffer getBuffer1() {
		return bufferComp1;
	}

	public void handleEvent() {
		if (bufferComp1.bufferFull()) {
			blocked = true;
		} else {
			blocked = false;
			bufferComp1.incrementBuffer();
		}
		
		if (!blocked) {
			sim.incrementItemsComplete(this);
			addBlockedTime();
			generateNextEvent();
		}
	}
	
	private void generateNextEvent() {
		nextEventTime = sim.currentTime() + sim.getRandom().getInsp1Time();
	}
}
