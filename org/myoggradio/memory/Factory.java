package org.myoggradio.memory;
import org.myoggradio.memory.impl.SimpleBrett;
public class Factory 
{
	public static Brett getBrett()
	{
		return new SimpleBrett();
	}
	
}
