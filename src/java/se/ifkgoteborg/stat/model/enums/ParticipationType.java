package se.ifkgoteborg.stat.model.enums;

public enum ParticipationType {
	STARTER(""), SUBSTITUTE_IN("inbytt"), SUBSTITUTE_OUT("utbytt");

	private String name;
	
	private ParticipationType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
