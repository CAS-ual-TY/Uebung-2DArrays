
public class Loesung
{
	public static void main(String[] args)
	{
		int variablen = 3;
		int extraSpalten = 2;
		
		int spalten = variablen + extraSpalten + 1;
		
		// ----------------------------------------------------------------------------------------------------------- //
		// ----------------------------------------------------------------------------------------------------------- //
		
		boolean[][] tabelle = erstelleTrueTabelle(variablen, spalten);
		ausgeben(tabelle, variablen); /*
		
		T T T || T T || T 
		T T T || T T || T 
		T T T || T T || T 
		T T T || T T || T 
		T T T || T T || T 
		T T T || T T || T 
		T T T || T T || T 
		T T T || T T || T 
		
		*/
		
		// ----------------------------------------------------------------------------------------------------------- //
		// ----------------------------------------------------------------------------------------------------------- //
		
		befuelleVariablenSpalten(tabelle, variablen);
		ausgeben(tabelle, "a", "b", "c"); /*
		
		a b c 
		F F F || T T || T 
		F F T || T T || T 
		F T F || T T || T 
		F T T || T T || T 
		T F F || T T || T 
		T F T || T T || T 
		T T F || T T || T 
		T T T || T T || T 
		
		*/
		
		// ----------------------------------------------------------------------------------------------------------- //
		// ----------------------------------------------------------------------------------------------------------- //
		
		boolean[] zeile;
		for(int i = 0; i < tabelle.length; ++i)
		{
			zeile = tabelle[i];
			
			// a AND b IMPL c OR a
			zeile[3] = AND(zeile[0], zeile[1]);
			zeile[4] = OR(zeile[2], zeile[0]);
			zeile[5] = IMPL(zeile[3], zeile[4]);
		}
		ausgeben(tabelle, "a", "b", "c"); /*
		
		a b c 
		F F F || F F || T 
		F F T || F T || T 
		F T F || F F || T 
		F T T || F T || T 
		T F F || F F || T 
		T F T || F T || T 
		T T F || T F || F 
		T T T || T T || T 
		
		*/
		
		// ----------------------------------------------------------------------------------------------------------- //
		// ----------------------------------------------------------------------------------------------------------- //
		
		ausgeben01(tabelle, variablen); /*
		
		1 1 1 || 1 1 || 0 
		1 1 0 || 1 0 || 0 
		1 0 1 || 1 1 || 0 
		1 0 0 || 1 0 || 0 
		0 1 1 || 1 0 || 0 
		0 1 0 || 1 0 || 0 
		0 0 1 || 0 0 || 0 
		0 0 0 || 0 0 || 0 
		
		*/
		
	}
	
	public static boolean[][] erstelleTrueTabelle(int variablen, int spalten)
	{
		int zeilen = zweierPotenz(variablen);
		boolean[][] tabelle = new boolean[zeilen][];
		
		for(int i = 0; i < zeilen; ++i)
		{
			tabelle[i] = new boolean[spalten];
			
			for(int j = 0; j < spalten; ++j)
			{
				tabelle[i][j] = true;
			}
		}
		
		return tabelle;
	}
	
	public static void befuelleVariablenSpalten(boolean[][] tabelle, int variablen)
	{
		int zeilen = zweierPotenz(variablen);
		for(int i = 0; i < variablen; ++i)
		{
			for(int j = 0; j < zeilen; ++j)
			{
				/*
				 * (1) j
				 * 		Die Zeile in der wir uns befinden
				 * 
				 * (2) i
				 * 		Die #te Variable die wir durchgehen (nicht die Spalte, siehe (6)).
				 * 
				 * (3) zweierPotenz(i)
				 * 		Fuer die erste Variable ist das 1,
				 * 		fuer die zweite ist es 2,
				 * 		fuer die dritte ist es 4,
				 * 		etc..
				 * 
				 * (4) j / zweierPotenz(i)
				 * 		j / 1 wird zu 0, 1, 2, 3, 4, 5...
				 * 		j / 2 wird zu 0, 0, 1, 1, 2, 2...
				 * 		j / 4 wird zu 0, 0, 0, 0, 1, 1...
				 * 
				 * (5) ... % 2 == 1
				 * 		true: Wenn x ungerade ist,
				 * 		false wenn x gerade ist
				 * 
				 * (6) variablen - 1 - i
				 * 		Wir wollen von Hinten anfangen. Sonst wird die erste Spalte sich immer abwechseln, und nicht die letzte (der Variablen).
				 * 		- 1 Da wir sonst beim ersten Durchlauf den Index variablen haben (variablen - 0).
				 * 
				 */
				
				tabelle[j][variablen - i - 1] = ((j / (zweierPotenz(i))) % 2 == 1);
			}
		}
	}
	
	public static int zeilenBenoetigt(int variablen)
	{
		return zweierPotenz(variablen);
	}
	
	public static int zweierPotenz(int potenz)
	{
		int r = 1;
		
		for(int i = 0; i < potenz; ++i)
		{
			r *= 2;
		}
		
		return r;
	}
	
	public static boolean AND(boolean b1, boolean b2)
	{
		return b1 && b2;
	}
	
	public static boolean OR(boolean b1, boolean b2)
	{
		return b1 || b2;
	}
	
	public static boolean IMPL(boolean b1, boolean b2)
	{
		return OR(!b1, b2);
	}
	
	public static boolean NOT(boolean b1)
	{
		return !b1;
	}
	
	public static void ausgeben(boolean[][] tabelle, String... variablen)
	{
		for(String s : variablen)
		{
			System.out.print(s + " ");
		}
		System.out.println();
		
		ausgeben(tabelle, variablen.length);
	}
	
	public static void ausgeben(boolean[][] tabelle, int variablen)
	{
		ausgeben(tabelle, variablen, "T ", "F ");
	}
	
	public static void ausgeben01(boolean[][] tabelle, int variablen)
	{
		ausgeben(tabelle, variablen, "0 ", "1 ");
	}
	
	public static void ausgeben(boolean[][] tabelle, int variablen, String t, String f)
	{
		boolean[] zeile;
		boolean wert;
		
		for(int i = 0; i < tabelle.length; ++i)
		{
			zeile = tabelle[i];
			
			for(int j = 0; j < zeile.length; ++j)
			{
				//i ist die Zeile
				//j ist die Spalte
				
				wert = tabelle[i][j];
				
				if(j == variablen || j == zeile.length - 1)
				{
					System.out.print("|| ");
				}
				
				System.out.print(wert ? t : f);
			}
			
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
	}
}
