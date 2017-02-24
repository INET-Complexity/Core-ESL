package agent;

import sim.engine.SimState;
import sim.engine.Steppable;

import inventory.Inventory;
import agent.Agent;
import java.util.List;

/**
 * MasonScheduledAgent are a subclass of Agent, which allows to have events
 * for the agent scheduled in Mason.
 * 
 * @author Torsten Heinrich (github.com/x0range)
 */
public abstract class MasonScheduledAgent extends Agent implements Steppable{

    /**
     * Constructor which sets the agent's name and calls scheduleEvent to 
     * schedule the first event.
     * 
     * @param name
     *            the agent's name.
     * @param state
     *            The Mason SimState - contains the scheduler
     */
	public MasonScheduledAgent(String name, SimState state) {
		super(name);
		this.scheduleEvent(state);
	}

    /**
     * Implementation of the Steppable interface in Mason. This is the method
     * that is called when the Mason scheduler calls the Contract. The method 
     * will generally be overridden by classes inheriting from this one.
     * 
     * @param state
     *            The Mason SimState - contains the scheduler
     */
	public void step(SimState state) {
		this.scheduleEvent(state);
	}

    /**
     * Schedule an Event within Mason. The time for this event is
     * obtained from the findNextEvent function which needs to be
     * implemented by classes inheriting from this one.
     * 
     * @param state
     *            The Mason SimState - contains the scheduler
     */
	public void scheduleEvent(SimState state) {
		Double eventTime = this.getNextEventTime(state);
		if (eventTime != null) {
			state.schedule.scheduleOnce(eventTime, this);
		}		
	}

    /**
     * Finds the next event.
     * 
     * @param state
     *            The Mason SimState - contains the scheduler
     * 
     * @return returns the time at which the next event is to take place,
     *         i.e. when the agent's step function should next be called.
     */	
	public abstract Double getNextEventTime(SimState state);
}
