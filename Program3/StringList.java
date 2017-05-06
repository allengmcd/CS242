
interface StringList extends Iterable<String>
{
	public  int getSize();
		// returns n, the number of strings in the list: O(1).
	public String getElementAt(int index);
		// returns the string at the specified index (0.. n-1): O(n).
	public boolean add(String s);
		// adds a new string to the list and returns true if there is a change: O(n).
	public void addAll(StringList others);
		// merges others into this list: O(n + |others|).		
	public boolean delete(String target);
		// deletes the specified string and returns true if there is a change: O(n).
	public void deleteAll(StringList others);
		// deletes all strings in others from this list: O(n + |others|).
	public boolean contains(String target);
		// searches for the target: O(n).
	public void clear();
		// resets the list to empty: O(1).
}
