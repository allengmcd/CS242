
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

class DiGraphTest extends JFrame
{
   MenuWatch mwatch;
   ButtonWatch bwatch;
   ListWatch lwatch;
   JMenuItem loadclass;
   JButton vinsertbutton, vremovebutton, clearbutton, einsertbutton,
      eremovebutton, refreshbutton, spathbutton, coverbutton, filebutton, randbutton, treebutton;
   JList neighborbox, vertexbox, pathbox, coverbox;
   DefaultListModel neighborlist, vertexlist, pathlist, coverlist;
   DiGraph graph;
   JTextField vertexfield, messagefield, startfield, endfield, thrufield, countfield,
      incountfield, outcountfield, ccountfield;
   private boolean listening = true;
   GraphManager gman;

   public DiGraphTest()
   {
      super("Generic Graph Testing Program");
      addWindowListener(new WindowWatch());
      mwatch = new MenuWatch();
      bwatch = new ButtonWatch();
      lwatch = new ListWatch();
      gman = new GraphManager(this);
      initmenu();

	// MESSAGE PANEL

      JLabel messagelabel = new JLabel("Message: ");
      messagefield = new JTextField();
      messagefield.setPreferredSize(new Dimension(400,20));

      JPanel messagepanel = new JPanel();
      messagepanel.setPreferredSize(new Dimension(590,40));
      messagepanel.setBackground(Color.pink);
      messagepanel.add(messagelabel);
      messagepanel.add(messagefield);

	// LIST PANEL

      JPanel listpanel = new JPanel();
      listpanel.setPreferredSize(new Dimension(150,450));
      listpanel.setBackground(Color.yellow);
      listpanel.add(new JLabel("     Vertex List      "));

      JLabel countlabel = new JLabel("Vertex Count");
      countfield = new JTextField();
      countfield.setPreferredSize(new Dimension(40,20));

      vertexlist = new DefaultListModel();
      vertexbox = new JList(vertexlist);
      vertexbox.addListSelectionListener(lwatch);
      JScrollPane vertexpane = new JScrollPane(vertexbox);
      vertexpane.setPreferredSize(new Dimension(140,200));
 
      filebutton = new JButton("File");
      filebutton.addActionListener(bwatch);
      filebutton.setPreferredSize(new Dimension(110, 30));
      
      randbutton = new JButton("Random");
      randbutton.addActionListener(bwatch);
      randbutton.setPreferredSize(new Dimension(110, 30));

   /*
	coverlist = new DefaultListModel();
	coverbox = new JList(coverlist);
	JScrollPane coverpane = new JScrollPane(coverbox);
	coverpane.setPreferredSize(new Dimension(140,180));

	coverbutton = new JButton("Find Cover");
	coverbutton.addActionListener(bwatch);
	coverbutton.setPreferredSize(new Dimension(130, 30));

	JLabel ccountlabel = new JLabel("Cover size");
	ccountfield = new JTextField();
	ccountfield.setPreferredSize(new Dimension(40,20));
   */
   
      listpanel.add(vertexpane);
      listpanel.add(countlabel);
      listpanel.add(countfield);
      listpanel.add(new JLabel("                      "));
      listpanel.add(new JLabel("Load graph"));
      listpanel.add(filebutton);
      listpanel.add(randbutton);
	
	//listpanel.add(new JLabel("Smallest Cover"));
	//listpanel.add(coverpane);
	//listpanel.add(ccountlabel);
	//listpanel.add(ccountfield);
	//listpanel.add(coverbutton);

	// VERTEX PANEL

      JPanel vertexpanel = new JPanel();
      vertexpanel.setPreferredSize(new Dimension(300,450));
      vertexpanel.setBackground(Color.green);

      JLabel vertexlabel = new JLabel("          Current Vertex          ");
      vertexfield = new JTextField();
      vertexfield.setPreferredSize(new Dimension(200,20));
      vertexpanel.add(vertexlabel);
      vertexpanel.add(vertexfield);

      JPanel vbuttonpanel = new JPanel();
      vbuttonpanel.setPreferredSize(new Dimension(290,40));
      vbuttonpanel.setBackground(Color.green);
      vinsertbutton = new JButton("Insert");
      vinsertbutton.addActionListener(bwatch);
      vinsertbutton.setPreferredSize(new Dimension(90, 30));
      vremovebutton = new JButton("Delete");
      vremovebutton.addActionListener(bwatch);
      vremovebutton.setPreferredSize(new Dimension(90, 30));
      vbuttonpanel.add(vinsertbutton);
      vbuttonpanel.add(vremovebutton);
      vertexpanel.add(vbuttonpanel);

      JLabel outcountlabel = new JLabel("Out Degree");
      outcountfield = new JTextField();
      outcountfield.setPreferredSize(new Dimension(40,20));
      vertexpanel.add(outcountlabel);
      vertexpanel.add(outcountfield);
      
      JLabel incountlabel = new JLabel("  In Degree");
      incountfield = new JTextField();
      incountfield.setPreferredSize(new Dimension(40,20));
      vertexpanel.add(incountlabel);
      vertexpanel.add(incountfield);

      JLabel neighborlabel = new JLabel("              Neighbors              ");
      neighborlist = new DefaultListModel();
      neighborbox = new JList(neighborlist);
      JScrollPane neighborpane = new JScrollPane(neighborbox);
      neighborpane.setPreferredSize(new Dimension(140,280));
      JPanel cbuttonpanel = new JPanel();
      cbuttonpanel.setPreferredSize(new Dimension(150,90));
      cbuttonpanel.setBackground(Color.green);
      clearbutton = new JButton("Clear Graph");
      clearbutton.addActionListener(bwatch);
      cbuttonpanel.add(clearbutton);

      refreshbutton = new JButton("Refresh Display");
      refreshbutton.addActionListener(bwatch);
      cbuttonpanel.add(refreshbutton);

      treebutton = new JButton("Tree");
      treebutton.addActionListener(bwatch);
      cbuttonpanel.add(treebutton);

      vertexpanel.add(neighborlabel);
      vertexpanel.add(neighborpane);
      vertexpanel.add(cbuttonpanel);

	// EDGE PANEL

      JPanel edgepanel = new JPanel();
      edgepanel.setPreferredSize(new Dimension(150,450));
      edgepanel.setBackground(Color.yellow);

      JPanel fieldpanel = new JPanel();
      fieldpanel.setPreferredSize(new Dimension(145,100));
      fieldpanel.setBackground(Color.yellow);

      JLabel startlabel = new JLabel("      From         ");
      startfield = new JTextField();
      startfield.setPreferredSize(new Dimension(90,20));
      JLabel endlabel = new JLabel("       To        ");
      endfield = new JTextField();
      endfield.setPreferredSize(new Dimension(90,20));

      fieldpanel.add(startlabel);
      fieldpanel.add(startfield);
      fieldpanel.add(endlabel);
      fieldpanel.add(endfield);

      JPanel buttonpanel = new JPanel();
      buttonpanel.setPreferredSize(new Dimension(140,100));
      buttonpanel.setBackground(Color.yellow);
      einsertbutton = new JButton("Insert");
      einsertbutton.addActionListener(bwatch);
      einsertbutton.setPreferredSize(new Dimension(100, 30));
      eremovebutton = new JButton("Delete");
      eremovebutton.addActionListener(bwatch);
      eremovebutton.setPreferredSize(new Dimension(100, 30));

      buttonpanel.add(new JLabel("         Edge          "));
      buttonpanel.add(einsertbutton);
      buttonpanel.add(eremovebutton);
	
      JPanel pathpanel = new JPanel();
      pathpanel.setPreferredSize(new Dimension(140,275));
      pathpanel.setBackground(Color.yellow);

      spathbutton = new JButton("Find Path");
      spathbutton.addActionListener(bwatch);
      spathbutton.setPreferredSize(new Dimension(130, 30));

      JLabel thrulabel = new JLabel("     Through      ");
      thrufield = new JTextField();
      thrufield.setPreferredSize(new Dimension(90,20));

      pathlist = new DefaultListModel();
      pathbox = new JList(pathlist);
      JScrollPane pathpane = new JScrollPane(pathbox);
      pathpane.setPreferredSize(new Dimension(120,165));
      
      pathpanel.add(new JLabel("         Path         "));
      pathpanel.add(spathbutton);
      pathpanel.add(thrulabel);
      pathpanel.add(thrufield);      
      pathpanel.add(pathpane);

      edgepanel.add(fieldpanel);
      edgepanel.add(buttonpanel);
      edgepanel.add(pathpanel);

      getContentPane().add(vertexpanel, BorderLayout.CENTER);
      getContentPane().add(edgepanel, BorderLayout.EAST);
      getContentPane().add(listpanel, BorderLayout.WEST);
      getContentPane().add(messagepanel, BorderLayout.SOUTH);
   }

    
   private void initmenu()
   {
      loadclass = new JMenuItem("Load graph class");
      loadclass.addActionListener(mwatch);
      JMenuBar bar = new JMenuBar();
      bar.add(loadclass);
      setJMenuBar(bar);
   }

