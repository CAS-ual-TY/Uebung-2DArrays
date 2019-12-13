
public class Main
{
	/*
	 * 	{@link #main(String[])} nicht veraendern. Diese ist schon vorgeschrieben.
	 * 
	 * 	In folgender Reihenfolge machen:
	 * 		{@link #zweierPotenz(int)}
	 * 		{@link #zeilenBenoetigt(int)}
	 * 		{@link #erstelleTrueTabelle(int, int)}
	 * 		{@link #ausgeben(boolean[][], int)}
	 * 		{@link #befuelleVariablenSpalten(boolean[][], int)}
	 * 		{@link #ausgeben(boolean[][], String...)}
	 * 		{@link #AND(boolean, boolean)}
	 * 		{@link #OR(boolean, boolean)}
	 * 		{@link #IMPL(boolean, boolean)}
	 * 		
	 * 	Sollte irgendetwas gar nicht gehen, so kann man alternativ exact die selben Methoden aus der Loesung Klasse callen.
	 * 	Also wo es nicht geht in der methode einfach "Loesung.NAME" (als return) schreiben (ist bereits so angegeben ueberall).
	 * 	Z.B.
	 * 		"ausgeben(...)" -> "Loesung.ausgeben(...)" (kein return da void)
	 * 		"erstelleTrueTabelle(...)" -> "return Loesung.erstelleTrueTabelle(...)".
	 * 
	 * 	Loesungen kann man sich jederzeit in jener Klasse anschauen.
	 */
	public static void main(String[] args)
	{
		// a AND b IMPL c OR a
		
		int variablen = 3;
		int extraSpalten = 2;
		
		int spalten = variablen + extraSpalten + 1; //+1 fuer Loesungs-Spalte
		
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
	
	/**
	 * Erstellt ein neues zweidimensionales boolean-Array mit der gegebenen Anzahl an Spalten und Zeilen und befuellt alle Positionen mit "true".
	 * Z.B Wenn zeilen=4 und spalten=2:
	 * 
	 * {
	 *     {true, true},
	 *     {true, true},
	 *     {true, true},
	 *     {true, true}
	 * }
	 * 
	 * @param zeilen Die Anzahl der Zeilen
	 * @param spalten Die Anzahl der Spalten
	 * @return Die mit "true" befuellte Tabelle (zweidimensionales boolean-Array).
	 */
	public static boolean[][] erstelleTrueTabelle(int variablen, int spalten)
	{
		return Loesung.erstelleTrueTabelle(variablen, spalten);
	}
	
	/**
	 * Befuellt die ersten Spalten der Tabelle, sodass alle T/F Kombinationen vorkommen, fuer die Anzahl der Variablen.
	 * Z.B. Wenn variablen=3 und zeilen=8:
	 * 
	 * {
	 *     {true, true, true, true},
	 *     {true, true, true, true},
	 *     {true, true, true, true},
	 *     {true, true, true, true},
	 *     {true, true, true, true},
	 *     {true, true, true, true},
	 *     {true, true, true, true},
	 *     {true, true, true, true}
	 * }
	 * 
	 * ... wird zu:
	 * 
	 * {
	 *     {false, false, false, true},
	 *     {false, false, true, true},
	 *     {false, true, false, true},
	 *     {false, true, true, true},
	 *     {true, false, false, true},
	 *     {true, false, true, true},
	 *     {true, true, false, true},
	 *     {true, true, true, true}
	 * }
	 * 
	 * @param tabelle Die Tabelle in welche die Kombinationen geschrieben werden sollen.
	 * @param variablen
	 * @param zeilen
	 */
	public static void befuelleVariablenSpalten(boolean[][] tabelle, int variablen)
	{
		Loesung.befuelleVariablenSpalten(tabelle, variablen);
	}
	
	/**
	 * Berechnet wieviele Zeilen benoetigt werden fuer die gegebene Anzahl an Variablen.
	 * @param variablen Anzahl der Variablen
	 * @return Wieviele Zeilen benoetigt werden um alle Kombinationen der Variablen abzudecken.
	 */
	public static int zeilenBenoetigt(int variablen)
	{
		return Loesung.zeilenBenoetigt(variablen);
	}
	
	/**
	 * @return 2 HOCH potenz (Ohne Math.pow()!!!)
	 */
	public static int zweierPotenz(int potenz)
	{
		return Loesung.zweierPotenz(potenz);
	}
	
	/**
	 * @return b1 UND b2
	 */
	public static boolean AND(boolean b1, boolean b2)
	{
		return Loesung.AND(b1, b2);
	}
	
	/**
	 * @return b1 ODER b2
	 */
	public static boolean OR(boolean b1, boolean b2)
	{
		return Loesung.OR(b1, b2);
	}
	
	/**
	 * @return b1 IMPL b2
	 */
	public static boolean IMPL(boolean b1, boolean b2)
	{
		return Loesung.IMPL(b1, b2);
	}
	
	/**
	 * @return NICHT b1
	 */
	public static boolean NOT(boolean b1)
	{
		return Loesung.NOT(b1);
	}
	
	/**
	 * Gib die Tabelle strukturiert aus. Erst werden die Variablen-Werte ausgegeben, dann die Zwischenergebnisse, dann das Endergebnis.
	 * Alle Sektionen getrennt durch '||'. Werte sind entweder 'T' oder 'F'. Nach jedem Wert kommt ausserdem ein ' '.
	 * Beispiel: Siehe Zeile 41.
	 * @param tabelle Die Tabelle
	 * @param variablen Die Anzahl der Variablen
	 */
	public static void ausgeben(boolean[][] tabelle, int variablen)
	{
		Loesung.ausgeben(tabelle, variablen);
	}
	
	/**
	 * Das selbe, wie {@link #ausgeben(boolean[][], int)} nur werden die Variablen-Namen ueber den ersten Spalten mit ausgegeben.
	 * @param tabelle Die Tabelle
	 * @param variablen Ein String[] (Array) welcher die Variablen-Buchstaben enthaelt.
	 */
	public static void ausgeben(boolean[][] tabelle, String... variablen)
	{
		/*
		 * String... variablen
		 * 		Einfach als String[] behandeln. Das '...' erlaubt einfacherer Methoden-Aufrufe. z.B:
		 * 
		 * 		Anstatt:
		 * 			ausgeben(tabelle, new String[] {"a", "b", "c"});
		 * 			ausgeben(tabelle, new String[] {"a", "b", "c", "d", "e"});
		 * 		
		 * 		...geht auch:
		 * 			ausgeben(tabelle, "a", "b", "c");
		 * 			ausgeben(tabelle, "a", "b", "c", "d", "e");
		 * 		
		 * 		Siehe auch Zeile 58.
		 */
		
		Loesung.ausgeben(tabelle, variablen);
	}
	
	/**
	 * Das selbe wie {@link #ausgeben(boolean[][], int)}, aber mit '0' und '1'.
	 * @param tabelle Die Tabelle
	 * @param variablen Die Anzahl der Variablen
	 */
	public static void ausgeben01(boolean[][] tabelle, int variablen)
	{
		Loesung.ausgeben01(tabelle, variablen);
	}
}
