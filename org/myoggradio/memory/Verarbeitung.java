package org.myoggradio.memory;
public class Verarbeitung 
{
	public void start()
	{
		String[] bildNamen = getBildNamen();
		MainMenu mm = new MainMenu(bildNamen);
		mm.anzeigen();
	}
	public String[] getBildNamen()
	{
		String[] erg = new String[16];
		String[] ein = new String[16];
		ein[0] = "bild1";
		ein[1] = "bild2";
		ein[2] = "bild3";
		ein[3] = "bild4";
		ein[4] = "bild5";
		ein[5] = "bild6";
		ein[6] = "bild7";
		ein[7] = "bild8";
		ein[8] = "bild1";
		ein[9] = "bild2";
		ein[10] = "bild3";
		ein[11] = "bild4";
		ein[12] = "bild5";
		ein[13] = "bild6";
		ein[14] = "bild7";
		ein[15] = "bild8";
		for (int i=0;i<16;i++)
		{
			int j = 16 - i - 1;
			int r = (int)(Math.random() * j);
			erg[i] = ein[r];
			ein[r] = ein[j];
		}
		return erg;
	}
}
