package trees;

public interface ITree {
	
	public String search(String key);

	public void insert(String key,String value);

	public void delete(String key);

}