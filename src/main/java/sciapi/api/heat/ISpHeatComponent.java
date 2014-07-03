package sciapi.api.heat;

import sciapi.api.abstraction.pos.IDirection;

public interface ISpHeatComponent extends IHeatComponent {
	
	/**
	 * <p>Get Neighbor Component of this Component, following the direction. </p>
	 * <p>This method is for performance. Just return null if not needed.</p>
	 * @param dir the direction.
	 * @return the neighbor component.
	 * */
	public IHeatComponent getNeighborComponent(IDirection dir);
	
}
