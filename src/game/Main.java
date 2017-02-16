package game;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;

import io.Timer;
import io.Window;

public class Main {

	public Main() {
		Window.setCallbacks();

		if(glfwInit() != true) {
			System.err.println("GLFW Failed to initialize");
			System.exit(1);
		}
		
		Window window = new Window();
		window.setSize(1920, 1080);
		window.createWindow("Game");
		window.setFullscreen(true);
		
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
		
		Manager manager = new Manager(window);
		
		/*
		 * GAME LOOP
		*/
		
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
				
				manager.input(window, frame_cap);
				
				manager.update(window);
				
				window.update();
				if(frame_time >= 1.0){
					frame_time = 0;
					System.out.println("FPS: " + frames);
					frames = 0;
				}
			}
			
			if(can_render){
				manager.render(window);
				frames++;
			}
		}
		
		glfwTerminate();
	}

	public static void main(String[] args) {
		new Main();
	}
}