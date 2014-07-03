package sciapi.api.mc.inventory;

import java.util.*;

import cpw.mods.fml.relauncher.Side;
import sciapi.api.mc.IMcManager;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.tick.ITickTask;

public class McInventoryManager implements ITickTask, IMcManager {
	
	private static EnumMap<Side, McInventoryManager> instance = new EnumMap(Side.class);
	private List<IMcInvManager> managerlist = new ArrayList();
	private boolean enabled = false;
	
	public static McInventoryManager getInstance(Side side) {
		if(!instance.containsKey(side))
			instance.put(side, new McInventoryManager());
		return instance.get(side);
	}
	
	public void addInvManager(IMcInvManager manager) {
		managerlist.add(manager);
	}
	

	@Override
	public void onLoad() {
		enabled = true;
	}

	@Override
	public void onUnload() {
		enabled = false;
	}
	
	@Override
	public void tickStart() {
		if(!enabled)
			return;
		
		InvIterator ite = getIterator();
		while(ite.hasNext())
			ite.next().onCheck();
		
		ite = getIterator();
		while(ite.hasNext())
			ite.next().getWorld().checkMove();
		
		ite = getIterator();
		while(ite.hasNext())
			ite.next().getWorld().checkInsert();
	}
	
	@Override
	public void tickEnd() {
		if(!enabled)
			return;
		
		InvIterator ite = getIterator();
		
		while(ite.hasNext())
			ite.next().getWorld().updateEntities();
		
		while(ite.hasNext())
			ite.next().notifyCheck();
	}
	
	
	public InvIterator getIterator() {
		return new InvIterator();
	}
	
	public boolean isEmpty() {
		if(managerlist.isEmpty())
			return true;
		
		for(IMcInvManager manager : managerlist)
			if(!manager.isEmpty())
				return false;
		
		return true;
	}
	
	
	public class InvIterator implements Iterator<McInventory> {
		
		int index;
		Iterator<McInventory> invite;
		
		public InvIterator() {
			index = 0;
			invite = null;
		}
		
		@Override
		public boolean hasNext() {
			if(isEmpty()) return false;
			if(invite == null) return true;
			if(invite.hasNext()) return true;
			
			for(int j = index + 1; j < managerlist.size(); j++)
				if(!managerlist.get(j).isEmpty())
					return true;
			
			return false;
		}

		@Override
		public McInventory next() {
			if(invite == null)
				invite = managerlist.get(index).getInvIterator();
			
			if(invite.hasNext())
				return invite.next();
			
			index++;
			while(index < managerlist.size()){
				if(!managerlist.get(index).isEmpty())
				{
					invite = managerlist.get(index).getInvIterator();
					return invite.next();
				}
				
				index++;
			}
			
			return null;
		}

		@Deprecated
		@Override
		public void remove() {
			invite.remove();
		}
		
	}
}
