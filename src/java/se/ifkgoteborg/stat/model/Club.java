package se.ifkgoteborg.stat.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="club", uniqueConstraints=@UniqueConstraint(columnNames="name"))
public class Club {
	
	@Id
	@GeneratedValue
	private Long id;

	
	private String name;
	private String city;
	
	private Boolean defaultClub = false;
	
	@ManyToOne
	private Country country;
	
	@Temporal(value=TemporalType.DATE)
	private Date foundedDate;
	
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getFoundedDate() {
		return foundedDate;
	}
	public void setFoundedDate(Date foundedDate) {
		this.foundedDate = foundedDate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Boolean getDefaultClub() {
		return defaultClub;
	}

	public void setDefaultClub(Boolean defaultClub) {
		this.defaultClub = defaultClub;
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
		Club other = (Club) obj;
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
