/**
 * The LinkedList class is a custom linkedList and uses custom class node for the nodes
 * <br><br>
 * @author Allen McDermott
 * @since 10/15/14
 */
public  class LinkedList
{
	/**
	 * global variables:
	 * head is the head node
	 * tail is the last node in the linkedList
	**/
	private node head;
	private node tail;


	/**
	 * LinkedList is the constructor for the class LinkedList 
	 * this constructor creates a empty linkedlist by setting head and tail
	 * to null
	 **/
	public LinkedList() 
	{
		head = null;
		tail = head;
	}


	/**
 	 * getSize will go threw the linkedList and cound how many nodes there are and then outputs
	 * that number however this method is redundent because set tracks the items already
	 * @return the amount of nodes in the linkedList
	**/
	public  int getSize()
	{
		// returns n, the number of strings in the list: O(n).
		int count = 0;
      		node p = head;     
		while (p != null)
       		{
           		count ++;
          		p = p.next;
       		}
       		return count;
	}

	

	/**
	 * getElementAt finds the element at int index and returns that item
	 * @param index is the index of the item that we are looking for
	 * @return the element at index of type String
	**/
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


	/**
	 * the add method will apend String s at the end of the linkedList and
	 * return true no matter what
	**/ 
	public boolean add(String s)
	{
		// adds a new string to the list and returns true if there is a change: O(n).
		if (head == null)
		{
			head = new node(s,null);
			head.setNext(tail);
			tail = head;	
			return true;	
		
		}
		else
		{	
			node temp = new node(s,null);
			tail.setNext(temp);
			tail = tail.getNext();
			
			return true;
		}
		
	}



	//this method was never used in this class but i decided not to delete it 
	/**public void addAll(StringList others);
		// merges others into this list: O(n + |others|).**/
	



	/**
	 * delete will remove String target  if target is found in the linkedList and return true
	 * if target is not found it will return false
	 * @param String target is the item that will be searched for and removed
	 * @return boolean true if item was found and removed, false if item wasn't found
	**/  
	public boolean delete(String target)
	{
		// deletes the specified string and returns true if there is a change: O(n).
		node temp = head;
		if((head.getData()+"").equals(target)) {
			head = null;
			head = temp.getNext();
			return true;
			
		}
		
		else {
			while(temp.getNext()!=null && !(temp.getNext().getData()+"").equals(target)) {
				temp = temp.getNext();
			}
		}
		
		if(target.equals(tail.getData()+"")) {
			tail = null;
			tail = temp;
			return true;
		}
		if(temp.getNext()!=null){
			if((temp.getNext().getData()+"").equals(target)) {
				node rep = temp;	
				rep.setNext(temp.getNext().getNext());	
				temp=null;			
				return true;
			}
		}
		return false;
	}

	//like addAll this method was never used but i never deleted it 
	/**public void deleteAll(StringList others);
		// deletes all strings in others from this list: O(n + |others|).**/




	/**
	 * contains will go threw the linked list and return true if it found String target
	 * if target was not found then it will return false
	 * @param target is the item that is being searched for
	 * @return true if target was found, false if not
	**/
	public boolean contains(String target)	
	{
		// searches for the target: O(n).
		if(head==null) {
			return false;
		}
		int count = 0;
		node p = head;
		while(p.next != null&&!(p.getData()+"").equals(target))
		{
			p=p.next;
		}
		if(p!=null&&(p.getData()+"").equals(target))
		{
			return true;
		}
		return false;
	}


	/**
	 * clear sets head and tail to null making the linkedList empty
	 * keep in mind no matter how big the linkedList is it will also have
	 * the same run time
	**/
	public void clear()
	{
		// resets the list to empty: O(1).
		head = null;
		tail = null;
	}


	/** 
	 * this method was used for testing but is not used for the main function
	 * of this class
	 * this method prints the data of each node
	**/
	public void getData() {
		int count = 0;
		node p = head;
		while(p.next.next != null)
		{
			count++;
			System.out.println(p.getData());
			p=p.next;
		}
	}
}
