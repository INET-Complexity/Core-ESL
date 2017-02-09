package contract;

import contract.handler.ContractHandler;
import contract.messages.FillObligation;
import contract.messages.ObligationResponse;
import contract.obligation.Obligation;
import contract.obligation.ScheduledObligation;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 * MasonScheduledContracts are a subclass of HandledContracts, which
 * automatically schedule Obligations in Mason.
 * 
 * @author prauwolf
 *
 */
public abstract class MasonScheduledContracts extends HandledContracts implements Steppable {

    // The next obligation which the contract will require.
    // If null, then there is no next Obligation
    private Obligation nextObligation;

    // The simstate variable holds the scheduler in Mason, so
    // it is used to schedule tasks
    private SimState state;

    /**
     * Builds a Contract which will schedule its Obligations in Mason
     * 
     * @param name
     *            Name of the contract
     * @param state
     *            The Mason SimState - contains the scheduler
     * @param handler
     *            The contracthandler which handles the Contracts Obligations
     */
    public MasonScheduledContracts(String name, SimState state, ContractHandler handler) {
	super(name, handler);
	this.state = state;
    }

    /**
     * Implementation of the Steppable interface in Mason. This is the method
     * that is called when the Mason scheduler calls the Contract As such, the
     * method attempts to fulfill the current Obligation, then schedule the next
     * Obligation.
     */
    public void step(SimState state) {

	// request the fulfillment of the current Obligation
	FillObligation fill = new FillObligation(nextObligation);

	// receive a response back about whether the obligation was fulfilled.
	ObligationResponse response = this.getHandler().fillObligation(fill);

	// handle the response
	this.handleResponse(response);

	// set schedule the next obligation
	ScheduledObligation o = this.requestNextObligation();
	this.scheduleEvent(o);

    }

    /**
     * Overrides the start method and schedules the next Obligation.
     */
    @Override
    public void start() {
	this.scheduleEvent(requestNextObligation());
    }

    /**
     * Schedule an Obligation within Mason
     * 
     * @param o
     *            The obligation which needs scheduling
     */
    public void scheduleEvent(ScheduledObligation o) {

	// if next obligation is null, then do not schedule another event.
	if (o == null || o.getObligation() == null) {
	    return;
	}

	// set the next obligation
	this.setNextObligation(o.getObligation());

	// schedule the obligation
	this.state.schedule.scheduleOnce(o.getScheduledTime(), this);
    }

    /**
     * Requests the next Obligation from the Contract.
     * 
     * @return returns an obligation to be scheduled.
     */
    public abstract ScheduledObligation requestNextObligation();

    /**
     * Stores the next Obligation to be scheduled
     * 
     * @param o
     *            The Obligation which will need processing
     */
    public void setNextObligation(Obligation o) {
	this.nextObligation = o;
    }

    /**
     * Gets the next obligation
     * 
     * @return the Obligation
     */
    public Obligation getNextObligation() {
	return nextObligation;
    }

    /**
     * Gets the SimState
     * 
     * @return the SimState
     */
    public SimState getState() {
	return state;
    }

    /**
     * Sets the SimState
     * 
     * @param state
     *            the SimState
     */
    public void setState(SimState state) {
	this.state = state;
    }

}
