package org.myoggradio.memory;
import javax.swing.*;
import java.awt.event.*;
public class Menu extends JFrame implements ActionListener
{
	static final long serialVersionUID = 1;
    public void anzeigen()
    {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
	public void actionPerformed(ActionEvent ae)
	{

	}
}
