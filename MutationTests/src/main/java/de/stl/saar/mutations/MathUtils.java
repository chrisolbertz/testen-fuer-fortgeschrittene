package de.stl.saar.mutations;

public class MathUtils {
	public static final String NOT_A_NATURAL_NUMBER = "Keine natuerliche Zahl!";
	
    public static long calculateDivisorSum(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException(NOT_A_NATURAL_NUMBER);
        }
        
        long divisorSum = 1;
        
        if (number > 1) {
            divisorSum = divisorSum + number;    
            
	        for(int i = 2; i <= Math.sqrt(number); i++) {
	            if(number % i == 0) {
	                divisorSum = divisorSum + i;
	                if (i < Math.sqrt(number)) {
	                    divisorSum = divisorSum + (number / i);    
	                }
	            }
	        }
        }

        return divisorSum;
    }
    
    public static boolean isEmpty(String bla) {
    	if (bla.trim().isEmpty()) {
    		return true;
    	}
    	return false;
    }
}
