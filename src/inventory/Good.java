package inventory;

/**
 * Created by taghawi on 10/21/16.
 */
public class Good extends Item {

    // Each good on the inventory has a quantity.
    private double quantity;

    public Good(String name, double quantity) {
	super(name);
	this.quantity = quantity;
    }

    /**
     * Gets the current quantity
     * 
     * @return the current quantity.
     */
    public double getQuantity() {
	return this.quantity;
    }

    /**
     * Sets a new quantity for the Good
     * 
     * @param quantity
     *            The new quantity.
     */
    public void setQuantity(double quantity) {
	this.quantity = quantity;
    }

}