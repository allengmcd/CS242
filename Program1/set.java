import java.util.Iterator;

/**
 * The set class uses a custom linkedList to do tasks from the StringList interface
 * also MAKE SURE TO READ THE COMMENTS UNDER DELETE ALL FOR CLARIFICATION!!!!
 * <br><br>
 * @author Allen McDermott
 * @since 10/15/14
 */

public class set implements StringList
{

	/**
	 * Global variables:
	 * list is a LinkedList object from the custom linkedList class
	 * size is an int that tracks the size of the linkedList
	**/
	public LinkedList list;
	public int size;
	

	/**
	 * set is a constructor for class set
	 * creates an empty linkedList
	**/
	public set() {
		list = new LinkedList();
	}


	/**
	 * getSize returns global variable size (which tracks the size of list)
	 * @return int size
	 */
	public int getSize() {
		return size;
	}


	/**
	 * getElementAt calls the method getElementAt which simply finds the element at a certain index
	 * @param int index is the index of the element that we are looking for
	 * @return String k is the element that was found at index
	**/ 
	public String getElementAt(int index) {
		String k = list.getElementAt(index+0)+"";
		return k;
	}


	/**
	 * add calls add from the linkedList class which will apend String s to the LinkedList
	 * note: this method also uses the method contains to check if that element already exists
	 * if the element is already in the linkedList it will not be added to the linkedList
	 * @param String s which will try to be added to the linkedList if it isn't already
	 * @return boolean true if item was added false if item was already in the linkedList or if s was null
	**/ 
	public boolean add(String s) {
		if(list.contains(s)==true||s==null)
			return false;	
		list.add(s);
		size++;	
		return true;
	}
	


	/**
	 * delete calls method delete from the linkedList class and will return true if String target
	 * was found and deleted, false if not
	 * @param String target is the item that will be removed from the linkedList
	 * @return boolean value true if target was deleted, false if not
	**/ 
	public boolean delete(String target) {
		if(target==null)
		{ return false; }
		boolean k = list.delete(target);
		if(k==true){
			size = size -1;
		}
		return k;
	}


	/**
	 * contains calls the contains method from the linkedList class it will return true if String 
	 * target was found and false if not
	 * @param String target is the target the will be searched for in the linkedList
	 * @return true if the value was found, false if not
	**/ 
	public boolean contains(String target) {
		boolean k = list.contains(target);
		return k;
	}


	/**
	 * clear calls the method clear from the linkedList class and resets the size to 0
	 * there are no param or return in this method
	**/
	public void clear() {
		list.clear();
		size=0;
	}


	/**
	 * addAll calls the method add and getElementAt from the linkedList class it will take in 
	 * another linked list and apend it to list or the current linkedList
	 * it does this by going threw each value in s and then adding it to list
	 * @param StringList s is a linkedList that will be added to list
	**/ 
	public void addAll(StringList s) {
		int k = s.getSize();
		for(int i=0;i<k;i++) {
			String l = s.getElementAt(i);
			add(l);
		}
			
		
	}


	/**
	 * deleteAll calls the delete and getElementAt methods from the linkedList class
	 * it deletes all the items from Stringlist s and from list
	 * IMPORTANT!!!!! THIS METHOD DELETES ALL THE ITEMS THAT ARE IN STRINGLIST S FROM
	 * LIST(the global linkedList) AS WELL AS ALL THE ITEMS IN STRINGLIST S!!!!!!!!!!!
	**/ 
	public void deleteAll(StringList s) {
		int k = s.getSize();
		for(int i=0;i<k;i++) {
			String l = s.getElementAt(i);
			delete(l);			
		}
	
	}



	/**
	 * iterator is not used for this class however it is ecsential for this class to run
	 * this is why it returns a null Iterator no matter what
	 * @return t which is a null Iterator
	**/
	public Iterator iterator() {
		Iterator i = new Iterator() {
			int index = 0;

		    
			public boolean hasNext() {
				if(list.getElementAt(index)!=null&&index<size) {
					return true;
				}
				else {
					return false;
				}
			}


			public String next() {
				return list.getIndexAt(index++);
			}

		}
		return i;
	}
	
	
	
}
