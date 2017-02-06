package render.ui.element;

import render.Texture;

public class UIButton extends UIElement {
	
	public UIButton(String textName) {
		texture = new Texture("ui/" + textName + ".png");
		type = 2;
	}
	
	public void pressed() {
		
	}
}
