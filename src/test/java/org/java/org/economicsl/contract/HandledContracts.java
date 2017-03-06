package org.java.org.economicsl.contract;

import org.java.org.economicsl.contract.handler.ContractHandler;
import org.java.org.economicsl.contract.messages.FillObligation;
import org.java.org.economicsl.contract.messages.ObligationResponse;
import org.java.org.economicsl.inventory.Contract;

/**
 * HandledContracts require that Obligations in a org.economicsl.contract be handled by a
 * ContractHandler. As such, the user will need to specify the type of
 * ContractHandler which will be handle Contract Obligations
 * 
 * @author prauwolf
 *
 */
public abstract class HandledContracts extends Contract {

    private ContractHandler handler;

    /**
     * Constructor, which includes the name of the Contract, as well as the
     * ContractHandler.
     * 
     * @param name
     * @param handler
     */
    public HandledContracts(String name, ContractHandler handler) {
	super(name);
	this.handler = handler;
    }

    /**
     * Gets contractHandler
     * 
     * @return returns the ContractHandler for the Contract
     */
    public ContractHandler getHandler() {
	return handler;
    }

    /**
     * Sets the ContractHandler
     * 
     * @param handler
     *            the ContractHandler
     */
    public void setHandler(ContractHandler handler) {
	this.handler = handler;
    }

    /**
     * Sends an Obligation to the ContractHandler so that it can be filled. It
     * returns an ObligationResponse, informing the Contract as to whether the
     * Obligation was successfully fulfilled.
     * 
     * @param fill
     *            Obligation to filled
     * @return Response defining whether the Obligation was successfully
     *         fulfilled.
     */
    public ObligationResponse sendObligation(FillObligation fill) {
	return handler.fillObligation(fill);
    }

    /**
     * All HandledContracts must instantiate a handleResponse method. This
     * processes the consequences of an Obligation which was attempted to be
     * fulfilled
     * 
     * @param response
     *            The response received from the ContractHandler, regarding
     *            whether the Obligation was successfully fulfilled.
     */
    public abstract void handleResponse(ObligationResponse response);

}
