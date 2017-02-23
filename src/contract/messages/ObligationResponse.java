package contract.messages;

import contract.obligation.Obligation;

/**
 * Once an attempt has been made to fulfill an Obligation, the
 * ObligationResponse informs a contract whether the Obligation was successfully
 * fulfilled.
 * 
 * @author prauwolf
 *
 */
public class ObligationResponse {

    // The Obligation which was potentially fulfilled.
    private Obligation obligation;

    // Was the Obligation successfully fulfilled?
    private Boolean filled;

    public ObligationResponse(Obligation o, Boolean filled) {
	this.obligation = o;
	this.filled = filled;
    }

    public Obligation getObligation() {
	return obligation;
    }

    public void setObligation(Obligation obligation) {
	this.obligation = obligation;
    }

    public Boolean getFilled() {
	return filled;
    }

    public void setFilled(Boolean filled) {
	this.filled = filled;
    }

}
