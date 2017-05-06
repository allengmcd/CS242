import javax.swing.*;
import java.util.*;

class StringListModel extends AbstractListModel implements Iterable<String>
{
   StringList list;

	public StringListModel(StringList s)
	{
		list = s;
	}

   public Object getElementAt (int index)
   {
	   if (index < 0 || index >= list.getSize())
	      return null;
      else
	      return new String(list.getElementAt(index));
   }

   public void add(String newstr)
   {
	   if (list.add(newstr))
	   {
	      fireContentsChanged(this, 0, list.getSize());
	   }
   }

   public void addAll(StringListModel other)
   {
	   list.addAll(other.getData());
	   fireContentsChanged(this, 0, list.getSize());
   }

	public boolean delete(String x)
	{
		if (list.delete(x))
		{
			fireContentsChanged(this, 0, list.getSize());
			return true;
		}
		else return false;
	}

   public void deleteAll(StringListModel other)
   {
	   list.deleteAll(other.getData());
	   fireContentsChanged(this, 0, list.getSize());
   }

	public boolean contains(String target)
	{
		return list.contains(target);
	}

   public void clear()
   {
	   list.clear();
	   fireContentsChanged(this, 0, list.getSize());
   }

	public Iterator<String> iterator()
	{
		return list.iterator();
	}

   public int getSize()
   {
	   return list.getSize();
   }
   
   public StringList getData()
   {
		return list;
   }
   
   public void update()
   {
   	fireContentsChanged(this, 0, list.getSize());
   }
}


