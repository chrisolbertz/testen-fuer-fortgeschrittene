package de.stl.saar.assertj;

import java.util.ArrayList;
import java.util.List;

public class CharacterFactory {
	public static StarFleetCharacter createPicard() {
		return new StarFleetCharacter("Picard", 60, StarTrekNation.HUMAN, StarFleetRank.CAPTAIN);
	}
	
	public static StarFleetCharacter createRiker() {
		return new StarFleetCharacter("Riker", 40, StarTrekNation.HUMAN, StarFleetRank.COMMANDER);
	}
	
	public static StarFleetCharacter createData() {
		return new StarFleetCharacter("Data", 35, StarTrekNation.ANDROID, StarFleetRank.LTCOMMANDER);
	}
	
	public static StarFleetCharacter createSpock() {
		return new StarFleetCharacter("Spock", 52, StarTrekNation.VULCANIAN, StarFleetRank.COMMANDER);
	}
	
	public static StarFleetCharacter createSpockAsCaptain() {
		return new StarFleetCharacter("Spock", 52, StarTrekNation.VULCANIAN, StarFleetRank.CAPTAIN);
	}
	
	public static StarFleetCharacter createWorf() {
		return new StarFleetCharacter("Worf", 45, StarTrekNation.KLINGON, StarFleetRank.LTCOMMANDER);
	}
	
	public static StarFleetCharacter createUhuru() {
		return new StarFleetCharacter("Uhura", 40, StarTrekNation.HUMAN, StarFleetRank.LIEUTENANT);
	}
	
	public static  List<StarFleetCharacter> createCrewOfEnterpriseD() {
		final List<StarFleetCharacter> crewOfEnterpriseD = new ArrayList<StarFleetCharacter>();
		crewOfEnterpriseD.add(createPicard());
		crewOfEnterpriseD.add(createRiker());
		crewOfEnterpriseD.add(createWorf());
		crewOfEnterpriseD.add(createData());
		
		return crewOfEnterpriseD;
	}
	
	public static  List<StarFleetCharacter> createCrewOfVoyager() {
		return new ArrayList<>();
	}
}
