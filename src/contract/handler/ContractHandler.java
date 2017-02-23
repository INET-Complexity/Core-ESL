package contract.handler;

import contract.messages.FillObligation;
import contract.messages.ObligationResponse;
import contract.obligation.Obligation;

/**
 * ContractHandlers receive Obligation requests from a contract, attempt to
 * fulfill the requests, then send the outcome of the request back to the
 * Contract. ContractHandler is an abstract class which requires that the user
 * instantiates handleObligation.
 * 
 * @author prauwolf
 *
 */
public abstract class ContractHandler {

    /**
     * This is the method a Contract uses to request the fulfillment of an
     * Obligation. The way a ContractHandler resolves an Obligation is by
     * calling the abstract method 'handleObligation'. Once an attempt has been
     * made to fulfill the response, an ObligationResponse is returned,
     * expressing whether the Obligation was successfully handled.
     * 
     * @param fill
     * @return
     */
    public final ObligationResponse fillObligation(FillObligation fill) {

	Obligation o = fill.getObligation();
	return handleObligation(o);

    }

    /**
     * This method handles an Obligation and returns an ObligationResponse. It
     * is abstract because different ContractHandlers may handle Obligation
     * requests in unique manners. An AutomaticContractHandler might try to
     * automatically fulfill an Obligation by manipulating the necessary
     * balanceSheets. However, a less invasive Contracthandler may request that
     * an Agent deal with the Obligation.
     * 
     * @param o
     *            The obligation which requires handling.
     * @return Defines whether the obligation was successfully fulfilled.
     */
    public abstract ObligationResponse handleObligation(Obligation o);
}
