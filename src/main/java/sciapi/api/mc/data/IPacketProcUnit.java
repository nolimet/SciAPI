package sciapi.api.mc.data;

import sciapi.api.basis.data.*;

public interface IPacketProcUnit {
	public void process(IDataMap datamap) throws PacketProtocolException;
	
	public DataTrFormSet getDataTrFormSet();
}
