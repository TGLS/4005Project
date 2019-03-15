package group13.iteration2;

public class DefaultBuilder implements SimulationBuilder {

	public Simulation buildSimulation() {
		Simulation retVal = new Simulation();
		retVal.setRand(new RandomDistribution());
		InspectorOneCurrent a1 = new InspectorOneCurrent(retVal);
		InspectorTwo a2 = new InspectorTwo(retVal, 2);
		WorkStationCurrent w1 = new WorkStationCurrent(retVal, 2, 4.6044, "Workstation 1");
		WorkStationCurrent w2 = new WorkStationCurrent(retVal, 2, 11.0926, "Workstation 2");
		WorkStationCurrent w3 = new WorkStationCurrent(retVal, 2, 8.7956, "Workstation 3");
		a1.addWorkStation(w1);
		a1.addWorkStation(w2);
		a1.addWorkStation(w3);
		w2.addBuffer(a2.getBuffer2());
		w3.addBuffer(a2.getBuffer3());
		retVal.addServer(a1);
		retVal.addServer(a2);
		retVal.addServer(w1);
		retVal.addServer(w2);
		retVal.addServer(w3);
		retVal.addBuffer("Workstation 1 Comp1 Buffer", w1.getBuffer());
		retVal.addBuffer("Workstation 2 Comp1 Buffer", w2.getBuffer());
		retVal.addBuffer("Workstation 3 Comp1 Buffer", w3.getBuffer());
		retVal.addBuffer("Comp2 Buffer", a2.getBuffer2());
		retVal.addBuffer("Comp3 Buffer", a2.getBuffer3());
		return retVal;
	}

}
