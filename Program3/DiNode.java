/**
 * this class is where the value of the vertex
 * is contained, this class represents the vertex
 * and contains all its neighbors via parents and
 * children 
 * @author Allen McDermott
 * @since 12/13/2014
**/




public class DiNode {


	//global variables
	String name;

	String[] parents;
	String[] children;


	//constructor for the class
	//@param nam is the name of the vertex
	public DiNode(String nam) {
		name = nam;
		parents = new String[0];
		children = new String[0];
	}

	//clears children
	public void clearChildren() {
		children = new String[0];
	}

	//clears parents
	public void clearParents() {
		parents = new String[0];
	}

	//@return name of the vertex
	public String getName() {
		return name+"";
	}

	//@return the size of parents
	public int getParentSize() {
		return parents.length;
	}


	//@return size of children
	public int getChildSize() {
		return children.length;
	}

	//@return a string of all parents seperated by commas
	public String getParent() {
		String s = "";
		for(int i = 0; i<parents.length;i++) {
			s+=","+parents[i];
		}
		return s+"";
	}
	

	//@return a string of all children seperated by commas
	public String getChi() {
		String s ="";
		for(int i = 0; i<children.length;i++) {
			s+=","+children[i];
		}
		return s+"";
	}


	//@param parent to be added to parents
	public void addParent(String parent) {
		String[] temp = parents;

		parents = new String[temp.length+1];
		int j = temp.length;
		for(int i = 0; i<temp.length;i++) {
			
			parents[i] = temp[i];
		}
		parents[j]=parent;
	
	}



	//@param child to be added to children
	public void addChild(String child) {
		String[] temp = children;
		children = new String[temp.length+1];
		int j = temp.length;
		for(int i = 0; i<temp.length;i++) {
			children[i] = temp[i];
			
		}
		children[j]=child;
	}




	//@param parent to be removed from parents
	public boolean removeParent(String parent) {
		String[] temp = parents;
		int j = 0;
		for(int i = 0; i<temp.length;i++) {
			if(temp[i].equals(parent)) {
				temp[i] = null;
				j++;
			}
		}
	
		if(j==0) {
			return false;
		}
		parents = new String[temp.length-1];

		int ki = 0;
		for(int i = 0; i<temp.length;i++) {
			if(temp[i]==null) {
				ki--;
			}
			else {
				parents[ki]=temp[i];
			}
			ki++;
		}
		return true;
	}





	//@param child to be removed from children
	public boolean removeChild(String child) {
		String[] temp = children;
		int go = temp.length;
		int j = 0;

		for(int i = 0; i<temp.length;i++) {	
			if(temp[i].equals(child)) {
				temp[i] = null;
				j++;
			}
			
		}
	
		if(j==0) {
			return false;
		}
		children = new String[go-1];
		int ki = 0;
		for(int i = 0; i<temp.length;i++) {
			if(temp[i]==null) {
				ki--;
			}
			else {
				children[ki]=temp[i];
			}
			ki++;
		}

		return true;
	}



	//@param parent to see if it is in parents
	//@return true if parent was found false if not
	public boolean containsParent(String parent) {
		for(int i = 0; i<parents.length;i++) {
			if(parents[i] == parent) {
				return true;
			}
		}
		return false;
	}
			


	//@param child to see if it is in children
	//@return true if child was found false if not
	public boolean containsChild(String child) {
		for(int i = 0; i<parents.length;i++) {
			if(children[i] == child) {
				return true;
			}
		}
		return false;
	}


	//@return array containing children
	public String[] getChildren() {
		return children;
	}


	//@return array containing parents
	public String[] getParents() {
		return parents;
	}
	



}


















