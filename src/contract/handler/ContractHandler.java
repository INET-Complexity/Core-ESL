package contract.handler;

import contract.messages.FillObligation;
import contract.messages.ObligationResponse;
import contract.obligation.Obligation;

public abstract class ContractHandler {
	
	public final ObligationResponse fillObligation(FillObligation fill) {
	
		Obligation o = fill.getObligation();
		return handleObligation(o);
		
	}
	
	public abstract ObligationResponse handleObligation(Obligation o);
}
