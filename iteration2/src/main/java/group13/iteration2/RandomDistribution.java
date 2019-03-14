package group13.iteration2;

import java.util.Random;

public class RandomDistribution {
	private static final double INSP1_MU_VALUE = 10.3579;
	private static final double INSP2_2_MU_VALUE = 15.5369;
	private static final double INSP2_3_MU_VALUE = 20.6328;
	private Random rand;
	
	public RandomDistribution() {
		rand = new Random();
	}
	
	public double getExponential(double mu) {
		return (Math.log(1 - rand.nextDouble()) * -mu);
	}
	
	public int getNextComponentTypeInsp2() {
		if (rand.nextBoolean()) {
			return 2;
		} else {
			return 3;
		}
	}

	public double getInsp1Time() {
		return getExponential(INSP1_MU_VALUE);
	}
	
	public double getInsp22Time() {
		return getExponential(INSP2_2_MU_VALUE);
	}
	
	public double getInsp23Time() {
		return getExponential(INSP2_3_MU_VALUE);
	}
}
