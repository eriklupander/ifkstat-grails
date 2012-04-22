package se.ifkgoteborg.stat.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="formation")
public class Formation {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;

	@OneToMany(mappedBy="formation")
	private List<FormationPosition> formationPositions = new ArrayList<FormationPosition>();
	
	public Formation() {}

	public Formation(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public List<FormationPosition> getFormationPositions() {
		return formationPositions;
	}

	public void setFormationPositions(List<FormationPosition> formationPositions) {
		this.formationPositions = formationPositions;
	}

	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Formation other = (Formation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

//	public List<Game> getUsedInGames() {
//		return usedInGames;
//	}
//
//	public void setUsedInGames(List<Game> usedInGames) {
//		this.usedInGames = usedInGames;
//	}
	
	
}
