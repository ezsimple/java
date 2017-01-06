package ion.net.common.db;

public abstract class DbDao implements Dao {
	protected abstract void initDao();
	protected abstract void bindDao();
	public void construct(){
		initDao();
		bindDao();
	}
}
