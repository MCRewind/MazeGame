package render.ui;

import java.util.ArrayList;

import entity.Entity;
import world.Tile;

public class Panel {

	int layer;
	int parentLayer;
	
	ArrayList tiles = new ArrayList<Tile>();
	ArrayList entities = new ArrayList<Entity>();
	
	public Panel(int layer, int parentLayer) {
		this.layer = layer;
		this.parentLayer = parentLayer;
	}
	
	public int getParent() {
		return layer;
	}
	
}
