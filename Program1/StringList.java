


interface StringList extends Iterable<String>
{
	public  int getSize();
		// returns n, the number of strings in the list: O(1).
	public String getElementAt(int index);
		// returns the string at the specified index (0.. n-1): O(n).
	public boolean add(String s);
		// adds a new string to the list and returns true if there is a change: O(n).
	public void addAll(StringList others);
		// merges others into this list: O(n + |others|).**/		
	public boolean delete(String target);
		// deletes the specified string and returns true if there is a change: O(n).
	public void deleteAll(StringList others);
		// deletes all strings in others from this list: O(n + |others|).**/
	public boolean contains(String target);
		// searches for the target: O(n).
	public void clear();
		// resets the list to empty: O(1).
}
/**public class StringList 
{
	private node head;
	private node tail;

	public StringList() 
	{
		head = null;
		tail = head;
	}
	public  int getSize()
	{
		// returns n, the number of strings in the list: O(1).
		int count = 0;
      		node p = head;     
		while (p != null)
       		{
           		count ++;
          		p = p.next;
       		}
       		return count;
	}
	public String getElementAt(int index)
	{
		// returns the string at the specified index (0.. n-1): O(n).
		int count = 0;
		node p = head;
		while(p.next != null&&count<index)
		{
			count++;
			p=p.next;
		}
		return p.getData();
	}
	public boolean add(String s)
	{
		// adds a new string to the list and returns true if there is a change: O(n).
		if (tail != null)
		{
			tail.setNext(new node(s));
			tail = tail.getNext();		
		}
		else
		{
			tail = new node(s);
			head = tail;
			return true;
		}
		return false;
	}
	/**public void addAll(StringList others);
		// merges others into this list: O(n + |others|).**/
/**				
	public boolean delete(String target)
	{
		// deletes the specified string and returns true if there is a change: O(n).
		int count = 0;
		node p = head;
		while(p.next != null&&!p.getData().equals(target))
		{
			count++;
			p=p.next;
		}
		if(p.getData().equals(target)) {
			node next = p.getNext();
			node prev = p.getPrev();
			prev.setNext(next);
			next.setPrev(prev);
			return true;
		}
		return false;
	}
	/**public void deleteAll(StringList others);
		// deletes all strings in others from this list: O(n + |others|).**/
	/**public boolean contains(String target)	
	{
		// searches for the target: O(n).
		int count = 0;
		node p = head;
		while(p.next != null&&!p.getData().equals(target))
		{
			count++;
			p=p.next;
		}
		if(p.getData().equals(target))
		{
			return true;
		}
		return false;
	}
	public void clear()
	{
		// resets the list to empty: O(1).
		head = null;
		tail = null;
	}
}**/
