package sciapi.api.heat;

import java.util.Map;

import sciapi.api.abstraction.pos.*;
import sciapi.api.basis.math.BMath;

public class HeatCompCalcUnit {
	public HeatSystem parhsys;
	public IHeatComponent hc;
	public double heatAmount;
	
	public HeatCompCalcUnit(HeatSystem phsys, IHeatComponent obj) {
		parhsys = phsys;
		hc = obj;
	}

	/**
	 * Applies External Heat.
	 * */
	public void apply(double heat){
		heatAmount += heat;
	}
	
	public void init(){
		heatAmount = 0.0;
	}
	
	/**
	 * Calculate Heat of this Component.
	 * */
	public void calc(){		
		double r1, r2, h;
		
		IDiscreteWorld dw = (IDiscreteWorld) hc.getWorld();
		IHeatComponent phc;
		
		if(hc instanceof ISpHeatComponent) {
			for(IDirection dir : dw.getPossibleDirections()){
				if((phc = ((ISpHeatComponent) hc).getNeighborComponent(dir)) != null){
					if(hc.isHeatTransferable(dir) && phc.isHeatTransferable(dir.getOpposite())){
						if(phc.getTemperature() == hc.getTemperature())
							continue;
						HeatCompCalcUnit hccu = parhsys.getHCCU(phc);
						r1 = hc.getHeatTransferRate(dir);
						r2 = phc.getHeatTransferRate(dir.getOpposite());
							
						h = ((r1 * r2) / (r1 + r2)) * (phc.getTemperature() - hc.getTemperature());
						
						this.apply(h);
					}
				}
			}
		}
		else {
			for(IDirection dir : dw.getPossibleDirections()){
				if((phc = getOnPosition(dw, hc.getPosition().getDiffPos(dir.toDifference()))) != null){
					if(hc.isHeatTransferable(dir) && phc.isHeatTransferable(dir.getOpposite())){
						if(phc.getTemperature() == hc.getTemperature())
							continue;
						HeatCompCalcUnit hccu = parhsys.getHCCU(phc);
						r1 = hc.getHeatTransferRate(dir);
						r2 = phc.getHeatTransferRate(dir.getOpposite());
						
						h = ((r1 * r2) / (r1 + r2)) * (phc.getTemperature() - hc.getTemperature());
						
						this.apply(h);
					}
				}
			}
		}
		
		
		Map<IAbsPosition, Double> map = hc.getExternalTransferList();
		
		if(map == null)
			return;
		
		for(Map.Entry<IAbsPosition, Double> e: map.entrySet()){
			if((phc = getOnPosition(dw, e.getKey())) != null){
				parhsys.getHCCU(phc).apply(e.getValue());
			}
		}
	}
	
	public void apply(){
		if(heatAmount == 0)
			return;
		hc.onHeatTransfer(heatAmount);
	}
	
	public static IHeatComponent getOnPosition(IDiscreteWorld world, IAbsPosition pos){
		IPosObject pobj;
		if((pobj = world.getObjonPos(pos)) == null)
			return null;
		if(!(pobj instanceof IHeatComponent))
			return null;
		
		return (IHeatComponent)pobj;
	}
}
