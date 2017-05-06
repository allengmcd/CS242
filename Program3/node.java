/**
 * this class acts as a node for
 * the graph class and contains variables
 * for the color, the next node, dist, and the
 * vertex which contains the string
 * @author Allen McDermott
 * @since 12/13/2014
**/


public class node {
	

	//global variables
	node next;
	String color;
	DiNode vertex;
	int dist;


	//constructor for node
	//@param pull is the DiNode that is being added as the vertex
	public node(DiNode pull) {
		next = null;
		vertex = pull;
		color = "white";
		dist=0;
	}

	//@return parents length
	public String[] getPar() {
		return vertex.getParents();
	}

	//@return children length
	public String[] getChi() {
		return vertex.getChildren();
	}
	
	//@param parent to be deleted
	public void deletePar(String par) {
		vertex.removeParent(par);
	}


	//@param child to be deleted
	public void deleteChi(String chi) {
		vertex.removeChild(chi);
	}


	//clears children
	public void clearChi() {
		vertex.clearChildren();
	}


	//clears parents
	public void clearPar() {
		vertex.clearParents();
	}

	//@param rep will replace current vertex
	public void setVertex(DiNode rep) {
		vertex = rep;
	}

	//@param node that will take the value of next
	public void setNext(node k) {
		next = k;
	}


	//@return the next node
	public node getNext() {
		return next;
	}


	//@return DiNode of current vertex
	public DiNode getVertex() {
		return vertex;
	}


	//@return String of the vertex
	public String getData() {
		return vertex.getName()+"";
	}


	//@param s is going to be the new color of the node
	public void setColor(String s) {
		color = s;
	}
	
	
	//@return current color of the node
	public String getColor() {
		return color;
	}


	//@return distance vertex is from the root
	public int getDist() {
		return dist;
	}
	
	//@param i is the new distance of the vertex
	public void setDist(int i) {
		dist = i;
	}

}
