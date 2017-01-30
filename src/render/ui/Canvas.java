package render.ui;

import java.util.ArrayList;

public class Canvas {

	int layer;
	
	ArrayList panels = new ArrayList<Panel>();
	
	public Canvas(int layer) {
		this.layer = layer;
	}
	
	public void addPanel(Panel panel) {
		panels.add(panel);
	}
	
}
