import java.io.FileInputStream;
import java.util.Scanner;

/**
 * The ScriptTool class will read from a file and implement the
 * commands from a file
 * <br><br>
 * @author Allen McDermott
 * @since 10/15/14
 */

public class ScriptTool {
	

	/**
	 * Global variables:
	 * stream-file from which the commands will be found
	 * list1- a linkedList
	 * list2- another linkedList
	**/
	public FileInputStream stream;
	public StringList list1;
	public StringList list2;



	/**
	 * ScriptTool constructor for class ScriptTool
	 * @param stream - the file to get commands from 
	 * @param list1 - one of the two linkedLists
	 * @param list2 - the other linkedList
	**/
	public ScriptTool(FileInputStream strem,StringList lst1,StringList lst2) {
		stream = strem;
		list1 = lst1;
		list2 = lst2;
	}


	/**
	 * the run method will read threw the data from stream
	**/
	public void run() {
		int i;
		try{
		StringBuilder sb = new StringBuilder();
		Scanner key = new Scanner(stream);
		while(key.hasNextLine()) {
			read(key.nextLine()+"");
		}
		}
		catch(Exception e){
		}
	}

	/**
	 * read will read a line from stream and then trigger a command based
	 * on what it reads
	 * @param data is the line of command from stream
	**/
	public void read(String data) {
		String[] k = data.split(" ");
		if(k[0].equals("add")) {
			if(k[2].equals("1")) {
				list1.add(k[1]);
			}
			else {
				list2.add(k[1]);
			}
		}
		
		else if(k[0].equals("delete")) {
			if(k[2].equals("1")) {
				list1.delete(k[1]);
			}
			else {
				list2.delete(k[1]);
			}
		}
		else if(k[0].equals("find")) {
			boolean b;
			if(k[2].equals("1")) {
				b = list1.contains(k[1]);
			}
			else {
				b = list2.contains(k[1]);
			}
			if(b==true) {
				System.out.println(k[1]+" was found in list "+k[2]+"!!");
			}
			else {
				System.out.println(k[1]+" was not found in list "+k[2]+" :(");
			}
		}
		else if(k[0].equals("clear")) {
			if(k[1].equals("1")) {
				list1.clear();
			}
			else {
				list2.clear();
			}
		}
		else if(k[0].equals("merge")) {
			if(k[1].equals("1")) {
				list1.addAll(list2);
			}
			else {
				list2.addAll(list1);
			}
		}
		else if(k[0].equals("subtract")) {
			if(k[1].equals("1")) {
				list1.deleteAll(list2);
			}
			else {
				list2.deleteAll(list1);
			}
		}
		else if(k[0].equals("show")) {
			if(k[1].equals("1")) {
				System.out.println();
				System.out.print("{");
				for(int i=0;i<list1.getSize();i++){
					String s = list1.getElementAt(i);
					System.out.print(s+",");
				}
			}
			else {
				System.out.println();
				System.out.print("{");
				for(int i=0;i<list2.getSize();i++){
					String s = list2.getElementAt(i);
					System.out.print(s+",");
				}
			}
			System.out.print("}");
			System.out.println();
		}
		else if(k[0].equals("size")) {
			int i;
			if(k[1].equals("1")) {
				i = list1.getSize();
			}
			else {
				i = list2.getSize();
			}
			System.out.println("List "+k[1]+" is size "+i);
		}
	}
	
	

	
}
		

	 
	
