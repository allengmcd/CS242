
/**
 * This is class graph it does all of the required 
 * work for program3 it uses class node as a master
 * list for all of the vertexes
 * @author Allen McDermott
 * @since 12/13/2014
**/



public class graph {


	//global variables
	node head;
	node tail;

	int size;
	int edgeCount;
	

	//constructor
	public graph() {
		head = null;
		tail = head;
		size = 0;
	}


	/**
	 * this method adds a string to the master
	 * list of vertexes and returns true if successful
	 * or false if the input is alread in the list
	 * @param String id is the string to be added
	 * @return true if item was added false if not
	 **/
	public boolean addVertex(String id) {
		if(contains(id)==true) {
			return false;
		}

		DiNode tap = new DiNode(id);
		node temp = head;
		if(head == null) {
			head = new node(tap);
			head.setNext(tail);
			size++;
			return true;
		}
		else{
			while(temp.getNext()!=null) {
				temp = temp.getNext();
			}
		}

		node nod = new node(tap);
		temp.setNext(nod);
		tail = temp.getNext();
		size++;
		return true;
	}




	/**
	 * the method addEdge makes an edge from id1 to id2
	 * @param id1 is going to be the parent of 1d2
	 * @param id2 is going to be the child of id1
	**/ 
	public void addEdge(String id1, String id2) {
		DiNode node1= null;
		DiNode node2=null;
		node temp = head;
		int baby;
		if(head!=null&&id1!=null&&id2!=null) {
				
			if(id1.equals(head.getData())) {
				while(temp.getNext()!=null&&!temp.getData().equals(id2)) {
					temp=temp.getNext();
				}
				node1=head.getVertex();
				node2=temp.getVertex();
				baby=0;
				String[] chichi=node1.getChildren();
				for(int i = 0; i<chichi.length;i++) {
					if(chichi[i].equals(node2.getName())) {
						baby++;
					}
				}
				if(baby==0) {
					node1.addChild(node2.getName()+"");
					node2.addParent(node1.getName()+"");
					edgeCount++;

				}
				
				
			}

			else if(id2.equals(head.getData())) {
				while(temp.getNext()!=null&&!temp.getData().equals(id1)) {
					temp=temp.getNext();
				}
				node1=head.getVertex();
				node2=temp.getVertex();
				baby=0;
				String[] chichi=node1.getParents();
				for(int i = 0; i<chichi.length;i++) {
					if(chichi[i].equals(node2.getName())) {
						baby++;
					}
				}
				if(baby==0) {
					//node1=head.getVertex();
					//node2=temp.getVertex();
					node2.addChild(node1.getName());
					node1.addParent(node2.getName());
					edgeCount++;
				}
			}

			else {
				
			
				while(temp.getNext()!=null&&!temp.getData().equals(id1)) {
					temp = temp.getNext();
				}
				
				node1 = temp.getVertex();
				temp = head;
				
				while(temp.getNext()!=null&&!temp.getData().equals(id2)) {
					temp = temp.getNext();
				}
				
				node2 = temp.getVertex();;
				
				
				
				baby=0;
				String[] chichi=node1.getChildren();
				for(int i = 0; i<chichi.length;i++) {
					if(chichi[i].equals(node2.getName())) {
						baby++;
					}
				}
				if(baby==0) {	
					node1.addChild(node2.getName()+"");
					node2.addParent(node1.getName()+"");
					edgeCount++;
				}

				
			}
		}
		
	}


