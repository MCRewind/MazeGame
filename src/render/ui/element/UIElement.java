package render.ui.element;

import render.Model;
import render.Texture;

public class UIElement {
	
	float[] vertices = new float[]{
			-1f,1f,0, 	//TOP LEFT
			1f,1f,0,	//TOP RIGHT
			1f,-1f,0,	//BOTTOM RIGHT
			-1f,-1f,0,	//BOTTOM LEFT
	};
	
	float[] textureCoords = new float[] {
			0,0,
			1,0,
			1,1,
			0,1,
	};
	
	int[] indices = new int[]{
			0,1,2,
			2,3,0
	};
	
	Model model = new Model(vertices, textureCoords, indices);
	
	public Texture texture;
	
	//position in screen space not world/tile coords
	
	int x, y;
	
	/*
	 * 0 = shape
	 * 1 = text
	 * 2 = texture
	 */
	
	int type;
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Model getModel() {
		return model;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public int getType() {
		return type;
	}
	
}
