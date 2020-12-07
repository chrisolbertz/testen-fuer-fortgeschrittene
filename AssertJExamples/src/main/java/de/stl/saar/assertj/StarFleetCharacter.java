package de.stl.saar.assertj;

public class StarFleetCharacter {
	private final String name;
	private final StarFleetRank rank;
	private final int age;
	private final StarTrekNation nation;
	
	public StarFleetCharacter(String name, int age, StarTrekNation nation, StarFleetRank rank) {
		super();
		this.name = name;
		this.age = age;
		this.nation = nation;
		this.rank = rank;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public StarTrekNation getNation() {
		return nation;
	}
	
	public StarFleetRank getRank() {
		return rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nation == null) ? 0 : nation.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StarFleetCharacter other = (StarFleetCharacter) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nation != other.nation)
			return false;
		if (rank != other.rank)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return rank + " " + name;
	}
}
