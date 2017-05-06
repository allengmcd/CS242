import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

class StringListTest extends JFrame
{
	ButtonWatch bwatch;
	JButton loadclass, runscript;
	ListWatch lwatch;
	JList listbox, secondbox;
	StringListModel displaylist, secondlist;
	JTextField lengthfield1, lengthfield2;
	TestPanel testpanel1;
	TestPanel testpanel2;

   public StringListTest()
   {
      super("String List Testing Program");
      addWindowListener(new WindowWatch());

      lwatch = new ListWatch();
		bwatch = new ButtonWatch();

		JPanel toppanel = new JPanel();
		toppanel.setPreferredSize(new Dimension(775, 40));
		toppanel.setBackground(LocalColor.GREEN);
		loadclass = new JButton("Load Set Class");
		loadclass.addActionListener(bwatch);
		runscript = new JButton("Run Script");
		runscript.addActionListener(bwatch);
		toppanel.add(loadclass);
		toppanel.add(runscript);

      JPanel listpanel = new JPanel();
      listpanel.setPreferredSize(new Dimension(395,420));
      listpanel.setBackground(LocalColor.TAN);

      JPanel leftpanel = new JPanel();
      leftpanel.setPreferredSize(new Dimension(175,420));
      leftpanel.setBackground(LocalColor.TAN);

		JPanel divider = new JPanel();
		divider.setPreferredSize(new Dimension(30, 420));
		divider.setBackground(LocalColor.GREEN);

      JPanel rightpanel = new JPanel();
      rightpanel.setPreferredSize(new Dimension(175,420));
      rightpanel.setBackground(LocalColor.TAN);

      leftpanel.add(new JLabel("          Set 1          "));
      rightpanel.add(new JLabel("          Set 2           "));
      listbox = new JList();
      listbox.addListSelectionListener(lwatch);
      JScrollPane listpane = new JScrollPane(listbox);
      listpane.setPreferredSize(new Dimension(150,320));
      leftpanel.add(listpane);

      secondbox = new JList();
      secondbox.addListSelectionListener(lwatch);
      JScrollPane secondpane = new JScrollPane(secondbox);
      secondpane.setPreferredSize(new Dimension(150,320));
      rightpanel.add(secondpane);
      
      
      lengthfield1 = new JTextField();
      lengthfield1.setPreferredSize(new Dimension(40,20));
      leftpanel.add(new JLabel("     Size: "));
      leftpanel.add(lengthfield1);
      lengthfield2 = new JTextField();
      lengthfield2.setPreferredSize(new Dimension(40,20));
      rightpanel.add(new JLabel("     Size: "));
      rightpanel.add(lengthfield2);
      
      listpanel.add(leftpanel);
      listpanel.add(divider);
      listpanel.add(rightpanel);
	
      testpanel1 = new TestPanel();
      testpanel2 = new TestPanel();
	
		getContentPane().add(toppanel, BorderLayout.NORTH);      
      getContentPane().add(testpanel1, BorderLayout.WEST);
      getContentPane().add(listpanel, BorderLayout.CENTER);
      getContentPane().add(testpanel2, BorderLayout.EAST);
 
      lengthfield1.setText("0");
      lengthfield2.setText("0");
   }

//////////////////////  Inner class TestPanel  /////////////////////////////

   private class TestPanel extends JPanel implements ActionListener
   {
      StringListModel list;
      JTextField itemfield;
      JButton insertbutton, removebutton, clearbutton, findbutton, subtractbutton,
         mergebutton, printbutton;
         
      public TestPanel()
      {
         super();
         setPreferredSize(new Dimension(150,420));
         setBackground(LocalColor.TAN);
         
         itemfield = new JTextField();
			itemfield.setPreferredSize(new Dimension(100,20));
			
         insertbutton = new JButton("Add");
         insertbutton.addActionListener(this);
         insertbutton.setPreferredSize(new Dimension(100, 30));
	    
         subtractbutton = new JButton("Subtract");
         subtractbutton.addActionListener(this);
         subtractbutton.setPreferredSize(new Dimension(100, 30));

         findbutton = new JButton("Find");
         findbutton.addActionListener(this);
         findbutton.setPreferredSize(new Dimension(100, 30));
	    
         removebutton = new JButton("Remove");
         removebutton.addActionListener(this);
         removebutton.setPreferredSize(new Dimension(100, 30));

         clearbutton = new JButton("Clear");
         clearbutton.addActionListener(this);
         clearbutton.setPreferredSize(new Dimension(100, 30));

         mergebutton = new JButton("Merge");
         mergebutton.addActionListener(this);
         mergebutton.setPreferredSize(new Dimension(100, 30));
         
         printbutton = new JButton("Print");
         printbutton.addActionListener(this);
         printbutton.setPreferredSize(new Dimension(100, 30));

         add(new Spacer(150, 30, LocalColor.TAN));
			add(itemfield);
			add(new Spacer(150, 10, LocalColor.TAN));        
         add(insertbutton);
         add(removebutton);
         add(findbutton);
         add(clearbutton);
         add(mergebutton);
         add(subtractbutton);
         add(printbutton);
      }

		public void setModel(StringListModel dlist)
		{
			list = dlist;
		}

      public String getItem()
      {
         String item = itemfield.getText();
         if (item != "")
            return item;
         else
         {
            JOptionPane.showMessageDialog(this, "Type a string in the item box and try again");
            return null;
         }
      }

      public void setItem(String s)
      {
         itemfield.setText(s);
      }

