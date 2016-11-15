package endtoend;

import java.util.ArrayList;
import java.util.List;

import agent.Agent;
import contract.examples.FixedBond;
import contract.examples.RandomTransfers;
import contract.handler.AutomaticContractHandler;
import inventory.Good;
import sim.engine.SimState;

public class BondRun extends SimState {

	public BondRun(long seed)
	{
		super(seed);
	}
	
	 public void start(){
         
		super.start(); // reuse the SimState start method
		
		List<Agent> governments = new ArrayList<Agent>();
		List<Agent> buyers = new ArrayList<Agent>();
		
		for (int i = 1; i <= 2; i++) {
			Agent a = new Agent("Government " + i);
			a.getInventory().add(new Good("cash", 1000000.0));
			governments.add(a);
		}
		
		for (int i = 1; i <= 2; i++) {
			Agent a = new Agent("Buyer " + i);
			a.getInventory().add(new Good("cash", 10000.0));
			buyers.add(a);
		}
		
		this.purchaseBonds(governments, buyers);
		
	}
	public static void main(String[] args)
	{
		doLoop(BondRun.class, args);
		System.exit(0);
	}
	
	private void purchaseBonds(List<Agent> governments, List<Agent> buyers) {
		
		AutomaticContractHandler handler = new AutomaticContractHandler();
		
		for (int i = 0; i < governments.size(); i++) {
			
			for (int j = 0; j < buyers.size(); j++) {
				
				FixedBond contract = new FixedBond("bond",this, handler, 
						governments.get(i), buyers.get(j), new Long(1000), 
						new Long(30), 24, "cash", 1.0);
				
			}
		}
	}

}