	/**
	 * this method removes a vertex from the array
	 * as well as deletes the any pointers from its parent
	 * and children
	 * @param String id that is to be deleted from the list
	 * @return boolean true if deleted false if not in the list
	**/
	public boolean removeVertex(String id) {
		if(contains(id)==false) {
			return false;
		}

		node temp = head;
		String[] parents;
		String[] children;
		if(head.getData().equals(id)) {
			head=null;
			head = temp.getNext();
			parents = temp.getPar();
			temp.clearPar();
			for(int i =0;i<parents.length;i++) {
				node hi = temp;
				while(hi.getNext()!=null) {
					hi=hi.getNext();
					if(hi.getVertex().equals(parents[i])) {
						hi.deleteChi(parents[i]);
					}
				}
			}

			children=temp.getChi();
			temp.clearChi();
			for(int i =0;i<children.length;i++) {
				node hi = temp;
				while(hi.getNext()!=null) {
					hi=hi.getNext();
					if(hi.getData().equals(children[i])) {
						hi.deletePar(children[i]);
					}
				}
			}
			size--;		
			return true;
		}
		
		while(temp.getNext()!=null&&!temp.getData().equals(id)) {
			temp=temp.getNext();
		}
		node tempo=head;
		while(tempo.getNext()!=temp) {
			tempo=tempo.getNext();
		}
			
			tempo.setNext(temp.getNext());
			if(temp==tail) {
				tail=tempo;
			}
			parents = temp.getPar();
			children = temp.getChi();
			temp.clearChi();
			temp.clearPar();
			
			node hi;

			for(int i =0;i<parents.length;i++) {
				hi = head;
				if(head.getData().equals(parents[i])) {
					head.deleteChi(temp.getData());
				}
				while(hi.getNext()!=null) {
					hi=hi.getNext();
					if(hi.getData().equals(parents[i])) {
						hi.deleteChi(temp.getData());
					}
				}
			}


			for(int i =0;i<children.length;i++) {
				hi = head;
				if(head.getData().equals(children[i])) {
					head.deletePar(temp.getData());
				}
				while(hi.getNext()!=null) {
					hi=hi.getNext();
					if(hi.getData().equals(children[i])) {
						hi.deletePar(temp.getData());
					}
				}
			}

			size--;			
			return true;

		
	}


	 /**
	 * although this method is not required it was extemley
	 * helpfull if i needed to check to see if a item was
	 * in the list or not
	 * @param id is a string to check if it is in the list
	 * @return true if item was found false if not
	 **/
	public boolean contains(String id) {
		node temp = head;
		if(head==null) {
			return false;
		}
		
		if((""+head.getData()).equals(id)) {
			return true;
		}
	
		while(temp.getNext()!=null) {
			temp=temp.getNext();
			if((temp.getData()+"").equals(id)) {
				return true;
			}
		}
		
		return false;
	}



	 /**
	 * this method removes an edge between two strings
	 * @param id1 string that is parent of string id2
	 **/
	public void removeEdge(String id1, String id2) {
		
		
		if(contains(id1)==true&&contains(id2)==true) {

			DiNode node1= null;
			DiNode node2=null;
			node temp = head;
			if(head.getData().equals(id1)) {
				while(temp.getNext()!=null&&!temp.getVertex().getName().equals(id2)) {
					temp=temp.getNext();
				}
				node1 = head.getVertex();
				node2 = temp.getVertex();

				node1.removeChild(node2.getName());

				node2.removeParent(node1.getName());
				edgeCount--;

			}

			else if(head.getData().equals(id2)) {
				while(temp.getNext()!=null&&!temp.getVertex().getName().equals(id1)) {
					temp=temp.getNext();
				}
				node1 = head.getVertex();
				node2 = temp.getVertex();
				node2.removeChild(node1.getName());
				node1.removeParent(node2.getName());
				edgeCount--;
			}



			else {
				node tempo = head;
				while(temp.getNext()!=null&&!temp.getData().equals(id1)) {
					temp = temp.getNext();
				}

				while(tempo.getNext()!=null&&!tempo.getData().equals(id2)) {
					tempo= tempo.getNext();
				}
				node1 = temp.getVertex();
				node2 = tempo.getVertex();
				
			

				node1.removeChild(node2.getName());
				node2.removeParent(node1.getName());
				edgeCount--;
			
				
			}
		}
	}

		
	

