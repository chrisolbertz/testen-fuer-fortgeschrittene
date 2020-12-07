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
	@Test
	public void testAllOf() {
		assertThat(
				"Hallo",
				is(allOf(notNullValue(), 
						 instanceOf(String.class),
						 equalTo("Hallo"))));
	}
	
	@Test
	public void testInstanceOf1() {
		int eineZahl = 4;
		assertThat(eineZahl, is(instanceOf(Integer.class)));
	}

	@Test
	public void testInstanceOf2() {
		Long meinObjekt = 4L;
		assertThat(meinObjekt, is(instanceOf(Number.class)));
	}
	
	@Test
	public void testAnyOf() {
		assertThat(
				"Hallo",
				is(anyOf(notNullValue(), 
						 instanceOf(Integer.class),
						 equalTo("Hallo"))));
	}
	
	@Test
	@DisplayName("Soll einen Fehler ausloesen, um describedAs zu demonstrieren.")
	public void testDescribedAs() {
		int wert = 7;
		assertThat(wert, describedAs(""
				+ "Hier wird meine eigene Fehlermeldung angezeigt!", equalTo(8)));
	}
	
	@Test 
	public void testEqualTo() {
		int ergebnis = 4 * 4;
		assertThat(ergebnis, equalTo(16));
	}
	
	@Test
	public void testIsShortcutForEqualTo() {
		int value = 5;
		assertThat(value, is(5));
	}

	@Test
	public void testIsWithEqualTo() {
		int value = 5;
		assertThat(value, is(equalTo(5)));
	}
	
	@Test
	public void testNot() {
		int value = 8;
		assertThat(value, not(6));
	}

	@Test
	public void testNotAndInstanceOf() {
		int value = 8;
		assertThat(value, not(instanceOf(String.class)));
	}

	@Test
	public void testNotWithIs() {
		int value = 8;
		assertThat(value, is(not(6)));
	}

	@Test
	public void testNotAndInstanceOfWithIs() {
		int value = 8;
		assertThat(value, is(not(instanceOf(String.class))));
	}

	@Test
	public void testNotWithIsAndEqual() {
		int value = 8;
		assertThat(value, is(not(equalTo(6))));
	}
	
	@Test
	public void testNotNull1() {
		String test = "Ich bin nicht null";
		assertThat(test, notNullValue());
	}

	@Test
	public void testNotNull2() {
		String test = "Ich bin nicht null";
		assertThat(test, is(notNullValue()));
	}
	
	@Test
	public void testSameInstance() {
		String string1 = "Ich bin ein String";
		String string2 = string1;
		assertThat(string1, sameInstance(string2));
	}

	@Test
	public void testSameInstanceWithIs() {
		String string1 = "Ich bin ein String";
		String string2 = string1;
		assertThat(string1, is(sameInstance(string2)));
	}
	
	@Test
	public void testHasItem() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("A");
		stringList.add("B");
		stringList.add("C");
		
		assertThat(stringList, hasItem("B"));
	}

	@Test
	public void testHasItems() {
		List<String> stringList = new ArrayList<String>();
		stringList.add("A");
		stringList.add("B");
		stringList.add("C");
		
		assertThat(stringList, hasItems("A","B"));
	}
}