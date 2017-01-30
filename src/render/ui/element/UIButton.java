package render.ui.element;

import render.Texture;

public class UIButton extends UIElement {

	public Texture texture;
	
	public UIButton(String textName) {
		texture = new Texture(textName);
	}
	
	public void pressed() {
		
	}
}