	 /**
	 * this method is fairly simple all it does is reset the list
	*/
	public void clear() {
		head = null;
		tail = head;
		size=0;
	}
		
		

	/**
	 * this method returns all the vertexs in a stringList
	 * see class set and LinkedList for more detail
	 * return StringList of all the vertexes
	 **/
	public StringList getVertexList() {
		node temp = head;
		
		StringList list= new set();
		if(head==null) {
			return list;
		}
		
		
		list.add(temp.getData());
		while(temp.getNext()!=null) {
			temp=temp.getNext();
			list.add(temp.getData());
		}

		return list;
	}



	 /**
	 * this method returns a StringList of all the parents
	 * and childre of the given vertex
	 * @param String id1 to find its parents and children
	 * @return StringList of id1's parents and children
	 **/
	public StringList getNeighbors(String id1) {
		StringList list = new set();
		if(contains(id1) == false) {
			return list;
		}
		
		String[] parents;
		String[] children;
		if(head.getData().equals(id1)) {
			parents = head.getPar();
			children = head.getChi();
		}
		
		else{
			node temp = head;
		
			while(temp.getNext()!=null&&!temp.getData().equals(id1)) {
				temp=temp.getNext();
			}
			parents = temp.getPar();
			children = temp.getChi();
		}

		for(int i =0; i<parents.length;i++) {
			list.add(parents[i]);
		}
		
		for(int x = 0; x<children.length;x++) {
			list.add(children[x]);
		}
		
		return list;
	}


	 /**
	 * this method gets the amount of parents of a given vertex
	 * @param String id1 is the vertex we are looking for
	 * @return the amount of parents of the vertex
	 **/
	public int getInDegree(String id1) {
		int ans;
		if(contains(id1) == false) {
			return -1;
		}

		if(head.getData().equals(id1)) {
			ans = head.getVertex().getParentSize();
		}
		
		else{
			node temp = head;
		
			while(temp.getNext()!=null&&!temp.getData().equals(id1)) {
				temp=temp.getNext();
			}
			ans = temp.getVertex().getParentSize();
		}
		return ans;
	}


 	/**
	 * this method gets the amount of children of a given vertex
	 * @param String id1 is the vertex we are looking for
	 * @return the amount of children of the vertex
	 **/
	public int getOutDegree(String id1) {
		int ans;
		if(contains(id1) == false) {
			return -1;
		}

		if(head.getData().equals(id1)) {
			ans = head.getVertex().getChildSize();
		}
		
		else{
			node temp = head;
		
			while(temp.getNext()!=null&&!temp.getData().equals(id1)) {
				temp=temp.getNext();
			}
			ans = temp.getVertex().getChildSize();
		}
		return ans;
	}


	 /**
	 * This method gets the amount of vertex's in the list
	 * @return the amount of vertex's in the list
	 **/
	public int getVertexCount() {
		return size;
	}

	/**
	 * @return the amount of edes in graph
	*/
	public int getEdgeCount() {
		return edgeCount;
	}


	/**
	 * this method check to see if there is an edge between two vertex's
	 * @param id1 is the vertex to check if it is the parent of the id2 
	 * @param id2 is the vertex to check if it is the child of the id1 
	 * @return true if edge exists false if not
	**/
	public boolean hasEdge(String id1, String id2) {
		if(contains(id1) == false||contains(id2)==false) {
			return false;
		}
		
		String[] children;
		String[] parents;
		if(head.getData().equals(id1)) {

			children = head.getChi();
			for(int i = 0;i<children.length;i++) {
				if(children[i]!=null&&children[i].equals(id2)) {
					System.out.println(children[i]);
					return true;
				}


			}
			
			return false;
		}

		else if(head.getData().equals(id2)) {
			parents = head.getPar();
			for(int i = 0;i<parents.length;i++) {

				if(parents[i]!=null&&parents[i].equals(id1)) {
					return true;
				}
			}
			return false;
		}
		
		else{
			node temp = head;
		
			while(temp.getNext()!=null&&!temp.getData().equals(id1)) {
				temp=temp.getNext();
				if(temp.getData().equals(id1)) {
					children = temp.getChi();
					for(int i = 0;i<children.length;i++) {

						if(children[i]!=null&&children[i].equals(id2)) {
							return true;
						}
					}
				}
				if(temp.getData().equals(id2)) {
					parents = temp.getPar();
					for(int i = 0;i<parents.length;i++) {
					
						if(parents[i]!=null&&parents[i].equals(id1)) {
							return true;
						}
					}
				}
			}
					
			
			
		}
		return false;
	}
		

		

