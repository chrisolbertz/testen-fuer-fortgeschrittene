package de.stl.saar.testing.mutations;

/**
 * Diese Klasse implementiert einige mathematische Funktionen:
 * 
 * @author Christopher
 *
 */
public class MathUtils {
    // ********************************************** Konstanten ***********************************************
    public static final String NOT_A_NATURAL_NUMBER = "Keine natuerliche Zahl!";
    
    private static final String ISBN_NOT_VALID = "Die ISBN ist nicht korrekt";
    
    private static final long ISBN_MIN = 100000000L;
    private static final long ISBN_MAX = 999999999L;
    /**
     * Privater Konstruktor, damit keine Objekte erzeugt werden koennen.
     */
    private MathUtils() { }

    /**
     * Einfach ein Beispiel, mit dem ein aequivalenter Mutant demonstriert
     * werden soll. Das Beispiel ist sinnlos. 
     * @return True
     */
    public static boolean isFooBar() {
    	  boolean foobar = false;
    	  int i = 2;
    	  if (i > 1) {
    	    foobar = true;
    	  }
    	 
    	  return foobar;
    }
    
    /**
     * Berechnet die Teilersumme einer Zahl. Die Teilersumme kann nur von einer natuerlichen Zahl berechnet werden. Die Berechnung laeuft bis
     * zur Wurzel der Zahl. Wenn ein Teiler gefunden wird, der kleiner als die Wurzel ist, wird auch gleichzeitig ein Teiler gefunden, der 
     * groesser ist als die Wurzel. Nur wenn der Teiler genau die Wurzel ist, wird der Teiler einmal hinzuaddiert.
     * Beispiel: Ein Teiler von 36 ist 3: 3 * 12 = 36. Somit ist auch 12 ein Teiler von 36.
     * Die Wurzel von 36 ist 6: 6 * 6 = 36 
     * Somit darf die 6 nur einmal zur Teilersumme addiert werden.
     * 
     * Die Schleife berechnet bei jedem Teiler den entsprechenden Teiler ueber der Wurzel und addiert diesen zur Teilersummer hinzu.
     * Beispiel fuer die Berechnung: Die Teilersumme von 36 ist = 91.
     * Die Berechnung der Teilersumme von 36 wuerde dann folgendermassen ablaufen:
     * Initialisierung: Teilersumme = 36 (eingelesene Zahl) + 1 (immer Teiler).
     * 1. Durchlauf (i = 2): teilersumme = teilersumme + 2 + 18
     * 2. Durchlauf (i = 3): teilersumme = teilersumme + 3 + 12
     * 3. Durchlauf (i = 4): teilersumme = teilersumme + 4 + 9
     * 4. Durchlauf (i = 5): 5 ist kein Teiler.
     * 5. Durchlauf (i = 6): teilersumme = teilersumme + 6
     * 
     * @param number Die Zahl, deren Teilersumme berechnet werden soll. 
     * @return Die Teilersumme.
     * @throws IllegalArgumentException wenn die Zahl kleiner 
     * oder gleich 0 ist.
     */
    public static long calculateDivisorSum(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException(NOT_A_NATURAL_NUMBER);
        }
        
        long divisorSum = 1;
        
        if (number > 1) {
            divisorSum = divisorSum + number;    
            
            if (isPrimeNumber(number) == false) {
                for(int i = 2; i <= Math.sqrt(number); i++) {
                    if(number % i == 0) {
                        divisorSum = divisorSum + i;
                        if (i < Math.sqrt(number)) {
                            divisorSum = divisorSum + (number / i);    
                        }
                    }
                }
            }
        }

        return divisorSum;
    }
    
    /**
     * Prueft, ob eine Zahl eine Primzahl ist. Der Algorithmus funktioniert folgendermassen:
     * Die Berechnung laeuft bis zur Wurzel der Zahl in 2er-Schritten, da nur ungeraden Zahlen geprueft werden muessen. Ob es sich um ungerade
     * Zahlen handelt, wird bereits vor der Schleife durch modulo 2 geprueft. Die Schleife muss aus folgendem Grund nur bis zur Wurzel laufen:
     * Wenn die Teiler vor der Wurzel schon geprueft werden, kann es nach der Wurzel keine Teiler mehr geben, weil ein Teiler vor der Wurzel mal ein
     * Teiler nach der Wurzel die Zahl ergibt -> teilerVorWurzel * teilerNachWurzel = zahl
     * Beispiel: 3 * 7 = 21
     * 
     * @param number Die zu pruefende Zahl.
     * @return True, wenn es sich um eine Primzahl handelt, sonst false.
     */
    public static boolean isPrimeNumber(long number) {
        if (number <= 0) {
            throw new RuntimeException(NOT_A_NATURAL_NUMBER);
        }
        
        if(number == 1) {
            return false;
        }
        else if(number == 2) {
            return true;
        }        
        else if(number % 2 == 0) {
            return false;
        }
        else {
            for(int i = 3; i <= Math.round(Math.sqrt(number)); i = i + 2) {
                if(number % i == 0) {
                    return false;
                }
            }
        }
        
        return true;  
    }
    
    public static String calculateCheckSumOfIsbn(long isbn) { 	
        if((isbn < ISBN_MIN) || (isbn > ISBN_MAX)) {
           throw new IllegalArgumentException(ISBN_NOT_VALID);
        }
    
        long summe = 0L;
        int i = 9;
    
        while(isbn > 0) {
           summe += (isbn % 10) * i;
           isbn /= 10;
           i--;
        }
    
        if (summe % 11 == 10) {
           return "X";
        }
        else {
           return "" + summe % 11;
        }
    }
}
