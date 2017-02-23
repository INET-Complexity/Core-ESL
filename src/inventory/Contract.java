package inventory;

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
     * The start method must be instantiated in order to start the contract
     */
    public abstract void start();

    /**
     * Adds this contract to another contract and returns a contract
     * 
     * @param c
     *            The contract to add to this contract
     * @return A new Contract comprised of the addition of this and another
     *         contract.
     */
    public abstract Contract addition(Contract c);

}