	/**
         * getPath finds a path between 2 vertexs
	 * @param DiNode the vertex we are currently in 
	 * @param id2 is the target vertex
	 * @return a string representing the path
	**/ 
	public String getPath(DiNode nob, String id2) {
		String[] list = nob.getChildren();
		
		String s=null;
		for(int i = 0;i<list.length;i++) {
			node temp = head;
			if(list[i].equals(id2)) {
				return id2;
			}
			if(head.getData().equals(list[i])) {
				s = getPath(head.getVertex(),id2);
			}
			else {
				while(temp.getNext()!=null) {
					temp=temp.getNext();
					if(temp.getData().equals(list[i])) {
						s=getPath(temp.getVertex(),id2);
					}
				}
			};
				
		}
		return s;
	}
	





	/**
	 * still needs work
	 * String dest,DiNode cur,String[] pop
	**/
	public boolean hasPath(String id1, String id2) {
		if(contains(id1) == false||contains(id2)==false) {
			return false;
		}
		String[] hey = new String[size];
		node tempo=head;
		int koal =1;
		hey[0]=head.getData();
		while(tempo.getNext()!=null) {
			tempo=tempo.getNext();
			hey[koal]=tempo.getData();			
			koal++;
		}
		node temp = head;
		if(head.getData().equals(id1)) {
			return makePath(id2,head.getVertex(),hey);
		}
		while(temp.getNext()!=null) {
			temp=temp.getNext();
			if(temp.getData().equals(id2)) {
				return makePath(id2,temp.getVertex(),hey);
			}
		}
		return false;
		/**node temp=head;
		String s=null;

		if(head.getData().equals(id1)) {
			s = getPath(head.getVertex(),id2);

		}
		else {
			while(temp.getNext()!=null) {
				temp=temp.getNext();
				if(temp.getData().equals(id1)) {
					s = getPath(temp.getVertex(),id2);
				}
			}
		}
		if(id2.equals(s)) {
			return true;
		}

		return false;**/
	}

		

	
	/**
	 * this method creates a bfs traversal represented as a string
	 * @param a list of vertexs that haven't been visited
	 * @param the vertex we are currently in
	 * @return a string representing the traversal
	 **/
	public String makeBFTree(String[] list,DiNode nad) {
		String[] letz =new String[nad.getChildSize()];		
		int gogo = 0;
		String BFT = "";
		for(int i=0;i<list.length;i++) {
			if(list[i]!=null){				
				if(list[i].equals(nad.getName())) {			
					list[i]=null;
				}
			}
		}
	
		String[] nap = nad.getChildren();
		for(int x = 0;x<nap.length;x++) {
			for(int i = 0;i<list.length;i++) {
				if(nap[x].equals(list[i])) {
					letz[gogo] = list[i];
					gogo++;
					list[i]=null;
				}
			}
		}

		if(letz.length==0) {
			return "";
		}
		if(letz[0]==null) {
			return "";
		}
		int toad=0;
		for(int x = 0;x<letz.length;x++) {

			node temp=head;
			if(head.getData().equals(letz[x])) {
				if(toad==0) {
					BFT+="("+letz[x]+makeBFTree(list,head.getVertex());
					toad++;
				}
				else {
					BFT+=" "+letz[x]+makeBFTree(list,head.getVertex());
				}
			}
			else {
				while(temp.getNext()!=null) {
					temp=temp.getNext();
					if(temp.getData().equals(letz[x])) {
						if(toad==0) {
							BFT+="("+letz[x]+makeBFTree(list,temp.getVertex());
							toad++;
						}
						else {
							BFT+=" "+letz[x]+makeBFTree(list,temp.getVertex());
						}
					}
				}
			}
		
		}

		return BFT+")";

		
		
	}



