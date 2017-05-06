import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;


class GraphManager extends JComponent
{
    GraphReader greader;
    File grffile;
    String path = "./";
    JFrame parent;

   public GraphManager(JFrame p)
   {
      parent = p;
   }

    public DiGraph chooseGraph(DiGraph emptygraph)
    {
	grffile = chooseNewFile();
        if (grffile == null) return null;
	try
	{
	    greader = new GraphReader(grffile);
	    return greader.getGraph(emptygraph);
	}
	catch (FileNotFoundException e)
	{
	    System.out.println("Can't open file");
	    return null;
	}
    }

    public int getGraphCount()
    {
	return greader.getGraphCount();
    }

    public DiGraph nextGraph(DiGraph emptygraph)
    {
	return greader.getGraph(emptygraph);
    }

   public DiGraph generateGraph(DiGraph emptygraph)
   {
      return new GraphGenerator(parent).makeGraph(emptygraph);
   }

    public void saveGraph(DiGraph graph)
    {
	String filename;
	if (graph == null)
	    System.out.println("Can't save null graph");
	JFileChooser saver = new JFileChooser(path);
	saver.showSaveDialog(this);
	filename = saver.getSelectedFile().getName();
	System.out.println("Saving as " + filename);
	try {
	    GraphWriter writer = new GraphWriter(path + filename);
	    writer.writeGraph(graph);
	    writer.closeFile();
        }
	catch (IOException e)
	{
	    System.out.println("Error creating output file");
	}
    }

    private File chooseNewFile()
    {
	JFileChooser finder = new JFileChooser(path);
	finder.showOpenDialog(this);
	return finder.getSelectedFile();
    }

    public void showGraph(DiGraph graph)
    {
	int i, j, count = 0;
	String id1, id2;
	int vcount = graph.getVertexCount();
	int ecount = graph.getEdgeCount();
	StringList vlist = graph.getVertexList();
	System.out.println(""+vcount+" "+ecount);

	for (i=0; i<vlist.getSize()-1; i++)
	{
	    id1 = vlist.getElementAt(i);
	    for (j=i+1; j<vlist.getSize(); j++)
	    {
		id2 = vlist.getElementAt(j);
		if (graph.hasEdge(id1, id2))
		{
		    System.out.print(" "+id1+" "+id2);
		    if (count == ecount - 1 || count % 8 == 7)
			System.out.println();
		    else
			System.out.print(" ");
		    count++;
		}
	    }
	}
    }
}


class GraphReader
{
    char prevchar;
    int nodecount, edgecount, graphcount;
    Scanner reader;

    public GraphReader(File grffile) throws FileNotFoundException
    {
	reader = new Scanner(grffile);
	prevchar = ' ';
	nodecount = 0;
	edgecount = 0;
	graphcount = 0;
    }

   public DiGraph getGraph(DiGraph emptygraph)
   {
      int i;

      DiGraph graph = emptygraph;
      nodecount = reader.nextInt();
      edgecount = reader.nextInt();
      for (i=0; i<edgecount; i++)
      {
         String v1 = reader.next();
         String v2 = reader.next();
         graph.addVertex(v1);
         graph.addVertex(v2);
         graph.addEdge(v1, v2);
      }
      graphcount++;
      return graph;
   }

    public int getGraphCount()
    {
	return graphcount;
    }
}

class GraphWriter extends BufferedWriter
{
    public GraphWriter(String filename) throws IOException
    {
	super (new FileWriter(filename));
    }

    public void writeGraph(DiGraph graph)
    {
	int i, j, count = 0;
	String id1, id2;
	int vcount = graph.getVertexCount();
	int ecount = graph.getEdgeCount();
	StringList vlist = graph.getVertexList();

	try
	{
	    write(""+vcount+" "+ecount);
	    newLine();
	    for (i=0; i<vcount-1; i++)
	    {
		id1 = (vlist.getElementAt(i));
		for (j=i+1; j<vcount; j++)
		{
		    id2 = vlist.getElementAt(j);
		    if (graph.hasEdge(id1, id2))
		    {
			write(" "+id1+" "+id2);
			if (count == ecount - 1 || count % 10 == 9)
			    newLine();
			else
			    write(" ");
			count++;
		    }
		}
	    }
	}
	catch (IOException e)
	{
	    System.err.println("Problem encountered while writing graph");
	}
    }

