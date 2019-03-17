package group13.iteration2;

public abstract class EventServer {
	protected Simulation sim;
	protected boolean blocked;
	protected double nextEventTime;
	protected String name;
	
	public EventServer(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	public abstract void handleEvent();

	public final boolean unblockEvent() {
		handleEvent();
		
		return !blocked;
	}
	
	public double getNextEventTime() {
		return nextEventTime;
	}
	
	public boolean nextEventBlocked() {
		return blocked;
	}
	
	public void addBlockedTime() {
		if (nextEventTime < sim.currentTime()) {
			sim.addBlockageTime(this, sim.currentTime() - nextEventTime);
		}
	}
	
	public void incrementCompleteItems() {
		sim.incrementItemsComplete(this);
	}
}
