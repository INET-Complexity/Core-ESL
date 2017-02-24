package org.java.org.economicsl.endtoend;

import java.util.ArrayList;
import java.util.List;

import org.java.org.economicsl.agent.Agent;
import org.java.org.economicsl.contract.examples.RandomTransfers;
import org.java.org.economicsl.contract.handler.AutomaticContractHandler;
import org.java.org.economicsl.inventory.Good;
import sim.engine.SimState;

public class HelloWorld extends SimState {

    public HelloWorld(long seed) {
	super(seed);
    }

    public void start() {

	super.start(); // reuse the SimState start method

	List<Agent> agents = new ArrayList<Agent>();

	for (int i = 1; i <= 2; i++) {
	    Agent a = new Agent("Agent " + i);

	    // initialize each org.economicsl.agent with $100.0
	    try {
		a.getInventory().add(new Good("cash", 1000.0));
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    agents.add(a);
	}

	this.connectedContracts(agents);

    }

    public static void main(String[] args) {
	doLoop(HelloWorld.class, args);
	System.exit(0);
    }

    private void connectedContracts(List<Agent> agents) {

	AutomaticContractHandler handler = new AutomaticContractHandler();

	for (int i = 0; i < agents.size(); i++) {

	    for (int j = 0; j < agents.size(); j++) {
		if (i <= j) {
		    continue;
		}

		RandomTransfers contract = new RandomTransfers(agents.get(i), agents.get(j), "randomTransfers", this,
			handler);
		contract.start(this);
	    }
	}
    }

}
