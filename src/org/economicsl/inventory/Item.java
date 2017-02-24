package org.economicsl.inventory;

/**
 * Item is the parent class for all objects which go on an org.economicsl.inventory. This
 * includes goods and contracts.
 * 
 * @author prauwolf
 *
 */
public class Item {

    // name of the item.
    private String name = new String();

    public Item(String name) {
	this.name = name;
    }

    /**
     * Get name
     * 
     * @return name of the item.
     */
    public String getName() {
	return this.name;
    }

    /**
     * Set name.
     * 
     * @param name
     *            The item's new name.
     */
    public void setName(String name) {
	this.name = name;
    }
}
