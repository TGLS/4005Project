package group13.iteration2;

public class WorkStationCurrent extends WorkStation {
	private Buffer buffer;
	
	public WorkStationCurrent(Simulation sim, int bufferSize, double muVal, String name) {
		super(sim, muVal, name);
		buffer = new Buffer(bufferSize);
		addBuffer(buffer);
	}

	public Buffer getBuffer() {
		return buffer;
	}
}
