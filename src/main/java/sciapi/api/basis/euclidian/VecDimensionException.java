package sciapi.api.basis.euclidian;

public class VecDimensionException extends RuntimeException {
	public VecDimensionException(IEVector v1, IEVector v2, String proc){
		super(v1 + " doesn't have the same dimension with: " +  v2
				+ ", so can't run this process: " + proc + ".\n"
				+ v1 + " have dimension: " + v1.getDimension() + ", "
				+ v2 + " have dimension: " + v2.getDimension() + "\n");
	}
	
	public VecDimensionException(IEVector v, int dim, String proc){
		super(v + " doesn't have the dimension: " +  dim
				+ ", so can't run this process: " + proc + ".\n"
				+ v + " have dimension: " + v.getDimension() + "\n");
	}
}
