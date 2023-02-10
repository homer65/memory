package org.myoggradio.memory;
import javax.swing.JPanel;
public abstract class Brett extends JPanel 
{
	private static final long serialVersionUID = 1L;
	public abstract void addBrettListener(BrettListener bl);
	public abstract void setBild(Position p,String bildname);
	public abstract String getBild(Position p);
}
