package game;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import collision.AABB;
import entity.Player;
import io.Timer;
import io.Window;
import render.Camera;
import render.Model;
import render.Shader;
import render.Texture;
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
		
//		float[] vertices = new float[]{
//				-0.5f,0.5f,0, 	//TOP LEFT
//				0.5F,0.5f,0,	//TOP RIGHT
//				0.5f,-0.5f,0,	//BOTTOM RIGHT
//				-0.5F,-0.5f,0,	//BOTTOM LEFT
//		};
//		
//		float[] texture = new float[] {
//				0,0,
//				1,0,
//				1,1,
//				0,1,
//		};
//		
//		int[] indices = new int[]{
//				0,1,2,
//				2,3,0
//		};
//		
//		Model model = new Model(vertices, texture,indices);
		Shader shader = new Shader();
		shader.createFragmentShader("fragment");
		shader.createVertexShader("vertex");
		//Texture tex = new Texture("./res/test.png");

		//Matrix4f projection = new Matrix4f()
				//.ortho2D(-640/2,640/2, -480/2, 480/2);
		World world = new World();
		
		Player player = new Player();
		
		for(int i=6; i < 12; i++){
			for(int j=6; j < 12; j++){
				
				world.setTile(Tile.test2, i, j);
			}
		}
		
		//camera.setPosition(new Vector3f(-200,0,0));
		
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