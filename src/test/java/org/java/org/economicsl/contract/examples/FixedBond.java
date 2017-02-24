package org.java.org.economicsl.contract.examples;

import org.java.org.economicsl.agent.Agent;
import org.java.org.economicsl.contract.ScheduledContracts;
import org.java.org.economicsl.contract.handler.ContractHandler;
import org.java.org.economicsl.contract.messages.ObligationResponse;
import org.java.org.economicsl.contract.obligation.Obligation;
import org.java.org.economicsl.contract.obligation.ScheduledObligation;
import org.java.org.economicsl.inventory.Contract;
import org.java.org.economicsl.inventory.Good;
import sim.engine.SimState;

public class FixedBond extends ScheduledContracts {

    private State currentState;
    private Agent seller;
    private Agent buyer;
    private Integer numCoupons;
    private Long couponAmount;
    private Long principalPayment;
    private String goodName;
    private Double gapBetweenCoupons;

    public FixedBond(String name, SimState state, ContractHandler handler, Agent seller, Agent buyer, Long principal,
					 Long coupon, Integer numCoupons, String goodName, Double gapBetweenCoupons) {

	super(name, state, handler);

	currentState = State.PRINCIPAL;
	this.seller = seller;
	this.buyer = buyer;
	this.numCoupons = numCoupons;
	this.couponAmount = coupon;
	this.principalPayment = principal;
	this.goodName = goodName;
	this.gapBetweenCoupons = gapBetweenCoupons;

    }

    @Override
    public ScheduledObligation requestNextObligation(SimState state) {

	Obligation o = null;
	Double time = gapBetweenCoupons;

	switch (this.currentState) {

	case PRINCIPAL:
	    o = new Obligation(this.seller, this.buyer, new Good(this.goodName, principalPayment));
	    time = new Double(1.0);
	    break;
	case COUPON:
	    o = new Obligation(this.buyer, this.seller, new Good(this.goodName, this.couponAmount));
	    break;
	case MATURED:
	    o = new Obligation(this.buyer, this.seller, new Good(this.goodName, this.principalPayment));
	    break;
	}

	return (new ScheduledObligation(o, state.schedule.getSteps() + time));
    }

    @Override
    public void handleResponse(ObligationResponse response) {

	printObligation(response.getObligation());

	// switch state to DEFAULT if the response is false
	if (!response.getFilled()) {
	    this.currentState = State.DEFAULT;
	    return;
	}

	// change the state based on the response to the previous obligation
	switch (this.currentState) {

	case PRINCIPAL:
	    this.currentState = State.COUPON;
	    break;
	case COUPON:
	    // if all the coupons have been paid, then move the
	    // org.economicsl.contract to a matured status.
	    if (this.numCoupons <= 0) {
		this.currentState = State.MATURED;
		// if there are more coupons to be paid, subtract the number
		// by one.
	    } else {
		this.numCoupons--;
	    }
	    break;

	// if the matured payment has been made, terminate the org.economicsl.contract.
	case MATURED:
	    this.currentState = State.TERMINATED;
	}

    }

    private void printObligation(Obligation o) {

	if (o == null) {
	    return;
	}
	Agent from = o.getFrom();
	Agent to = o.getTo();
	String what = o.getWhat().getName();
	Double quantity = 1.0;

	if (o.getWhat() instanceof Good) {
	    quantity = ((Good) o.getWhat()).getQuantity();
	}
	System.out.println("The current state is: " + this.currentState + ". Therefore, " + from.getName() + " gave "
		+ to.getName() + " " + quantity + " of " + what);
	try {
	    System.out
		    .println("Agent " + from.getName() + " has $" + from.getInventory().getGood("cash").getQuantity());
	    System.out.println("Agent " + to.getName() + " has $" + to.getInventory().getGood("cash").getQuantity());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	}

    }

    private enum State {
	PRINCIPAL, COUPON, DEFAULT, MATURED, TERMINATED
    }

    @Override
    public Contract addition(Contract c) {
	// TODO Auto-generated method stub
	return null;
    }

}
