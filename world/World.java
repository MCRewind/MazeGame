package world;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import collision.AABB;
import io.Window;
import render.Camera;
import render.Shader;

public class World {
	private final int view = 20;
	private byte[] tiles;
	private AABB[] bounding_boxes;
	private int width;
	private int height;
	private int scale;
	
	private Matrix4f world;
	
	public World() {
		width = 64;
		height = 64;
		scale = 32;
		
		tiles = new byte[width*height];
		bounding_boxes = new AABB[width*height];
		world = new Matrix4f().setTranslation(new Vector3f(0));
		world.scale(scale); //Makes the tiles 32 x 32
	}
	
	public void render(TileRenderer render, Shader shader, Camera cam, Window window){
		int posX = ((int)cam.getPosition().x+(window.getWidth()/2)) / (scale*2);
		int posY = ((int)cam.getPosition().y-(window.getHeight()/2)) / (scale*2);
		
		for(int i=0; i < view; i++){
			for(int j=0; j < view; j++){
				Tile t = getTile(i-posX,j+posY);
				if(t != null){
					render.renderTile(t, i-posX,-j-posY,shader,world,cam);
					
				}
			}
		}
	}

	public void correctCamera(Camera camera, Window window){
		Vector3f pos = camera.getPosition();
		
		int w = -width * scale * 2;
		int h = height * scale * 2;

		if(pos.x > -(window.getWidth()/2)+scale){
			pos.x = -(window.getWidth()/2)+scale;
		}
		if(pos.x < w + (window.getWidth()/2)+scale){
			pos.x = w + (window.getWidth()/2)+scale;
		}
		
		if(pos.y < (window.getHeight()/2)-scale){
			pos.y = (window.getHeight()/2)-scale;
		}
		if(pos.y > h-(window.getHeight()/2)-scale){
			pos.y = h-(window.getHeight()/2)-scale;
		}
		
		
	}
	
	
	public void setTile(Tile tile, int x, int y){
		tiles[x+y*width] = tile.getId();
		if(tile.isSolid()){
			bounding_boxes[x + y * width] = new AABB(new Vector2f(x*2, -y*2), new Vector2f(1,1));
		}
		else
		{
			bounding_boxes[x + y * width] = null;
		}
	}
	
	public void setGlobalTile(Tile tile, int x, int y, Window window, Camera camera){
		
		//System.out.println(String.format("PosX %.8g%n", ;
		//System.out.println(String.format("PosY %.8g%n", (;
		//x = (x+(window.getWidth()/2)) / (scale*2);
		
		//y = (y-(window.getHeight()/2)) / (scale*2);
		
		float x1 = ((((float)camera.getPosition().x+(window.getWidth()/2)) / (scale*2)) -0.25f);
		float y1 = ((((float)camera.getPosition().y-(window.getHeight()/2))/ (scale*2)) +0.5f);

		
		float CameraPositionX = (float)camera.getPosition().x;
		float CameraPositionY = (float)camera.getPosition().y;
		
		float WindowWidth = (float)window.getWidth();
		float WindowHeight = (float)window.getHeight();
		
		float TestX = -(float)((CameraPositionX+(WindowWidth/2))/(scale*2))+0.5f;
		float TestY = (float)((CameraPositionY-(WindowHeight/2))/(scale*2))+0.5f;
		
		System.out.println(String.format("TestX %.8g%n", TestX));
		System.out.println(String.format("TestY %.8g%n", TestY));
		
		
		System.out.println(String.format("WindowX %.8g%n", WindowWidth));
		System.out.println(String.format("WindowY %.8g%n", WindowHeight));
		
		System.out.println(String.format("CameraX %.8g%n", CameraPositionX));
		System.out.println(String.format("CameraY %.8g%n", CameraPositionY));
		//x = (x+(width/2))/(scale*2);
		//y = (y+(height/2))/(scale*2);
		x = x/(scale*2);
		
		
		x += TestX;
		y += TestY;
		
		//x = Math.round(x);
		//y = Math.round(y);
		//System.out.println(String.format("x1 %.8g%n", x));
		//x += 0.25f;
		//y += 0.5f;
		
		
		tiles[x+y*width] = tile.getId();
		if(tile.isSolid()){
			bounding_boxes[x + y * width] = new AABB(new Vector2f(x*2, -y*2), new Vector2f(1,1));
		}
		else
		{
			bounding_boxes[x + y * width] = null;
		}
	}

	public Tile getTile(int x, int y){
		try{
			return Tile.tiles[tiles[x+y*width]];
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}
	
	public AABB getTileBoundingBox(int x, int y){
		try{
			return bounding_boxes[x+y*width];
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}
	public int getScale() {
		return scale;
	}
	
	public int getWidth() {
		return width * 2;
	}
	
	public int getHeight() {
		return height * 2;
	}
}
