package de.stl.saar.testing.mutations;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import de.stl.saar.testing.mutations.MathUtils;

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
		@CsvSource({
	        "1, 1",
	        "2, 3",
	        "3, 4",
	        "6, 12",
	        "12, 28",
	        "16, 31",
	        "200000000, 499511463"
		})
	    @DisplayName("When a timeout of 5 milliseconds is required")
		void testBerechneTeilersumme_timeout5Milliseconds(long zahl, long erwarteteTeilersumme) {
			assertTimeout(ofMillis(5), () -> {
				final long teilersumme = MathUtils.calculateDivisorSum(zahl);
				assertEquals(erwarteteTeilersumme, teilersumme);
		    });
		}
	
		
		@ParameterizedTest
		@ValueSource(
		        ints = {0, -1, -2, -3}
			)
		@DisplayName("Throws exception when the divisor sum is calculated with negative numbers")
		void testBerechneTeilersumme_shouldThrowExceptions(long zahl) {
			Assertions.assertThrows(RuntimeException.class, () -> {
				MathUtils.calculateDivisorSum(zahl);
			});
		}
	}
	
	@Nested
	@DisplayName("The calculation of the isbn checksum is tested")
	class WhenCalculationOfIsbnCheckSumIsTested {
		@DisplayName("Calculation of checksum is tested with correct values")
		@ParameterizedTest
		@CsvSource({
			"389864253, 4",
			"344640800, 2",
			"123456789, X"
		})
		public void testcalculateCheckSumOfIsbn_withCorrectValues(long isbn, 
				String erwartetePruefziffer) {
			final String actualCheckSum = MathUtils.calculateCheckSumOfIsbn(isbn);
			assertEquals(erwartetePruefziffer, actualCheckSum);
		}
		
		@ParameterizedTest
		@ValueSource(
		        longs = {12345, 1234567890, -1234, 12345678}
			)
		@DisplayName("Throws exception when the isbn is invalid")
		void testBerechneIsbnPruefsumme_shouldThrowExceptions(long isbn) {
			Assertions.assertThrows(IllegalArgumentException.class, () -> {
				MathUtils.calculateCheckSumOfIsbn(isbn);
			});
		}
	}
	
	@ParameterizedTest
	@MethodSource("createWords")
	void withMethodSource(String word) { }
	
	
	private static Stream<String> createWords() {
		return Stream.of("Hello", "Junit");
	}
	
	@Test
	void isFoobar() {
	  assertTrue(MathUtils.isFooBar());
	}
}
