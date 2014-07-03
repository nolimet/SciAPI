package sciapi.api.heat;

import java.util.*;

import sciapi.api.abstraction.pos.*;

public class HeatComparator implements Comparator<IHeatComponent>{

	@Override
	public int compare(IHeatComponent o1, IHeatComponent o2) {
		if(o1.getPosition().equals(o2.getPosition()))
			return 0;
		else if(o1.hashCode() > o2.hashCode())
			return 1;
		else return -1;
	}

}
