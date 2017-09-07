package kanbanserver;

import java.io.Serializable;

public class SimpleProject implements Serializable{
	private static final long serialVersionUID = -2398503995120490647L;
	private String name;
	private String id;

	public SimpleProject(String name, String id) {
		this.setName(name);
		this.setId(id);
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}
}
