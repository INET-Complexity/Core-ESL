package org.java.org.economicsl.inventory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

/**
 * Created by taghawi on 10/21/16. edited by prauwolf 12/09/16.
 */
public class Inventory {
    Set<Item> items = new HashSet<Item>();

    /**
     * Adds an item of type Good or Contract to the Inventory
     * 
     * @param item
     *            the item to add to the org.economicsl.inventory.
     * @throws Exception
     *             If the Item is not of type Good or Contract, and error will
     *             be thrown.
     */
    public void add(Item item) throws Exception {
	if (item instanceof Good) {
	    Good g = (Good) item;
	    this.add(g);
	} else if (item instanceof Contract) {
	    Contract c = (Contract) item;
	    this.add(c);
	} else {
	    throw new Exception("Type of Item is not handled by the Inventory");
	}
    }

    /**
     * Private method called from add(Item). Adds an Item to the BalanceSheet.
     * This method is protected to maintain stock-flow consistency.
     * 
     * @param item
     *            the Item to add
     * @return returns a boolean regarding whether the add was successful.
     * @throws Exception
     */
    private void add(Good good) throws Exception {

	// check to see if Good has a quantity greater than zero.
	if (good.getQuantity() < 0.0) {
	    throw new Exception("When adding a good, the quantity must be greater than zero. The current good: "
		    + good.getName() + " has a quantity of: " + good.getQuantity());
	}

	// Check whether there are any existing goods which match the name of
	// this good.
	List<Good> matches = items.stream().filter(t -> t instanceof Good && t.getName().equals(good.getName()))
		.map(Good.class::cast).collect(Collectors.toList());

	// If there are no matches, then add the good to the Set Items.
	if (matches.size() == 0) {
	    items.add(good);
	    // If there is one match, then add the quantity of the good to the
	    // current good.
	} else if (matches.size() == 1) {
	    matches.get(0).setQuantity(matches.get(0).getQuantity() + good.getQuantity());
	    // If there are more than one match, then the integrity of the
	    // Inventory has been compromised. This should never occur.
	} else {
	    throw new Exception("There is more than one good in the org.economicsl.inventory with the name: " + good.getName()
		    + ". This is illegal");
	}
    }

    /**
     * Private method called from add(Item) which attempts to add a org.economicsl.contract to
     * the Item set.
     * 
     * @param contract
     *            the org.economicsl.contract to add
     * @throws Exception
     *             Exception thrown if org.economicsl.contract cannot be added to the set.
     */
    private void add(Contract contract) throws Exception {
	if (this.items.contains(contract)) {
	    throw new Exception("cannot add org.economicsl.contract that is already present");
	} else {
	    this.items.add(contract);
	}
    }

    /**
     * Removes a Good or a Contract from the Inventory
     * 
     * @param item
     *            The item to be removed.
     * @throws Exception
     *             Throws an exception if the item is not a Good or Contract
     */
    public void remove(Item item) throws Exception {
	if (item instanceof Good) {
	    Good g = (Good) item;
	    this.remove(g);
	} else if (item instanceof Contract) {
	    Contract c = (Contract) item;
	    this.remove(c);
	} else {
	    throw new Exception("Type of Item is not handled by the Inventory");
	}

    }

    private void remove(Good good) throws Exception {

	// check to see if Good has a quantity greater than zero.
	if (good.getQuantity() < 0.0) {
	    throw new Exception("When removing a good, the quantity must be greater than zero. The current good: "
		    + good.getName() + " has a quantity of: " + good.getQuantity());
	}

	// Check whether there are any existing goods which match the name of
	// this good.
	List<Good> matches = items.stream().filter(t -> t instanceof Good && t.getName().equals(good.getName()))
		.map(Good.class::cast).collect(Collectors.toList());

	// If there are no matches, then the good cannot be removed, since it
	// doesn't exist.
	if (matches.size() == 0) {

	    throw new Exception(
		    "The org.economicsl.inventory does not contain the good: " + good.getName() + ". So, it cannot be removed.");

	    // If there is one match, then remove the quantity of the good from
	    // the current good.
	} else if (matches.size() == 1) {
	    Good existingGood = matches.get(0);
	    if (existingGood.getQuantity() < good.getQuantity()) {
		throw new Exception("The current quantity of Good: " + good.getName() + " is "
			+ existingGood.getQuantity() + ". That is not enough to fulfill the request for a quantity of: "
			+ good.getQuantity());
	    } else {
		existingGood.setQuantity(existingGood.getQuantity() - good.getQuantity());
	    }

	    // If there are more than one match, then the integrity of the
	    // Inventory has been compromised. This should never occur.
	} else {
	    throw new Exception("There is more than one good in the org.economicsl.inventory with the name: " + good.getName()
		    + ". This is illegal");
	}
    }

    private void remove(Contract contract) {
	this.items.remove(contract);

    }

    public List<Good> getAllGoodEntries() {
	return items.stream().filter(t -> t instanceof Good).map(Good.class::cast).collect(Collectors.toList());

    }

    /**
     * Returns a good queried by name if it exists in the Inventory.
     * 
     * @param name
     *            the name of the good
     * @return the returned good.
     * @throws Exception
     */
    public Good getGood(String name) throws Exception {
	List<Good> matched = this.getAllGoodEntries().stream().filter(g -> g.getName().equals(name))
		.collect(Collectors.toList());

	if (matched.size() == 0) {
	    return null;
	} else if (matched.size() > 1) {
	    throw new Exception("More than one Good of that name was discovered");
	} else {
	    return matched.get(0);
	}

    }

    /**
     * Calculates the net value for the org.economicsl.inventory
     * 
     * @param parameters
     * @param value_functions
     * @return
     */
    public double net_value(Map<Class, List<Object>> parameters,
	    Map<Class, ToDoubleBiFunction<Item, List<Object>>> valuationMap) {

	Double nv = 0.0;

	for (Item i : this.items) {
	    nv += valuationMap.get(i.getClass()).applyAsDouble(i, parameters.get(i.getClass()));
	}

	return nv;
    }

}
