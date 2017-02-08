package render.ui.element;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import collision.AABB;
import io.Window;
public class UIButton extends UIElement {
	
	AABB bounds;
	
	public UIButton(String textName, float x, float y, float scale) {
		super(textName, x, y, scale);
		bounds = new AABB(new Vector2f(x, y), new Vector2f(width / 2, height / 2));
	}
	
	public boolean pressed(Window window) {
		DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window.getWindow(), b1, b2);
		return bounds.getPointCollision((float)b1.get(0), (float)b2.get(0)) && window.getInput().isMouseButtonDown(GLFW_MOUSE_BUTTON_1);
	}
}
