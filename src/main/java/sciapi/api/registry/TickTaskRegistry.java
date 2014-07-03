package sciapi.api.registry;

import java.util.*;

import cpw.mods.fml.relauncher.Side;
import sciapi.api.basis.storage.IncPool;
import sciapi.api.mc.tick.*;

public class TickTaskRegistry {
	
	private static class TickTaskSup{
		public TickTaskSup(ITickTaskBase ttb, int pdelay, Side pside) {
			base = ttb;
			delay = pdelay;
			tickite = 0;
			side = pside;
		}
		
		public ITickTaskBase base;
		public Side side;
		public int delay;
		public int tickite;
	}
	
	private static List<TickTaskSup> sttasklist = new ArrayList();
	
	public static void registerTickStartTask(ITickStartTask task, Side side){
		registerTickStartTask(task, 1, side);
	}

	public static void registerTickStartTask(ITickStartTask task, int delay, Side side){
		sttasklist.add(new TickTaskSup(task, delay, side));
	}
	
	
	private static List<TickTaskSup> endtasklist = new ArrayList();
	
	public static void registerTickEndTask(ITickEndTask task, Side side){
		registerTickEndTask(task, 1, side);
	}

	public static void registerTickEndTask(ITickEndTask task, int delay, Side side){
		endtasklist.add(new TickTaskSup(task, delay, side));
	}
	
	
	public static void registerTickTask(ITickTask task, Side side){
		registerTickTask(task, 1, side);
	}

	public static void registerTickTask(ITickTask task, int delay, Side side){
		registerTickStartTask(task, delay, side);
		registerTickEndTask(task, delay, side);
	}
	
	
	private static List<TickTaskSup> schtasklist = new ArrayList();
	
	public static void registerScheduledTask(IScheduledTask task, int delay, Side side){
		schtasklist.add(new TickTaskSup(task, delay, side));
	}
	
	
	private static List<TimedPool> plist = new ArrayList();
	
	/**
	 * Register Pool which would be cleared every cycle.
	 * @param tclear tick for a cycle
	 * @param ip the Pool
	 * */
	public static void registerTimedPool(IncPool ip, int tclear, Side side){		
		registerTickStartTask(new TimedPool(ip), tclear, side);
	}
	
	private static class TimedPool implements ITickStartTask{
		public IncPool ip;
		
		public TimedPool(IncPool pip){
			ip = pip;
		}
		
		@Override
		public void tickStart() {
			ip.reset();
		}
	}
	
	
	public static void onTickStart(Side side){
		for(TickTaskSup sttask : sttasklist){
			if(sttask.side == side)
				if(sttask.tickite++ >= sttask.delay)
				{
					sttask.tickite = 0;
					((ITickStartTask)sttask.base).tickStart();
				}
		}
		
		for(TickTaskSup schtask : schtasklist){
			if(schtask.side == side)
				if(schtask.tickite++ >- schtask.delay)
				{
					schtask.tickite = 0;
					((IScheduledTask)schtask.base).onTask();
				}
		}
	}
	
	public static void onTickEnd(Side side){
		for(TickTaskSup endtask : endtasklist){
			if(endtask.side == side)
				if(endtask.tickite++ >= endtask.delay)
				{
					endtask.tickite = 0;
					((ITickEndTask)endtask.base).tickEnd();
				}
		}
	}
	
	/*public static void reset(Side side){
		Iterator<TickTaskSup> ite = sttasklist.iterator();
		
		while(ite.hasNext())
			if(ite.next().side == side)
				ite.remove();
		
		ite = schtasklist.iterator();
		
		while(ite.hasNext())
			if(ite.next().side == side)
				ite.remove();

		ite = endtasklist.iterator();
		
		while(ite.hasNext())
			if(ite.next().side == side)
				ite.remove();
		
		Iterator<TimedPool> ite2 = plist.iterator();
		
		while(ite.hasNext())
			if(ite.next().side == side)
				ite.remove();
	}*/
}
