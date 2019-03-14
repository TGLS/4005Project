package group13.iteration2;

public class DummyRandom extends RandomDistribution {
	
	public DummyRandom() {
		super();
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
