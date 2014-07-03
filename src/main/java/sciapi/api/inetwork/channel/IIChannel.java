package sciapi.api.inetwork.channel;

import sciapi.api.basis.data.IDataTag;
import sciapi.api.inetwork.IDynamicConnection;

public interface IIChannel extends IDynamicConnection {
	/**
	 * Gets the identifier for this channel.
	 * */
	public IDataTag getIdentifier();
}
