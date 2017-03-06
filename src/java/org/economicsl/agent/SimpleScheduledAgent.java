/**
 * Created by Torsten Heinrich
 */

package org.economicsl.agent;

import sim.engine.SimState;
import sim.engine.Steppable;


public abstract class SimpleScheduledAgent extends Agent implements Steppable {

    public SimpleScheduledAgent(String name, SimState state) {
        super(name);
        this.scheduleEvent(state);
    }

    public void step(SimState state) {
        this.scheduleEvent(state);
    }

    public void scheduleEvent(SimState state) {
        Double eventTime = this.scheduleNextEvent(state);
        if (eventTime != null) {
            state.schedule.scheduleOnce(eventTime, this);
        }
    }

    public abstract Double scheduleNextEvent(SimState state);
}