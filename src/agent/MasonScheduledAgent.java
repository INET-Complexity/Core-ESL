/**
 * Created by Torsten Heinrich
 */

package agent;

import sim.engine.SimState;
import sim.engine.Steppable;

import inventory.Inventory;
import agent.Agent;
import java.util.List;

public abstract class MasonScheduledAgent extends Agent implements Steppable{

	public MasonScheduledAgent(String name, SimState state) {
		super(name);
		this.scheduleEvent(state);
	}

	public void step(SimState state) {
		this.scheduleEvent(state);
	}

	public void scheduleEvent(SimState state) {
		Double eventTime = this.findNextEvent(state);
		if (eventTime != null) {
			state.schedule.scheduleOnce(eventTime, this);
		}		
	}
	
	public abstract Double findNextEvent(SimState state);
}
