package de.stl.saar.mutations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Tests the class MathUtils")
public class MathUtilsTest {
	@Nested
	@DisplayName("When the calculation of the divisor sum is tested")
	public class WhenDivisorSumIsTested {
		@DisplayName("Calculation of the divisor sum is tested with correct values")
		@ParameterizedTest
		@CsvSource({
	        "1, 1",
	        "2, 3",
	        "3, 4",
	        "6, 12",
	        "12, 28",
	        "16, 31",
	        "200000000, 499511463"
		})
		void testBerechneTeilersumme(long zahl, long erwarteteTeilersumme) {
			final long teilersumme = MathUtils.calculateDivisorSum(zahl);
			assertEquals(erwarteteTeilersumme, teilersumme);
		}
		
		@ParameterizedTest
		@ValueSource(
		        ints = { 0, 
		        		-1, 
		        		-2, 
		        		-3}
		)
		@DisplayName("Throws exception when the divisor sum is calculated with negative numbers")
		void testBerechneTeilersumme_shouldThrowExceptions(long zahl) {
			Assertions.assertThrows(RuntimeException.class, () -> {
				MathUtils.calculateDivisorSum(zahl);
			});
		}
	}
	
	@Test
	public void test() {
		assertTrue(MathUtils.isEmpty("    "));
	}
}