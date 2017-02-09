package contract.messages;

import contract.obligation.Obligation;

/**
 * FillObligation contains information necessary for requesting the fulfillment
 * of an Obligation.
 * 
 * @author prauwolf
 *
 */
public class FillObligation {

    // The obligation which requires fulfilling.
    private Obligation o;

    public FillObligation(Obligation o) {
	this.o = o;
    }

    /**
     * Returns the obligation which requires fulfilling.
     * 
     * @return the obligation.
     */
    public Obligation getObligation() {
	return o;
    }

    /**
     * Sets the obligation which requires fulfilling.
     * 
     * @param o
     *            the obligation.
     */
    public void setObligation(Obligation o) {
	this.o = o;
    }

}
