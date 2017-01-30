package entity;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import collision.AABB;
import collision.Collision;
import io.Window;
import render.Animation;
import render.Camera;
import render.Model;
import render.Shader;
import render.Texture;
import world.World;

public class Player {
	private Model model;
	private AABB bounding_box;
	//private Texture texture;
	private Animation texture;
	private Transform transform;

	int speed = 10;
	
	public Player() {
		float[] vertices = new float[]{
				-1f,1f,0, 	//TOP LEFT
				1f,1f,0,	//TOP RIGHT
				1f,-1f,0,	//BOTTOM RIGHT
				-1f,-1f,0,	//BOTTOM LEFT
		};
		
		float[] texture = new float[] {
				0,0,
				1,0,
				1,1,
				0,1,
		};
		
		int[] indices = new int[]{
				0,1,2,
				2,3,0
		};
		
		model = new Model(vertices, texture,indices);
		//model.
		//this.texture = new Texture("player.png");
		this.texture = new Animation(8,5,"wait");
		transform = new Transform();
		transform.scale = new Vector3f(32,32,1);
		transform.pos.x = 13;
		transform.pos.y = -13;
		
		System.out.println("intersecting1");
		bounding_box = new AABB(new Vector2f(transform.pos.x,transform.pos.y), new Vector2f(1,1));

	}

	public void update(Window window, Camera camera, World world){
		//System.out.println(String.format("Pos %.8g%n", transform.pos.x));
		Vector2f velocity = new Vector2f();
		
		//transform.pos.mul(new Vector3f(velocity.x,velocity.y,0));
		
		bounding_box.getCenter().set(transform.pos.x,transform.pos.y);
		
		AABB[] boxes = new AABB[25];
		for(int i = 0; i< 5; i ++){
			for(int j = 0; j< 5; j ++){
				boxes[i + j * 5] = world.getTileBoundingBox(
						(int)(((transform.pos.x/2)+0.5f) - (5/2)) + i,
						(int)(((-transform.pos.y/2)+0.5f) - (5/2)) + j
						);
			}
		}
		
		AABB box = null;
		
		for(int i = 0; i < boxes.length; i++){
			if(boxes[i] != null){
				if(box == null) box = boxes[i];
				
				Vector2f length1 = box.getCenter().sub(transform.pos.x,transform.pos.y, new Vector2f());
				Vector2f length2 = boxes[i].getCenter().sub(transform.pos.x,transform.pos.y, new Vector2f());
				
				if(length1.lengthSquared() > length2.lengthSquared()){
					box = boxes[i];
				}
			}
		}
		if(box != null){
			//Collision data = bounding_box.getPredictiveCollisionWithStatic(box,velocity,true,delta);
			Collision data = bounding_box.getCollision(box);
			if(!data.isIntersecting){
				//System.out.println("Not intersecting");
				//bounding_box.correctPosition(box, data);
				//transform.pos.add(velocity.x,velocity.y,0);
				
				
			}
			else{
				bounding_box.correctPosition(box, data);
				//System.out.println("intersecting");
				transform.pos.set(bounding_box.getCenter(),0);
				
				
				//box = null;
				
				for(int i = 0; i < boxes.length; i++){
					if(boxes[i] != null){
						if(box == null) box = boxes[i];
						
						Vector2f length1 = box.getCenter().sub(transform.pos.x,transform.pos.y, new Vector2f());
						Vector2f length2 = boxes[i].getCenter().sub(transform.pos.x,transform.pos.y, new Vector2f());
						
						if(length1.lengthSquared() > length2.lengthSquared()){
							box = boxes[i];
						}
					}
				}
				if(box != null){
					//Collision data = bounding_box.getPredictiveCollisionWithStatic(box,velocity,true,delta);
					data = bounding_box.getCollision(box);
					if(!data.isIntersecting){
						//System.out.println("Not intersecting");
						//bounding_box.correctPosition(box, data);
						//transform.pos.add(velocity.x,velocity.y,0);
						
						
					}
					else{
						bounding_box.correctPosition(box, data);
						//System.out.println("intersecting");
						transform.pos.set(bounding_box.getCenter(),0);
						
						
					}
					
					
				}
			}
			
			
		}
		else{
			//transform.pos.add(velocity.x,velocity.y,0);
		}
		
		
		
		
		
		camera.setPosition(transform.pos.mul(-world.getScale(), new Vector3f()));
	}
	
	public void input(Window window, float delta) {
		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_A)){
			transform.pos.add(new Vector3f(-speed*delta,0,0));
			//velocity.add(-speed*delta, 0);
		}
		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_W)){
			transform.pos.add(new Vector3f(0,speed*delta,0));
			//velocity.add(0, speed*delta);
		}
		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_D)){
			transform.pos.add(new Vector3f(speed*delta,0,0));
			//velocity.add(speed*delta, 0);
		}
		if(window.getInput().isKeyDown(GLFW.GLFW_KEY_S)){
			transform.pos.add(new Vector3f(0,-speed*delta,0));
			//velocity.add(0, -speed*delta);
		}
	}
	
	public void render(Shader shader, Camera camera){
		shader.bind();
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", transform.getProjection(camera.getProjection()));
		texture.bind(0);
		model.render();
	}
}
