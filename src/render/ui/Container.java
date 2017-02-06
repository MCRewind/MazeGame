package render.ui;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import entity.Entity;
import render.Camera;
import render.Shader;
import render.Texture;
import render.ui.element.UIElement;
import world.Tile;

public class Container {

	int layer;
	int parentLayer;
	
	ArrayList<Tile> tiles = new ArrayList<>();
	ArrayList<Entity> entities = new ArrayList<>();
	ArrayList<UIElement> uiElements = new ArrayList<>();
	
	public Container(int layer, int parentLayer) {
		this.layer = layer;
		this.parentLayer = parentLayer;
	}
	
	public int getParent() {
		return layer;
	}
	
	public void addElement(UIElement element) {
		uiElements.add(element);
	}
	
	public void render(int x, int y, Shader shader, Camera cam){
		shader.bind();
		
		for(int i = 0; i < uiElements.size(); i++) {
			if (uiElements.get(i).getType() == 2) {
				uiElements.get(i).getTexture().bind(0);
			}
		}
		
		Matrix4f pos = new Matrix4f().translate(new Vector3f(x, y, 0));
		Matrix4f target = cam.getUntransformedProjection().mul(pos, new Matrix4f()).scale(new Vector3f(32, 32, 0), new Matrix4f());
		
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", target);
		
		for(int i = 0; i < uiElements.size(); i++) {
			uiElements.get(i).getModel().render();
		}
	}
	
	public void input() {
		
	}
}
