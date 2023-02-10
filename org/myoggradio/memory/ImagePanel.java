package org.myoggradio.memory;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ImagePanel extends JPanel implements MouseListener
{
	public static final long serialVersionUID = 1;
	private Color color = null;
	private Image img = null;
	private ArrayList<ImagePanelListener> listener = new ArrayList<ImagePanelListener>();
	private Position p = null;
	public ImagePanel(Image img,Position p)
	{
		this.p = p;
		this.img = img;
		Dimension dim = new Dimension(96,96);
		setSize(dim);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		setLayout(null);
		this.addMouseListener(this);
	}
	public void setColor(Color color)
	{
		this.color = color;
		this.repaint();
	}
	public void addListener(ImagePanelListener ipl)
	{
		listener.add(ipl);
	}
	public void paintComponent(Graphics g)
	{
		g.drawImage(img,0,0,this);
		if (color != null)
		{
			g.setColor(color);
			g.drawRect(0,0,96,96);
			g.drawRect(1,1,94,94);
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		for (int i=0;i<listener.size();i++)
		{
			ImagePanelListener ipl = listener.get(i);
			ipl.pressedOnImage(p);
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}
	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}
}
