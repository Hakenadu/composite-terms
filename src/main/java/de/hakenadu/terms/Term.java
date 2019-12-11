package de.hakenadu.terms;

import de.hakenadu.terms.visitor.Visitor;

/**
 * Every node in the composite is an instance of this interface.
 * 
 * @author Manuel Seiche
 * @since 11.12.2019
 */
public interface Term {

	<C> void accept(Visitor<C> visitor, C context);
}
