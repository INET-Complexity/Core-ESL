package agent;

import inventory.Inventory;

/**
 * The Agent is a foundation class of ESL. All Agent's have balancesheets.
 * 
 * @author prauwolf
 *
 */
public class Agent {

    // The agent's balancesheet
    private Inventory inventory;

    // The agent's name.
    private String name;

    /**
     * Overloaded constructor generating an agent with no name.
     */
    public Agent() {
	this("");
    }

    /**
     * Constructor which sets the agent's name.
     * 
     * @param name
     *            The name of the agent.
     */
    public Agent(String name) {
	inventory = new Inventory();
	this.name = name;
    }

    /**
     * Returns the agent's inventory.
     * 
     * @return the agent's inventory.
     */
    public Inventory getInventory() {
	return inventory;
    }

    /**
     * Set's the agent's inventory. This method should rarely be employed.
     * 
     * @param inventory
     *            The agent's new inventory.
     */
    public void setInventory(Inventory inventory) {
	this.inventory = inventory;
    }

    /**
     * Get the agent's name.
     * 
     * @return the agent's name.
     */
    public String getName() {
	return this.name;
    }

    /**
     * Set the agent's name.
     * 
     * @param name
     *            the agent's name.
     */
    public void setName(String name) {
	this.name = name;
    }

}