   private Object getClassInstance(String classname)
   {
      Object instance;
      Class newclass;
      try
      {
         newclass = Class.forName(classname);
         instance = newclass.newInstance();
         System.err.println("Loaded class name: " + newclass.getName());
         return instance;
      }
      catch (Exception e)
      {
         System.err.println("Error loading graph class. "+e.toString());
         return null;
      }
   }

   private void loadGraphClass()
   {
      JFileChooser finder = new JFileChooser("./");
      finder.showOpenDialog(this);
      String classname = (finder.getSelectedFile()).getName();
      classname = classname.substring(0,classname.indexOf("."));
      System.err.println("Loading class " + classname);
      graph = (DiGraph) getClassInstance(classname);
   }


   private void refreshDisplay()
   {
      int count = graph.getVertexCount();
      listening = false;
      vertexlist.clear();
      neighborlist.clear();
      pathlist.clear();
      //coverlist.clear();
      messagefield.setText("");
      countfield.setText(String.valueOf(count));
      //ccountfield.setText("");
      String vname = vertexfield.getText();
      vertexbox.clearSelection();
      copyList(graph.getVertexList(), vertexlist);
      if (!vname.equals(""))
      {    
         copyList(graph.getNeighbors(vname), neighborlist);
         outcountfield.setText(""+graph.getOutDegree(vname));
         incountfield.setText(""+graph.getInDegree(vname));
      }
      listening = true;
   }
	
