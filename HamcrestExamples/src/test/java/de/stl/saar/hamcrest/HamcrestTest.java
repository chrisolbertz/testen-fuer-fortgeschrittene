package de.stl.saar.hamcrest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HamcrestTest {
	/**
	 * Der allOf()-Matcher erwartet, dass alle Bedingungen erfuellt sind. Im Beispiel
	 * darf das Objekt also nicht null sein, muss ein Integer sein und den Text 5 enthalten.
	 * Sobald eine der Bedingungen nicht wahr ist, ist schlaegt der Test fehl.
	 */
	@Test
	public void testAllOf() {
		Integer value = 5;
		assertThat(
				value,
				is(allOf(notNullValue(), 
						 instanceOf(Integer.class),
						 equalTo(5))));
	}
	
	/**
	 * Zeigt die Verwendung des Matchers instanceOf.
	 */
	@Test
	public void testInstanceOf1() {
		int eineZahl = 4;
		assertThat(eineZahl, is(instanceOf(Integer.class)));
	}

	/**
	 * Zeigt die Verwendung des Matchers instanceOf. Dabei werden auch
	 * Vererbungsbeziehungen aufgeloest.
	 */
	@Test
	public void testInstanceOf2() {
		Long meinObjekt = 4L;
		assertThat(meinObjekt, is(instanceOf(Number.class)));
	}
	
	/**
	 * Funktioniert, wenn eine der Bedingungen wahr ist. Im Beispiel muss der zu
	 * ueberpruefende Wert also ein Integer sein oder den Text Hallo enthalten. 
	 */
	@Test
	public void testAnyOf() {
		assertThat(
				"Hallo",
				is(anyOf(instanceOf(Integer.class),
						 equalTo("Hallo"))));
	}
	
	/**
	 * Hier wird auf jeden Fall ein Fehler ausgeloest. Aber der Test soll zeigen, dass man
	 * mit describedAs() eine eigene Fehlermeldung angeben kann.
	 */
	@Test
	@DisplayName("Soll einen Fehler ausloesen, um describedAs zu demonstrieren.")
	public void testDescribedAs() {
		int wert = 7;
		assertThat(wert, describedAs(""
				+ "Dieser Test soll fehlschlagen und eine eigene Fehlermeldung anzeigen.", equalTo(8)));
	}
	
	/**
	 * Ein einfacher Test, ob ergebnis den Wert 16 enthaelt.
	 */
	@Test 
	public void testEqualTo() {
		int ergebnis = 4 * 4;
		assertThat(ergebnis, equalTo(16));
	}
	
	/**
	 * Statt equalTo() kann auch der Matcher is() verwendet werden. Das mache ich allerdings
	 * ungerne.
	 */
	@Test
	public void testIsShortcutForEqualTo() {
		int value = 5;
		assertThat(value, is(5));
	}

	/**
	 * Ich bevorzuge diese Moeglichkeit: die Matcher is() und equalTo() kombiniert ergeben einen
	 * wundervoll lesbaren englischen Satz.
	 */
	@Test
	public void testIsWithEqualTo() {
		int value = 5;
		assertThat(value, is(equalTo(5)));
	}
	
	/**
	 * Demonstriert den Matcher not().
	 */
	@Test
	public void testNot() {
		int value = 8;
		assertThat(value, not(6));
	}

	/**
	 * Verbindet die Matcher not() und instanceOf(). Es wird also geprueft, ob wir
	 * es nicht mit einem String zu tun haben.
	 */
	@Test
	public void testNotAndInstanceOf() {
		int value = 8;
		assertThat(value, not(instanceOf(String.class)));
	}

	/**
	 * Hier wird praktisch ein not equal geprueft, weil der Matcher is() gleichbedeutend
	 * mit equalTo() eingesetzt wird. 
	 */
	@Test
	public void testNotWithIs() {
		int value = 8;
		assertThat(value, is(not(6)));
	}

	/**
	 * Auch hier erhalten wir dank is() einen wunderbaren lesbaren Satz.
	 */
	@Test
	public void testNotAndInstanceOfWithIs() {
		int value = 8;
		assertThat(value, is(not(instanceOf(String.class))));
	}

	/**
	 * Auch hier erhalten wir dank is() einen wunderbaren lesbaren Satz.
	 */
	@Test
	public void testNotWithIsAndEqual() {
		int value = 8;
		assertThat(value, is(not(equalTo(6))));
	}
	
	/**
	 * Ueberpruefug auf nicht null.
	 */
	@Test
	public void testNotNull1() {
		String test = "Ich bin nicht null";
		assertThat(test, notNullValue());
	}

	/**
	 * Ueberpruefug auf nicht null in Kombination mit is(). Semantisch
	 * identisch mit der oberen Methode, aber die Lesbarkeit ist hoeher. 
	 */
	@Test
	public void testNotNull2() {
		String test = "Ich bin nicht null";
		assertThat(test, is(notNullValue()));
	}
	
	/**
	 * Prueft, ob die Instanzen gleich sind. Hier wird also ein Referenzvergleich durchgefuehrt.
	 */
	@Test
	public void testSameInstance() {
		String string1 = "Ich bin ein String";
		String string2 = string1;
		assertThat(string1, sameInstance(string2));
	}

	/**
	 * Prueft, ob die Instanzen gleich sind. Hier wird also ein Referenzvergleich durchgefuehrt.
	 * Semantisch identisch mit der oberen Methode, aber die Lesbarkeit ist durch die Verwendung
	 * von is() hoeher.
	 */
	@Test
	public void testSameInstanceWithIs() {
		String string1 = "Ich bin ein String";
		String string2 = string1;
		assertThat(string1, is(sameInstance(string2)));
	}
	
	/**
	 * Prueft, ob das Element B in der Liste enthalten ist. 
	 */
	@Test
	public void testHasItem() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("A");
		stringList.add("B");
		stringList.add("C");
		
		assertThat(stringList, hasItem("B"));
	}

	/**
	 * Prueft, ob die Elemente A und B in der Liste enthalten sind. 
	 */
	@Test
	public void testHasItems() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("A");
		stringList.add("B");
		stringList.add("C");
		
		assertThat(stringList, hasItems("A","B"));
	}
}