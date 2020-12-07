package de.stl.saar.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HamcrestTests {
	@Test
	public void testEqualTo() {
		int ergebnis = 4 * 4;
	}
	
	@Test
	public void testIsEqualTo() {
		int ergebnis = 4 * 4;
	}
	
	@Test
	public void testIsNotEqualTo() {
		int ergebnis = 4 * 4;
	}
	
	@Test
	public void testNotNull() {
		String myString = null;
	}
	
	@Test
	public void testHasItems() {
		List<String> myStrings = new ArrayList<>();
		myStrings.add("A");
		myStrings.add("B");
		myStrings.add("C");
		
	}
}
