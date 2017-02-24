package org.java.org.economicsl.contract.examples;

import org.java.org.economicsl.agent.Agent;
import org.java.org.economicsl.contract.ScheduledContracts;
import org.java.org.economicsl.contract.handler.ContractHandler;
import org.java.org.economicsl.contract.messages.ObligationResponse;
import org.java.org.economicsl.contract.obligation.Obligation;
import org.java.org.economicsl.contract.obligation.ScheduledObligation;
import ec.util.MersenneTwisterFast;
import org.java.org.economicsl.inventory.Contract;
import org.java.org.economicsl.inventory.Good;
import sim.engine.SimState;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RandomTransfers extends ScheduledContracts {

    private Agent agent1;
    private Agent agent2;
    private Boolean active;

    public RandomTransfers(Agent agent1, Agent agent2, String name, SimState state, ContractHandler handler) {
	super(name, state, handler);
	this.agent1 = agent1;
	this.agent2 = agent2;
	active = true;

    }

    @Override
    public void handleResponse(ObligationResponse response) {

	// if someone couldn't handle the transfer, cancel the org.economicsl.contract.
	if (!response.getFilled()) {
	    active = false;
	} else {
	    Good g = (Good) response.getObligation().getWhat();
	    Obligation o = response.getObligation();

	    try {
		System.out.println(o.getFrom().getName() + " sent " + o.getFrom().getName() + " $" + g.getQuantity()
			+ ". As such, " + o.getFrom().getName() + " has $"
			+ o.getFrom().getInventory().getGood("cash").getQuantity() + " and " + o.getTo().getName()
			+ " has $" + o.getTo().getInventory().getGood("cash").getQuantity());
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }

	}
    }

    @Override
    public ScheduledObligation requestNextObligation(SimState state) {

	if (!active) {
	    return null;
	}

	// randomly assign the amount to trade from 0 - 100.
	MersenneTwisterFast r = this.getState().random;
	double amount = r.nextDouble() * 10.0;
	Good trade = new Good("cash", amount);

	Obligation o = new Obligation(agent1, agent2, trade);

	// There is a coin flip on who trades with whom
	if (r.nextBoolean()) {
	    o = new Obligation(agent2, agent1, trade);
	}

	// the next action occurs in the next 1-5 rounds
	r = this.getState().random;
	Double time = this.getState().schedule.getTime() + r.nextInt(5) + 1;

	// delete, for print
	Good g = (Good) o.getWhat();
	try {
	    System.out.println(o.getFrom().getName() + " has $"
		    + o.getFrom().getInventory().getGood("cash").getQuantity() + " and " + o.getTo().getName()
		    + " has $" + o.getTo().getInventory().getGood("cash").getQuantity() + " and "
		    + o.getFrom().getName() + " is scheduled to send " + o.getTo().getName() + " $" + g.getQuantity()
		    + " in " + time + " rounds.");
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return new ScheduledObligation(o, time);
    }

    @Override
    public Contract addition(Contract c) {
	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	throw new NotImplementedException();
    }

}
