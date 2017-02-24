package org.java.org.economicsl.agent;

import org.java.org.economicsl.inventory.Inventory;

/**
 * The Agent is a foundation class of ESL. All Agent's have balancesheets.
 * 
 * @author prauwolf
 *
 */
public class Agent {

    // The org.economicsl.agent's balancesheet
    private Inventory inventory;

    // The org.economicsl.agent's name.
    private String name;

    /**
     * Overloaded constructor generating an org.economicsl.agent with no name.
     */
    public Agent() {
	this("");
    }

    /**
     * Constructor which sets the org.economicsl.agent's name.
     * 
     * @param name
     *            The name of the org.economicsl.agent.
     */
    public Agent(String name) {
	inventory = new Inventory();
	this.name = name;
    }

    /**
     * Returns the org.economicsl.agent's org.economicsl.inventory.
     * 
     * @return the org.economicsl.agent's org.economicsl.inventory.
     */
    public Inventory getInventory() {
	return inventory;
    }

    /**
     * Set's the org.economicsl.agent's org.economicsl.inventory. This method should rarely be employed.
     * 
     * @param inventory
     *            The org.economicsl.agent's new org.economicsl.inventory.
     */
    public void setInventory(Inventory inventory) {
	this.inventory = inventory;
    }

    /**
     * Get the org.economicsl.agent's name.
     * 
     * @return the org.economicsl.agent's name.
     */
    public String getName() {
	return this.name;
    }

    /**
     * Set the org.economicsl.agent's name.
     * 
     * @param name
     *            the org.economicsl.agent's name.
     */
    public void setName(String name) {
	this.name = name;
    }

}
