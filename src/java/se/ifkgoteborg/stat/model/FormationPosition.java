package se.ifkgoteborg.stat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="formation_position")
public class FormationPosition {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Integer index;
	
	@ManyToOne
	private Formation formation;
	
	@ManyToOne
	private Position position;
	
	public FormationPosition() {
		
	}

	public FormationPosition(Integer index, Formation formation,
			Position position) {
		super();
		this.index = index;
		this.formation = formation;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return position.getName();
	}
	
	
}
