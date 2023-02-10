package org.myoggradio.memory;
/*
 * Position auf dem Brett; erlaubte Werte fuer x und y sind 0,1,2,3
 */
public class Position
{
	private int x = 0;
	private int y = 0;
	public void setX(int i)
	{
		x = i;
	}
	public void setY(int i)
	{
		y = i;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
}
