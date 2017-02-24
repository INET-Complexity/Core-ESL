package org.java.org.economicsl.inventory;

import sim.engine.SimState;

/**
 * Created by taghawi on 10/21/16.
 */
public abstract class Contract extends Item {

    /**
     * Constructor.
     * 
     * @param name
     *            Name of the Contract
     */
    public Contract(String name) {
	super(name);
    }

    /**
     * The start method must be instantiated in order to start the org.economicsl.contract
     */
    public abstract void start(SimState state);

    /**
     * Adds this org.economicsl.contract to another org.economicsl.contract and returns a org.economicsl.contract
     * 
     * @param c
     *            The org.economicsl.contract to add to this org.economicsl.contract
     * @return A new Contract comprised of the addition of this and another
     *         org.economicsl.contract.
     */
    public abstract Contract addition(Contract c);

}
