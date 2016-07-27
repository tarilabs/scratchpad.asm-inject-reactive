package my;

import adding.MarkerInterface;

public class TargetMyPojo implements MarkerInterface {
	private String name;

	public TargetMyPojo(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
