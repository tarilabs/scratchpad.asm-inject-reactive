package my;

import adding.MarkerInterface;

public class TargetPojo implements MarkerInterface {
	private String name;

	public TargetPojo(String name) {
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
