package sciapi.api.inetwork;

/**
 * Actual entity to access the network component.
 * An entity can have less than one component for each network.
 * */
public interface IINEntity {
	/** Gets the Identifier to identify this entity. */
	public IINCIdentifier getIdentifier();
}
