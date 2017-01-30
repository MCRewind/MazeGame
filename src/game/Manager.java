package game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;

import entity.Player;
import io.Window;
import render.Camera;
import render.Shader;
import world.Tile;
import world.TileRenderer;
import world.World;

public class Manager {

	public void input(Window window, World world, Camera camera) {
		if(window.getInput().isKeyReleased(GLFW_KEY_ESCAPE)){
			//glfwSetWindowShouldClose(win.getWindow(), true);
			System.out.println("TRUE");
		}
		if(window.getInput().isMouseButtonDown(GLFW_MOUSE_BUTTON_1)){ //2 should be changed to the right number
			//glfwSetWindowShouldClose(win.getWindow(), true);
			//double x;
			//double y;
			DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
			DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
			glfwGetCursorPos(window.getWindow(), b1, b2);
			//Tile thisTile = world.getTile((int)b1.get(0), (int)b2.get(0));
			
			world.setGlobalTile(Tile.test2, (int)b1.get(0), (int)b2.get(0), window, camera);
			
			System.out.println("x : " + b1.get(0) + ", y = " + b2.get(0));
		}
	}

	public void render(Window window, World world, Player player, Camera camera, TileRenderer tiles, Shader shader) {
		glClear(GL_COLOR_BUFFER_BIT);
		
		
		//shader.bind();
		//shader.setUniform("sampler", 0);
		//shader.setUniform("projection", camera.getProjection().mul(target));
		//tex.bind(0);
		//model.render();
		
		world.render(tiles, shader, camera,window);
		
		player.render(shader, camera);
		

		window.swapBuffers();
	}
	
}