	/**public String makeBFTString(String start) {
		if(contains(start)==false) {
			return null;
		}
		String BFT="";
		node temp = head;
		head.setColor("white");
		while(temp.getNext()!=null) {
			temp=temp.getNext();
			temp.setColor("white");
		}
		LinkedList list = new LinkedList();
		list.add(start);
		if(head.getData().equals(start)) {
			head.setColor("grey");
			BFT+=head.getData()+"(";
		}
		else {
			temp=head;
			while(temp.getNext()!=null&&!temp.equals(start)) {
				temp=temp.getNext();
			}
			temp.setColor("grey");
			BFT+=head.getData();
		}


		while(list.getSize()!=0) {
			String u = list.getElementAt(0);
			String[] chi;
			int pal = 0;
			if(head.getData().equals(u)) {
				chi = head.getChi(); 
			}
			else {
				temp=head;
				while(temp.getNext()!=null&&!temp.getData().equals(u)) {
					temp=temp.getNext();
				}
				chi = temp.getChi();
			}
		
			for(int pa = 0;pa<chi.length;pa++) {
				if(head.getData().equals(chi[pa])) {
					if(head.getColor().equals("white")) {
						head.setColor("grey");
						list.add(head.getData());
						if(pal==0) {
							BFT+=head.getData()+"(";
							pal++;
						}
						
						
					} 
				}
				else {
					temp=head;
					while(temp.getNext()!=null) {
						temp=temp.getNext();
						if(temp.getData().equals(chi[pa])&&temp.getColor().equals("white")) {
							temp.setColor("grey");
							list.add(temp.getData());
						} 
					}
			
				}
			}

			if(head.getData().equals(u)) {
				head.setColor("black"); 
			}
			else {
				temp=head;
				while(temp.getNext()!=null) {
					temp=temp.getNext();
					if(temp.equals(u))
					{
						temp.setColor("black");
					}	
				}
			}
			list.delete(u);
			BFT+=u+"(";
			
			
			
		}
		return mech;

	
			
	}
**/







	/**
	 * This method makes a string representing a breadth first search
	 * @param vertex representing the root
	 * @return string representing the BFS tree
	 **/

	public String getBFTree(String root) {
		if(contains(root) == false) {
			return null;
		}
		node tempo = head;
		String[] getEm = new String[size];
		getEm[0] = head.getData();
		int koal = 1;
		while(tempo.getNext()!=null) {
			tempo=tempo.getNext();
			getEm[koal]=tempo.getData();			
			koal++;
		}
		node temp = head;
		if(head.getData().equals(root)) {
			return root+makeBFTree(getEm,head.getVertex());
		}
		else {
			while(temp.getNext()!=null) {
				temp=temp.getNext();
				if(temp.getData().equals(root)) {
					return root+makeBFTree(getEm,temp.getVertex());
				}
			}
		}
		return "";
	}
		

