package sciapi.api.mc;


public interface ITileConnection {
	public ITile getBTile();
	public ITile getFTile();
	
	public ITile getAnotherTile(ITile tile);
}
