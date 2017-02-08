package game;

import io.Window;
import render.Camera;
import render.Shader;
import render.Texture;
import render.ui.Canvas;
import render.ui.Container;
import render.ui.element.UIButton;
import render.ui.element.UIImage;

public class MainMenu {

	int pressed = 0;
	Window window;
	Canvas canvas = new Canvas(0);
	Container container;
	UIButton playButton;
	UIImage background;
	
	public MainMenu(Window window) {
		this.window = window;
		background = new UIImage("background.png", 0, 0, 5);
		playButton = new UIButton("play.png", 0, -50, 5);
		container = new Container(0, 0);
		container.addElement(background);
		container.addElement(playButton);
		canvas.addContainer(container);
	}
	
	public void update() {
		if(playButton.pressed(window))
			pressed = 1;
	}
	
	public void render(Shader shader, Camera camera) {
		canvas.getContainer(0).render(shader, camera);
	}
	
	public int getPressed() {
		return pressed;
	}
}
