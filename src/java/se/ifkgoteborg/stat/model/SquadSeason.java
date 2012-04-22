package se.ifkgoteborg.stat.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import se.ifkgoteborg.stat.util.DateFactory;

@Entity
@Table(name="squad_season")
public class SquadSeason {

	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(value=TemporalType.DATE)
	private Date startYear;
	
	@Temporal(value=TemporalType.DATE)
	private Date endYear;
	
	private String name;
	
	@OneToMany(mappedBy="season")
	private List<PlayedForClub> squad = new ArrayList<PlayedForClub>();
	
	@OneToMany(mappedBy="season")
	private List<TournamentSeason> tournamentSeasons = new ArrayList<TournamentSeason>();
	
	public SquadSeason() {}
	
	public SquadSeason(String name, int startYearInt, int endYearInt) {
		if(startYearInt < 1900 || endYearInt < 1900) {
			throw new IllegalArgumentException("Cannot create Season instance, supplied year was < 1900. Season name: " + name + ". Start year: " + startYear + " endYear: " + endYear);
		}
		System.out.println("creating new season based on name: " + name);
		this.name = name;
		
		setStartYear(DateFactory.get(startYearInt, 0, 1));
		setEndYear(DateFactory.get(endYearInt, 11, 31));

	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Date getStartYear() {
		return startYear;
	}

	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}

	public Date getEndYear() {
		return endYear;
	}

	public void setEndYear(Date endYear) {
		this.endYear = endYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PlayedForClub> getSquad() {
		return squad;
	}

	public void setSquad(List<PlayedForClub> squad) {
		this.squad = squad;
	}
	
	public List<TournamentSeason> getTournamentSeasons() {
		return tournamentSeasons;
	}

	public void setTournamentSeasons(List<TournamentSeason> tournamentSeasons) {
		this.tournamentSeasons = tournamentSeasons;
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
		SquadSeason other = (SquadSeason) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return this.name;
	}
}
