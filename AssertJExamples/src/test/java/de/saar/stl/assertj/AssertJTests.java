package de.saar.stl.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.stl.saar.assertj.CharacterFactory;
import de.stl.saar.assertj.StarFleetCharacter;
import de.stl.saar.assertj.StarTrekNation;

public class AssertJTests {
	/**
	 * Prueft, ob das Alter des Charakters Picard 60 ist. 
	 */
	@Test
	void picards_age_should_be_equal_60() {
		StarFleetCharacter picard = CharacterFactory.createPicard();
		assertThat(picard.getAge()).isEqualTo(60);
	}
	
	/**
	 * Dieser Test schnappt sich eine Liste mit Objekten vom Typ StarFleetCharacter. Dabei werden alle
	 * Elemente fuer den Test herausgefiltert, die im Attribut nation den Wert HUMAN haben. Also der
	 * Test betrachtet nur die Menschen in der Liste. Es wird erwartet, dass dann nur picard und riker
	 * enthalten sind. Den Test kann man zum Fehlschlag bringen, indem man das Objekt data auch
	 * noch an die Methode containsOnly() uebergibt. 
	 */
	@Test
	void should_only_filter_humans() {
		final List<StarFleetCharacter> crewOfEnterpriseD = CharacterFactory.createCrewOfEnterpriseD();
		final StarFleetCharacter picard = CharacterFactory.createPicard();
		final StarFleetCharacter riker = CharacterFactory.createRiker();
		final StarFleetCharacter data = CharacterFactory.createData();
		
		assertThat(crewOfEnterpriseD).filteredOn("nation", StarTrekNation.HUMAN).containsOnly(picard, riker);
	}	
	
	/**
	 * Prueft, ob die Liste mit Charakteren nicht null und nicht leer ist.
	 */
	@Test
	void list_should_not_be_null_nor_empty() {
		final List<StarFleetCharacter> crewOfEnterpriseD = CharacterFactory.createCrewOfEnterpriseD();
		assertThat(crewOfEnterpriseD).isNotNull().isNotEmpty();
	}
	
	/**
	 * Fuer diesen Test gibt es zwei Spock-Objete. Beide haben die gleichen Attributwerte; sie unterscheiden
	 * sich nur im Attribut Rank. Dieses Attribut ist jedoch fuer unseren Test unwichtig. Im Test vergleiche
	 * ich die Objekte spockUnderTest und spock nur in den Attributen name und age. Wer den Test zum 
	 * Scheitern bringen moechte, kann die auskommentierte Zeile einfach mal einkommentieren und den Test
	 * starten.
	 */
	@Test
	void name_and_age_should_be_correct() {
		final StarFleetCharacter spock = CharacterFactory.createSpock();
		final StarFleetCharacter spockUnderTest = CharacterFactory.createSpockAsCaptain();
		
		assertThat(spockUnderTest).isEqualToComparingOnlyGivenFields(spock, "name", "age");
		// assertThat(spockUnderTest).isEqualToComparingOnlyGivenFields(spock, "name", "age", "rank");
	}
	
	/**
	 * Dieser Test wird nur auf dem Attribut name durchgefuehrt. Wir erwarten, dass die Liste die Namen
	 * Picard, Riker und Data enthaelt. Die Namen Spock und Uhura duerfen nicht in der Liste erscheinen.
	 */
	@Test
	void should_extract_picard_riker_data_by_name_and_contains_not_spock_and_uhura() {
		final List<StarFleetCharacter> crewOfEnterpriseD = CharacterFactory.createCrewOfEnterpriseD();
		assertThat(crewOfEnterpriseD).extracting("name").contains("Picard", "Riker", "Data").doesNotContain("Spock", "Uhura");
	}
}
