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
	
	public void render(Shader shader, Camera cam){
		shader.bind();
		for (int i = 0; i < uiElements.size(); i++) {
			Matrix4f pos = new Matrix4f().translate(uiElements.get(i).getPosition());
			Matrix4f target = cam.getUntransformedProjection().mul(pos, new Matrix4f());
			shader.setUniform("projection", target);
			shader.setUniform("sampler", 0);
			uiElements.get(i).getTexture().bind(0);
			uiElements.get(i).getModel().render();
		}
	}
}
