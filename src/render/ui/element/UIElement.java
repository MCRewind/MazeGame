package render.ui.element;

import org.joml.Vector3f;

import render.Model;
import render.Texture;

public class UIElement {
	
	Model model;
	
	public Texture texture;
	
	//position in screen space not world/tile coords
	
	float x, y, width, height;
	
	/*
	 * 0 = shape
	 * 1 = text
	 * 2 = texture
	 */
	
	int type;
	
	public UIElement(String textName, float x, float y, float scale) {
		texture = new Texture(textName);
		width = texture.getWidth() * scale;
		height = texture.getHeight() * scale;
		float[] vertices = new float[]{
				0,     -height, 0, //TOP LEFT
				width, -height, 0, //TOP RIGHT
				width,  0,      0, //BOTTOM RIGHT
				0,      0,      0, //BOTTOM LEFT
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
		this.x = x;
		this.y = y;
		model = new Model(vertices, textureCoords, indices);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector3f getPosition() {
		return new Vector3f(x, y, 0.5f);
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
