package render.ui.element;

import render.Model;

public class UIElement {
	
	float[] vertices = new float[]{
			-1f,1f,0, 	//TOP LEFT
			1f,1f,0,	//TOP RIGHT
			1f,-1f,0,	//BOTTOM RIGHT
			-1f,-1f,0,	//BOTTOM LEFT
	};
	
	float[] texture = new float[] {
			0,0,
			1,0,
			1,1,
			0,1,
	};
	
	int[] indices = new int[]{
			0,1,2,
			2,3,0
	};
	
	Model model = new Model(vertices, texture, indices);
	
	int x, y;
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Model getModel() {
		return model;
	}
	
}