   private void copyList(StringList list, DefaultListModel deflist)
   {
      if (list == null)
         return;
      for (String next: list)
         deflist.addElement(next);
   }

   private void clearGraph()
   {
      graph.clear();
      vertexfield.setText("");
      startfield.setText("");
      endfield.setText("");
      thrufield.setText("");
      outcountfield.setText("");
      incountfield.setText("");
      refreshDisplay();
   }

   private void insertVertex()
   {
      String item = new String(vertexfield.getText());
      graph.addVertex(item);
      refreshDisplay();
   }

   private void removeVertex()
   {
      String item = new String(vertexfield.getText());
      boolean ritem;
      messagefield.setText("");
      ritem = graph.removeVertex(item);
      if (ritem == false)
         messagefield.setText("Vertex "+item+ " not in graph");
      else
      {
         vertexfield.setText("");
         refreshDisplay();
         messagefield.setText(item+" removed");
      }
   }

   private void insertEdge()
   {
      messagefield.setText("");
      String startnode = startfield.getText();
      String stopnode = endfield.getText();
      graph.addEdge(startnode, stopnode);
      refreshDisplay();
   }

   private void removeEdge()
   {
      messagefield.setText("");
      String startnode = startfield.getText();
      String stopnode = endfield.getText();

      if (graph.hasEdge(startnode, stopnode))
      {
         graph.removeEdge(startnode, stopnode);
         refreshDisplay();
      }
      else
         messagefield.setText("The vertices are not adjacent");
   }

   private void checkPath()
   {
      String startnode = startfield.getText();
      String stopnode = endfield.getText();
      String midnode = thrufield.getText();
      StringList path;

      pathlist.clear();
      if (midnode.equals(""))
         path = graph.getShortestPath(startnode, stopnode);
      else
         path = graph.getPathThrough(startnode, midnode, stopnode);
         
      if (path != null)
      {
         copyList(path, pathlist);
         messagefield.setText("Found path from "+startnode+" to "+stopnode);
      }
      else
         messagefield.setText("No path found");
   }

   private void showTree()
   {
      messagefield.setText(graph.getBFTree(vertexfield.getText()));
   }

   private void readGraph()
   {
      clearGraph();
      graph = gman.chooseGraph(graph);
      refreshDisplay();
   }
   
   private void randGraph()
   {
      clearGraph();
      graph = gman.generateGraph(graph);
      refreshDisplay();
   }

   private class WindowWatch extends WindowAdapter
   {
      public void windowClosing(WindowEvent event)
      {
         dispose();
         System.exit(0);
      }
   }

   private class MenuWatch implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         Object source = event.getSource();
         if (source == loadclass)
            loadGraphClass();
      }
   }

   private class ListWatch implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent event)
      {
         if (listening)
         {
            String vname = (String) vertexbox.getSelectedValue();
            if (vname == null)
               return;
            vertexfield.setText(vname);
            refreshDisplay();
         }
      }
   }

   private class ButtonWatch implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         Object source = event.getSource();
         if (source == vinsertbutton)
            insertVertex();
         else if (source == vremovebutton)
            removeVertex();
         else if (source == clearbutton)
            clearGraph();
         else if (source == einsertbutton)
            insertEdge();
         else if (source == eremovebutton)
            removeEdge();
         else if (source == refreshbutton)
            refreshDisplay();
         else if (source == spathbutton)
            checkPath();
         else if (source == treebutton)
            showTree();
         else if (source == filebutton)
            readGraph();
         else if (source == randbutton)
            randGraph();
      }
   }

   public Dimension getPreferredSize()
   {
      return new Dimension(600,620);
   }

   public static void main(String[] args)
   {
      DiGraphTest testbox = new DiGraphTest();
      testbox.pack();
      testbox.setVisible(true);
      JOptionPane.showMessageDialog(testbox, "Step 1:\nChoose a Graph implementation class");
      testbox.loadGraphClass();
   }
}
































