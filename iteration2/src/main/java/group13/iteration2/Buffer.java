package group13.iteration2;

public class Buffer {
	private int bufferSize;
	private int buffer;
	private int highWaterMark;
	
	public Buffer(int bufferSize) {
		this.bufferSize = bufferSize;
		buffer = 0;
		highWaterMark = 0;
	}

	public boolean bufferFull() {
		return (buffer == bufferSize);
	}
	
	public boolean bufferEmpty() {
		return (buffer == 0);
	}
	
	public int getValue() {
		return buffer;
	}
	
	public void incrementBuffer() {
		buffer++;
		if (buffer > highWaterMark) {
			highWaterMark = buffer;
		}
	}
	
	public void decrementBuffer() {
		buffer--;
	}

	public int getHighWaterMark() {
		return highWaterMark;
	}
}
