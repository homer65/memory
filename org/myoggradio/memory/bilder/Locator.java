package org.myoggradio.memory.bilder;
import java.net.*;
public class Locator 
{
	public URL getURL(String pfad)
	{
		URL url = this.getClass().getResource(pfad);
		return url;
	}
}
