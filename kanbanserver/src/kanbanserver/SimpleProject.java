package kanbanserver;

import java.io.Serializable;

public class SimpleProject implements Serializable{
	private static final long serialVersionUID = -2398503995120490647L;
	String name;
	String id;

	public SimpleProject(String name, String id) {
		this.name = name;
		this.id = id;
	}
}
