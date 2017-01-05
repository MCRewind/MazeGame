package game;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.DoubleBuffer;

import org.lwjgl.opengl.GL;

import entity.Player;
import io.Timer;
import io.Window;
import render.Camera;
import render.Shader;
import world.Tile;
import world.TileRenderer;
import world.World;


public class Main {

	public Main() {
		Window.setCallbacks();

		if(glfwInit() != true) {
			System.err.println("GLFW Failed to initialize");
			System.exit(1);
		}
		
		Window window = new Window();
		window.setSize(960, 640);
		window.createWindow("Game");
		window.setFullscreen(true);
		
		
		GL.createCapabilities();
		
		Camera camera = new Camera(window.getWidth(), window.getHeight());
		
		glEnable(GL_TEXTURE_2D);
		
		TileRenderer tiles = new TileRenderer();
		
		Shader shader = new Shader();
		shader.createFragmentShader("fragment");
		shader.createVertexShader("vertex");
		shader.attach();
		World world = new World();
		
		Player player = new Player();
		
		
		//world.setTile(Tile.test2, 4, 4);
		//world.setTile(Tile.test2, 4, 5);
		//world.setTile(Tile.test2, 4, 6);
		//world.setTile(Tile.test2, 4, 7);
		///*
		for(int a = 0; a < 30;a++){
			world.setTile(Tile.test2, a, 0);
		}
		
		for(int a = 0; a < 30;a++){
			world.setTile(Tile.test2, 0, a);
		}
		
		for(int a = 0; a < 30;a++){
			world.setTile(Tile.test2, 30, a);
		}
		
		for(int a = 0; a < 30;a++){
			world.setTile(Tile.test2, a, 30);
		}
		
		for(int i=6; i < 12; i++){
			for(int j=6; j < 12; j++){
				//ds
				world.setTile(Tile.test2, i, j);
			}
		}
		//*/
		double frame_cap = 1.0/60.0;
		
		double frame_time = 0;
		int frames = 0;
		
		double time = Timer.getTime();
		
		double unprocessed = 0;
		while(!window.shouldClose()){
			boolean can_render = false;
			double time_2 = Timer.getTime();
			double passed = time_2 - time;
			unprocessed+=passed;
			frame_time += passed;
			
			time = time_2;
			
			while(unprocessed >= frame_cap){
				can_render=true;
				unprocessed -= frame_cap;
				if(window.getInput().isKeyReleased(GLFW_KEY_ESCAPE)){
					//glfwSetWindowShouldClose(win.getWindow(), true);
					System.out.println("TRUE");
				}
				if(window.getInput().isMouseButtonDown(2)){ //2 should be changed to the right number
					//glfwSetWindowShouldClose(win.getWindow(), true);
					//double x;
					//double y;
					 DoubleBuffer xPos = null, yPos = null;
					 glfwGetCursorPos(window.getWindow(),xPos, yPos);
					//double x,y = glfwGetCursorPos(window);
					System.out.println("TRUE");
				}
				
				player.update((float)frame_cap, window, camera, world);
				
				world.correctCamera(camera, window);
				
				window.update();
				if(frame_time >= 1.0){
					frame_time = 0;
					System.out.println("FPS: " + frames);
					frames = 0;
				}
			}
			
			if(can_render){
				glClear(GL_COLOR_BUFFER_BIT);
				
				
				//shader.bind();
				//shader.setUniform("sampler", 0);
				//shader.setUniform("projection", camera.getProjection().mul(target));
				//tex.bind(0);
				//model.render();
				
				world.render(tiles, shader, camera,window);
				
				player.render(shader, camera);
				

				window.swapBuffers();
				frames++;
			}
		}
		
		glfwTerminate();
	}

	public static void main(String[] args) {
		
		new Main();
	}
}