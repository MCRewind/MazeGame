package render.ui;

import java.util.ArrayList;

public class Canvas {

	int layer;
	
	ArrayList<Container> containers = new ArrayList<>();
	
	public Canvas(int layer) {
		this.layer = layer;
	}
	
	public void addContainer(Container container) {
		containers.add(container);
	}
	
	public Container getContainer(int id) {
		return containers.get(id);
	}
	
}
