package de.saar.stl.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.stl.saar.assertj.CharacterFactory;
import de.stl.saar.assertj.StarFleetCharacter;
import de.stl.saar.assertj.StarTrekNation;

public class AssertJTests {
	@Test
	void picards_age_should_be_equal_60() {
		StarFleetCharacter picard = CharacterFactory.createPicard();
		assertThat(picard.getAge()).isEqualTo(60);
	}
	
	@Test
	void should_only_filter_humans() {
		final List<StarFleetCharacter> crewOfEnterpriseD = CharacterFactory.createCrewOfEnterpriseD();
		final StarFleetCharacter picard = CharacterFactory.createPicard();
		final StarFleetCharacter riker = CharacterFactory.createRiker();
		final StarFleetCharacter data = CharacterFactory.createData();
		
		assertThat(crewOfEnterpriseD).filteredOn("nation", StarTrekNation.HUMAN).containsOnly(picard, riker);
	}	
	
	@Test
	void list_should_not_be_null_nor_empty() {
		final List<StarFleetCharacter> crewOfEnterpriseD = CharacterFactory.createCrewOfEnterpriseD();
		assertThat(crewOfEnterpriseD).isNotNull().isNotEmpty();
	}
	
	@Test
	void name_and_age_should_be_correct() {
		final StarFleetCharacter spock = CharacterFactory.createSpock();
		final StarFleetCharacter spockUnderTest = CharacterFactory.createSpockAsCaptain();
		assertThat(spockUnderTest).isEqualToComparingOnlyGivenFields(
				spock, "name", "age");
		
	}
	
	@Test
	void should_extract_picard_riker_data_by_name_and_contains_not_spock_and_uhura() {
		final List<StarFleetCharacter> crewOfEnterpriseD = CharacterFactory.createCrewOfEnterpriseD();
		assertThat(crewOfEnterpriseD).extracting("name").contains("Picard", "Riker", "Data")
			.doesNotContain("Spock", "Uhura");
	}
}
