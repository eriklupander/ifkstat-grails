package se.ifkgoteborg.stat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="tournament", uniqueConstraints={@UniqueConstraint(columnNames="name")})
public class Tournament {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private boolean yearOverlapping = false;
	
	public Tournament() {
		
	}
	
	public Tournament(String name, boolean yearOverlapping) {
		this.name = name;
		this.yearOverlapping = yearOverlapping;
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

	public boolean isYearOverlapping() {
		return yearOverlapping;
	}

	public void setYearOverlapping(boolean yearOverlapping) {
		this.yearOverlapping = yearOverlapping;
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
		Tournament other = (Tournament) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	
}