	String[] path;
	int ill;
	String[] path2;
	public boolean makePath(String dest,DiNode cur,String[] pop) {
		if(cur.getName().equals(dest)) {
			path[ill]=cur.getName();
			ill++;
			return true;
		}
		String[] list = pop;
		String[] letz =new String[cur.getChildSize()];		
		int gogo = 0;
		String BFT = "";
		boolean j=false;
		for(int i=0;i<list.length;i++) {
			if(list[i]!=null){				
				if(list[i].equals(cur.getName())) {			
					list[i]=null;
				}
			}
		}
	
		String[] nap = cur.getChildren();
		for(int x = 0;x<nap.length;x++) {
			for(int i = 0;i<list.length;i++) {
				if(nap[x].equals(list[i])) {
					letz[gogo] = list[i];
					gogo++;
					list[i]=null;
				}
			}
		}

		for(int i = 0;i<letz.length;i++) {
			if(head.getData().equals(letz[i])) {
				j=makePath(dest,head.getVertex(),list);

			}
			else {
				node temp = head;
				while(temp.getNext()!=null) {
					temp=temp.getNext();
					if(temp.getData().equals(letz[i])) {
						j=makePath(dest,temp.getVertex(),list);
					}
				}
			}
			if(j==true) {
				path[ill]=cur.getName();
				ill++;
				return true;
			}
		}
		return false;
			
	}
	

	/**
	 * this method finds a path from a to b and from b to c
	 * @param start is the starting vertex
	 * @param mid is the middle vertex
	 * @param is the destination vertex
	 * @return a StringList representing a path from a to b to c
	*/
	public StringList getPathThrough(String start,String mid, String end) {
		if(contains(start)!=true||contains(mid)!=true||contains(end)!=true) {
			return null;
		}
		String[] getEm = new String[size];
		getEm[0] = head.getData();
		int koal = 1;
		node tempo=head;
		while(tempo.getNext()!=null) {
			tempo=tempo.getNext();
			getEm[koal]=tempo.getData();			
			koal++;
		}
		boolean one;
		boolean two;
		node temp;
		if(head.getData().equals(start)) {
			path = new String[2*(size+1)];
			ill=0;
			one = makePath(mid,head.getVertex(),getEm);
			getEm[0] = head.getData();
			koal = 1;
			tempo=head;
			while(tempo.getNext()!=null) {
				tempo=tempo.getNext();
				getEm[koal]=tempo.getData();			
				koal++;
			}
			temp =head;
			while(temp.getNext()!=null&&!temp.getData().equals(mid)) {
				temp=temp.getNext();
			}

			two = makePath(end,temp.getVertex(),getEm);
		}
		else {
			path = new String[2*(size+1)];
			ill=0;
			temp = head;
			while(temp.getNext()!=null&&!temp.getData().equals(start)) {
				temp=temp.getNext();
			}
			one = makePath(mid,temp.getVertex(),getEm);
			getEm[0] = head.getData();
			koal = 1;
			tempo=head;
			while(tempo.getNext()!=null) {
				tempo=tempo.getNext();
				getEm[koal]=tempo.getData();			
				koal++;
			}
			if(head.getData().equals(mid)) {
				two = makePath(end,head.getVertex(),getEm);
			}
			else {
				temp =head;
				while(temp.getNext()!=null&&!temp.getData().equals(mid)) {
					temp=temp.getNext();
				}
				two = makePath(end,temp.getVertex(),getEm);
			
			}
		}

		if(one!=true||two!=true) {
			return null;
		}

		StringList last = new set();
		int me=0;
		for(int k=0;k<path.length;k++) {
			if(path[k]==null) {
				break;
			}
			else if(path[k].equals(start)) {
				for(int jay = k;jay!=-1;jay--) {
					System.out.println(path[jay]);
					last.add(path[jay]);
				}
			}
			else if(me==0&&path[k].equals(mid)) {
				me++;
			}
			else if(me!=0&&path[k].equals(mid)) {
				for(int jay = k-1;!path[jay].equals(start);jay--) {
					System.out.println(path[jay]);
					last.add(path[jay]);
				}
			}
		}
		return last;
		
		
	}		



