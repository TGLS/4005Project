package group13.iteration2;

public class DummyRandom extends RandomDistribution {
	private boolean lastCompTwo;
	
	public DummyRandom() {
		super();
		lastCompTwo = false;
	}
	
	public int getNextComponentTypeInsp2() {
		if (!lastCompTwo) {
			lastCompTwo = true;
			return 2;
		} else {
			lastCompTwo = false;
			return 3;
		}
	}
	
	public double getExponential(double mu) {
		return mu;
	}
	
	public double getInsp1Time() {
		return 1;
	}
	
	public double getInsp22Time() {
		return 2;
	}
	
	public double getInsp23Time() {
		return 3;
	}
}
