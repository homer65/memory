package org.myoggradio.memory.impl;
import org.myoggradio.memory.Brett;
import org.myoggradio.memory.BrettListener;
import org.myoggradio.memory.ImagePanel;
import org.myoggradio.memory.ImagePanelListener;
import org.myoggradio.memory.Position;
import org.myoggradio.memory.bilder.Locator;
import java.awt.*;
import java.net.URL;
import java.util.*;
public class SimpleBrett extends Brett implements ImagePanelListener
{
	public static final long serialVersionUID = 1;
	private ImagePanel[][] feldPanel = new ImagePanel[4][4];
	private String[][] feldNamen = new String[4][4];
	private Locator locator = new Locator();
	private Color color = new Color(255,0,0);
	private ArrayList<BrettListener> listener = new ArrayList<BrettListener>();
	public SimpleBrett()
	{
		this.setLayout(new GridLayout(4,4));
		for (int x=0;x<4;x++)
		{
			for (int y=0;y<4;y++)
			{
				Position feld = new Position();
				feld.setX(x);
				feld.setY(y);
				String name = "background";
				URL url = locator.getURL(name + ".png");
				Image img = Toolkit.getDefaultToolkit().getImage(url); 
				feldPanel[x][y] = new ImagePanel(img,feld);
				feldPanel[x][y].setColor(color);
				feldPanel[x][y].addListener(this);
				feldNamen[x][y] = name;
				add(feldPanel[x][y],x,y);
			}
		}
	}
	public void pressedOnImage(Position feld) 
	{
		System.out.println("BrettPanel:pressedOnImage:" + feld.getX() + ":" + feld.getY());
		for (int i=0;i<listener.size();i++)
		{
			BrettListener bl = listener.get(i);
			bl.clickedOn(feld);
		}
	}
	@Override
	public void addBrettListener(BrettListener bl) 
	{
		listener.add(bl);	
	}
	@Override
	public void setBild(Position p, String bildname) 
	{
		this.removeAll();
		this.setLayout(new GridLayout(4,4));
		int x = p.getX();
		int y = p.getY();
		feldNamen[x][y] = bildname;
		for (x=0;x<4;x++)
		{
			for (y=0;y<4;y++)
			{
				bildname = feldNamen[x][y];
				p = new Position();
				p.setX(x);
				p.setY(y);
				System.out.println(bildname);
				URL url = locator.getURL(bildname + ".png");
				Image img = Toolkit.getDefaultToolkit().getImage(url); 
				feldPanel[x][y] = new ImagePanel(img,p);
				feldPanel[x][y].setColor(color);
				feldPanel[x][y].addListener(this);	
				add(feldPanel[x][y],x,y);
			}
		}
		repaint();
		validate();
	}
	@Override
	public String getBild(Position p) 
	{
		return feldNamen[p.getX()][p.getY()];
	}
}
