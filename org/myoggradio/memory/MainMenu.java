package org.myoggradio.memory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class MainMenu extends Menu implements ActionListener,BrettListener
{
	static final long serialVersionUID = 1;
	private JMenuBar bar = new JMenuBar();
	private JMenu m1 = new JMenu("Spiel");
	private JMenuItem m11 = new JMenuItem("restart");
	private JMenu m2 = new JMenu("Info");
	private JMenuItem m21 = new JMenuItem("Version");
	private JPanel cpan = null;
	private JPanel bpan = null;
	private JLabel lab1 = null;
	private JLabel lab2 = null;
	private String[][] feldNamen = new String[4][4];
	private boolean[][] aufgedeckt = new boolean[4][4];
	private Brett brett = Factory.getBrett();
	private Position vorherigePosition = null;
	private Position aktuellePosition = null;
	private int aufgedecktCount = 0;
	private int clickCount = 0;
	private long startZeit = new Date().getTime();
	public MainMenu(String[] bildNamen)
	{
		bar.add(m1);
		bar.add(m2);
		m1.add(m11);
		m2.add(m21);
		m11.addActionListener(this);
		m21.addActionListener(this);
		setJMenuBar(bar);
		for (int i=0;i<4;i++)
		{
			for (int j=0;j<4;j++)
			{
				aufgedeckt[i][j] = false;
				feldNamen[i][j] = bildNamen[i*4+j];
				System.out.println(i + ":" + j + ":" + feldNamen[i][j]);
			}
		}
		brett.addBrettListener(this);
		cpan = new JPanel();
		cpan.setLayout(new BorderLayout());
		cpan.add(brett,BorderLayout.NORTH);
		bpan = new JPanel();
		bpan.setLayout(new BorderLayout());
		lab1 = new JLabel("" + clickCount);
		lab2 = new JLabel("" + aufgedecktCount);
		lab1.setText("Clicks " + clickCount + " Sekunden " + 0);
		lab2.setText("Gefunden " + aufgedecktCount);
		bpan.add(lab1,BorderLayout.WEST);
		bpan.add(new JPanel(),BorderLayout.CENTER);
		bpan.add(lab2,BorderLayout.EAST);
		cpan.add(bpan,BorderLayout.SOUTH);
		setContentPane(cpan);
		validate();
	}
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object quelle = ae.getSource();
		if (quelle == m11)
		{
			Verarbeitung v = new Verarbeitung();
			v.start();
			dispose();
		}
		if (quelle == m21)
		{
			JOptionPane.showMessageDialog(null,"Version 0.01 - 10.02.2023");
		}
	}
	@Override
	public void clickedOn(Position p) 
	{
		int x = p.getX();
		int y = p.getY();
		if (!aufgedeckt[x][y])
		{
			boolean ok = true;
			if (vorherigePosition != null)
			{
				int u = vorherigePosition.getX();
				int v = vorherigePosition.getY();
				if (x==u && y==v) ok = false;
			}
			if (aktuellePosition != null)
			{
				int u = aktuellePosition.getX();
				int v = aktuellePosition.getY();
				if (x==u && y==v) ok = false;
			}
			if (ok)
			{
				clickCount++;
				brett.setBild(p,feldNamen[p.getX()][p.getY()]);
				if (aktuellePosition != null)
				{
					brett.setBild(vorherigePosition,"background");
					brett.setBild(aktuellePosition,"background");
					vorherigePosition = null;
					aktuellePosition = null;
				}
				if (vorherigePosition == null)
				{
					vorherigePosition = p;
				}
				else
				{
					int x1 = vorherigePosition.getX();
					int y1 = vorherigePosition.getY();
					int x2 = p.getX();
					int y2 = p.getY();
					String bn1 = feldNamen[x1][y1];
					String bn2 = feldNamen[x2][y2];
					if (bn1.equals(bn2))
					{
						aktuellePosition = null;
						vorherigePosition = null;
						aufgedeckt[x1][y1] = true;
						aufgedeckt[x2][y2] = true;
						aufgedecktCount++;
						aufgedecktCount++;
					}
					else
					{
						aktuellePosition = p;
					}
				}
				long jetzt = new Date().getTime();
				long dauer = (jetzt - startZeit)/1000;
				lab1.setText("Clicks " + clickCount + " Sekunden " + dauer);
				lab2.setText("Gefunden " + aufgedecktCount);
				if (aufgedecktCount > 15)
				{
					System.out.println("Glückwunsch Sie haben alles in " +  clickCount + " Clicks aufgedeckt");
				}
			}
		}
	}
}
