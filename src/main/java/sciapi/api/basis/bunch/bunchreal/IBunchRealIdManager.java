package sciapi.api.basis.bunch.bunchreal;

public interface IBunchRealIdManager<S extends IBunchRealIdType> {
	public S getType(int id);
}