      public void actionPerformed(ActionEvent event)
      {
         Object source = event.getSource();

         if (source == insertbutton)
            insertItem(list, getItem());
         else if (source == removebutton)
            removeItem(list, getItem());
         else if (source == findbutton)
            findItem(list, getItem());
         else if (source == clearbutton)
            clearList(list);
         else if (source == mergebutton)
            merge(list);
         else if (source == subtractbutton)
         	subtract(list);
         else if (source == printbutton)
            printStrings(list);
      }
   }
//////////////////////  end TestPanel   //////////////////////////


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
			System.err.println("Error loading list class. "+e.toString());
			return null;
		}
	}

	private void loadListClass()
	{
		JOptionPane.showMessageDialog(this, "Choose a List Class");
		JFileChooser finder = new JFileChooser();
		finder.showOpenDialog(this);
		String classname = (finder.getSelectedFile()).getName();
		classname = classname.substring(0,classname.indexOf("."));
		System.err.println("Loading class " + classname);
		displaylist = new StringListModel((StringList) getClassInstance(classname));
		secondlist = new StringListModel((StringList) getClassInstance(classname));
		initialize();
	}

	private void runScript()
	{
		JOptionPane.showMessageDialog(this, "Choose a Script File");
		JFileChooser finder = new JFileChooser();
		finder.showOpenDialog(this);
		String filename = (finder.getSelectedFile()).getName();
		try {
			ScriptTool script =
				new ScriptTool(new FileInputStream(filename), displaylist.getData(), secondlist.getData());
			script.run();
			displaylist.update();
			secondlist.update();
			refreshDisplay();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}

   private void initialize()
   {
      lengthfield1.setText("0");
      lengthfield2.setText("0");
      listbox.setModel(displaylist);
      testpanel1.setModel(displaylist);
      secondbox.setModel(secondlist);
      testpanel2.setModel(secondlist);
   }

   private void refreshDisplay()
   {
      String item1, item2;

      lengthfield1.setText(String.valueOf(displaylist.getSize()));
      lengthfield2.setText(String.valueOf(secondlist.getSize()));

      if (listbox.getSelectedValue() == null)
         item1 = "";
      else
         item1 = listbox.getSelectedValue().toString();
      if (secondbox.getSelectedValue() == null)
         item2 = "";
      else
         item2 = secondbox.getSelectedValue().toString();
      testpanel1.setItem(item1);
      testpanel2.setItem(item2);
   }

   private void clearList(StringListModel list)
   {
      list.clear();
      refreshDisplay();
   }

   private void insertItem(StringListModel list, String x)
   {
      list.add(x);
      refreshDisplay();
   }

   private void removeItem(StringListModel list, String x)
   {
      boolean result = list.delete(x);
      if (!result)
      {
         JOptionPane.showMessageDialog(this, x +" not in list");
         return;
      }
      refreshDisplay();
   }
   
   private void merge(StringListModel list)
   {
      System.out.println("Call to merge");
      StringListModel otherlist;
      if (list == displaylist)
      {
         System.out.println("Setting other to secondlist");
         otherlist = secondlist;
      }
      else
      {
         System.out.println("Setting other to displaylist");
         otherlist = displaylist;
      }
      list.addAll(otherlist);
      otherlist.clear();
      refreshDisplay();
   }

   private void subtract(StringListModel list)
   {
      StringListModel otherlist;
      if (list == displaylist)
      {
         System.out.println("Setting other to secondlist");
         otherlist = secondlist;
      }
      else
      {
         System.out.println("Setting other to displaylist");
         otherlist = displaylist;
      }
      list.deleteAll(otherlist);
      otherlist.clear();
      refreshDisplay();
   }


   private void printStrings(StringListModel list)
   {
      System.out.println("\nSet contents: ");
      for (String x: list)
         System.out.print(" " + x);
      System.out.println();
   }   

   private void findItem(StringListModel list, String x)
   {
      if (!list.contains(x))
         JOptionPane.showMessageDialog(this, x + " not in list");
      else
         JOptionPane.showMessageDialog(this, x + " found");
   }

   private class WindowWatch extends WindowAdapter
   {
      public void windowClosing(WindowEvent event)
      {
         dispose();
         System.exit(0);
      }
   }

	private class ButtonWatch implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if (source == loadclass)
				loadListClass();
			else if (source == runscript)
				runScript();
		}
	}

   private class ListWatch implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent event)
      {
         Object source = event.getSource();
			if (source == listbox && listbox.getSelectedValue() != null)
            testpanel1.setItem(listbox.getSelectedValue().toString());
         else if (source == secondbox && secondbox.getSelectedValue() != null)
            testpanel2.setItem(secondbox.getSelectedValue().toString());
      }
   }

   private class Spacer extends JPanel
   {
      public Spacer(int width, int height, Color color)
      {
         super();
         setPreferredSize(new Dimension(width, height));
         setBackground(color);
      }
   }

    public static void main(String[] args)
    {
	    StringListTest testbox = new StringListTest();
	    testbox.pack();
	    testbox.setVisible(true);
	    testbox.loadclass.doClick();
    }
}

class LocalColor
{
    static public final Color TAN = new Color(255,235,175);
    static public final Color LIGHT_YELLOW = new Color(255,255,175);
    static public final Color LIGHT_GRAY = new Color(245,240,230);
    static public final Color LIGHT_GREEN = new Color(210,240,200);
    static public final Color GREEN = new Color(80,190,0);
    static public final Color BROWN = new Color(140, 8, 0);
    static public final Color ORANGE = new Color(255, 150, 0);
    static public final Color PURPLE = new Color(160, 10, 255);
    static public final Color DARK_BLUE = new Color(70, 70, 130);
    static public final Color BLUE_GRAY = new Color(175, 200, 220);
 }