	/**
	 * this method takes in two vertexs and returns a array representing the
	 * shortest path between start and dest
	 * @param start is the starting vertex
	 * @param is the destination
	 * @return an array of strings representing the shortest path
	*/
	public String[] makeShortPath(String start,String dest) {
		if(contains(start)==false) {
			return null;
		}
		node temp = head;
		head.setColor("white");
		while(temp.getNext()!=null) {
			temp=temp.getNext();
			temp.setColor("white");
		}
		LinkedList list = new LinkedList();
		list.add(start);
		if(head.getData().equals(start)) {
			head.setColor("grey");
		}
		else {
			temp=head;
			while(temp.getNext()!=null&&!temp.equals(start)) {
				temp=temp.getNext();
			}
			temp.setColor("grey");
		}

		String[] mech = new String[2*size];
		int onize=0;
		int iggy=0;
		String[] par = new String[0];
		String[] panth = new String[size];
		int biggy=0;
		while(list.getSize()!=0) {
			String u = list.getElementAt(0);


			if(u.equals(dest)) {
				temp=head;
				if(head.equals(u)) {
					par = head.getPar();
				}
				else {
					while(temp.getNext()!=null&&!temp.getData().equals(u)) {
						temp= temp.getNext();
					}
					par = temp.getPar();
				}
				String kool = "";
				
				while(!kool.equals(start)) {
					
					int smaller;
					int smallest = size;
					String ind;
					for(int q = 0;q<par.length;q++) {
						temp=head;
						if(head.equals(par[q])) {
							smaller = head.getDist();
						}
						else {
							while(temp.getNext()!=null&&!temp.getData().equals(par[q])) {
								temp=temp.getNext();
							}
							smaller = temp.getDist();
						}
						if(smallest>smaller) {
							smallest=smaller;
							kool=par[q];
						}
					}
					temp=head;
					if(head.equals(kool)) {
						par = head.getPar();
						panth[biggy]=kool;
						biggy++;
						
					}
					else {
						while(temp.getNext()!=null&&!temp.getData().equals(kool)) {
							temp=temp.getNext();
						}
						par = temp.getPar();
						panth[biggy]=kool;
						biggy++;
					}
					
				}

				return panth;				
			}



			String[] chi;
			
			if(head.getData().equals(u)) {
				chi = head.getChi();
				iggy = head.getDist()+1; 
			}
			else {
				temp=head;
				while(temp.getNext()!=null&&!temp.getData().equals(u)) {
					temp=temp.getNext();
				}
				chi = temp.getChi();
				iggy = temp.getDist()+1;
			}
		
			for(int pa = 0;pa<chi.length;pa++) {
				if(head.getData().equals(chi[pa])) {
					if(head.getColor().equals("white")) {
						head.setColor("grey");
						list.add(head.getData());
						head.setDist(iggy);
					} 
				}
				else {
					temp=head;
					while(temp.getNext()!=null) {
						temp=temp.getNext();
						if(temp.getData().equals(chi[pa])&&temp.getColor().equals("white")) {
							temp.setColor("grey");
							list.add(temp.getData());
							temp.setDist(iggy);
						} 
					}
			
				}
			}
			if(head.getData().equals(u)) {
				head.setColor("black"); 
			}
			else {
				temp=head;
				while(temp.getNext()!=null) {
					temp=temp.getNext();
					if(temp.equals(u))
					{
						temp.setColor("black");
					}	
				}
			}
			list.delete(u);
			mech[onize] = u;
			onize++;
			
			
		}
		return null;

	
			
	}

	


	/**
	 * this method finds the shortest path from start to dest
	 * @param start is the starting vertex
	 * @param dest is the finish vertex
	 * @return a StringList of the path from start to dest or null
	 * if path does not exist
	 * note: path returns in reverse order due to effeiciency
	 **/	
	public StringList getShortestPath(String start, String dest) {
		if(contains(start)==false||contains(dest)==false) {
			return null;
		}		
		String[] hai = makeShortPath(start,dest);
		StringList list = new set();
		if(hai!=null&&hai.length!=0) {
			list.add(dest);
			for(int i = 0;i<hai.length;i++) {
				if(hai[i]==null) {
					break;
				}
				list.add(hai[i]);
			}
			return list;
		}
		return null;
	}
	





}