    public void closeFile()
    {
	try {
	    flush();
	    close();
	}
	catch (IOException e)
	{
	    System.err.println("Problem closing file");
	}
    }

}

class GraphGenerator
{
    BufferedWriter writer;
    Random randgen;
    JFrame parent;

   public GraphGenerator (JFrame p)
   {
      parent = p;
      randgen = new Random();
   }


   public DiGraph makeGraph(DiGraph emptygraph)
   {
      int i, v1, v2;
      int ecount, vcount;
      
      PopUp inbox = new PopUp(parent);
      inbox.setVisible(true);
      vcount = inbox.getNodeCount();
      ecount = inbox.getEdgeCount();
      inbox.dispose();
      //System.err.println(""+vcount+" nodes and "+ecount+" edges");
      if (ecount > vcount*vcount)
      {
         System.err.println("Too many edges");
         return emptygraph;
      }
      DiGraph graph = emptygraph;
      for (i=1; i<= vcount; i++)
         graph.addVertex("v"+i);
      for (i=0; i<ecount; i++)
      {
         v1 = 1 + randgen.nextInt(vcount);
         v2 = 1 + randgen.nextInt(vcount);
         while (v1 == v2 || graph.hasEdge("v"+v1, "v"+v2))
         {
            v1 = 1 + randgen.nextInt(vcount);
            v2 = 1 + randgen.nextInt(vcount);
         }
         graph.addEdge("v"+v1, "v"+v2);
      }
      return graph;
   }
    
   private static class PopUp extends JDialog
   {
    JButton okbutton;
    int nodecount;
    int edgecount;
    JTextField nodebox, edgebox;

      public PopUp(JFrame parent)
      {
         super(parent, "Set Node and Edge Counts", true);

      setSize(new Dimension(300,150));
      okbutton = new JButton("OK");
      ButtonWatch bwatch = new ButtonWatch();
      okbutton.addActionListener(bwatch);
      JPanel okpanel = new JPanel();
      okpanel.add(okbutton);
      JPanel inpanel = new JPanel();
      inpanel.setSize(new Dimension(270, 85));

      JPanel npanel = new JPanel();
      npanel.setPreferredSize(new Dimension(260,40));
      JLabel nlabel = new JLabel("How many nodes?");
      nlabel.setPreferredSize(new Dimension(200,30));
      nodebox = new JTextField();
      nodebox.setPreferredSize(new Dimension(50,30));
      npanel.add(nlabel);
      npanel.add(nodebox);

      JPanel epanel = new JPanel();
      epanel.setPreferredSize(new Dimension(260,40));
      JLabel elabel = new JLabel("How many edges?");
      elabel.setPreferredSize(new Dimension(200,30));
      edgebox = new JTextField();
      edgebox.setPreferredSize(new Dimension(50,30));
      epanel.add(elabel);
      epanel.add(edgebox);

      inpanel.add(npanel);
      inpanel.add(epanel);

      getContentPane().add(inpanel, BorderLayout.CENTER);
      getContentPane().add(okpanel, BorderLayout.SOUTH);
      setLocationRelativeTo(this);
      }

      public int getNodeCount()
      {
         return nodecount;
      }
   
      public int getEdgeCount()
      {
         return edgecount;
      }

      private class ButtonWatch implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            Object source = event.getSource();
	   
            if (source == okbutton)
            {
               nodecount = Integer.parseInt(nodebox.getText());
               edgecount = Integer.parseInt(edgebox.getText());
               setVisible(false);
            }
         }
      }
   }
}














