package group13.iteration2;

public class AlternativeBuilder implements SimulationBuilder {

	public Simulation buildSimulation() {
		Simulation retVal = new Simulation();
		retVal.setRand(new RandomDistribution());
		InspectorOneAlternative a1 = new InspectorOneAlternative(retVal, 50);
		InspectorTwo a2 = new InspectorTwo(retVal, 50);
		WorkStation w1 = new WorkStation(retVal, 4.6044, "Workstation 1");
		WorkStation w2 = new WorkStation(retVal, 11.0926, "Workstation 2");
		WorkStation w3 = new WorkStation(retVal, 8.7956, "Workstation 3");
		w1.addBuffer(a1.getBuffer1());
		w2.addBuffer(a1.getBuffer1());
		w3.addBuffer(a1.getBuffer1());
		w2.addBuffer(a2.getBuffer2());
		w3.addBuffer(a2.getBuffer3());
		retVal.addServer(a1);
		retVal.addServer(a2);
		retVal.addServer(w1);
		retVal.addServer(w2);
		retVal.addServer(w3);
		retVal.addBuffer("Comp1 Buffer", a1.getBuffer1());
		retVal.addBuffer("Comp2 Buffer", a2.getBuffer2());
		retVal.addBuffer("Comp3 Buffer", a2.getBuffer3());
		return retVal;
	}

}
