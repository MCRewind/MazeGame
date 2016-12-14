package collision;

import org.joml.Vector2f;

public class AABB {
	private Vector2f center, half_extent;


	public AABB(Vector2f center, Vector2f half_extent) {
		this.center = center;
		this.half_extent = half_extent;
	}
	
	public Collision getCollision(AABB box2){
		Vector2f distance = box2.center.sub(center, new Vector2f());
		distance.x = (float)Math.abs(distance.x);
		distance.y = (float)Math.abs(distance.y);
		
		distance.sub(half_extent.add(box2.half_extent, new Vector2f()));
		
		return new Collision(distance,(distance.x < 0 && distance.y < 0));
	}
	
	public Collision getPredictiveCollisionWithStatic(AABB box2, Vector2f vel, boolean correct, float delta){
		
		float speed = vel.length();
		boolean approaching = false;
		Vector2f distance = box2.center.sub(center, new Vector2f());
		System.out.flush();
		//vel.mul(1/delta);ds
		//if(distance.sub(vel).
		
		distance.x = (float)Math.abs(distance.x);
		distance.y = (float)Math.abs(distance.y);
		distance.sub(half_extent.add(box2.half_extent, new Vector2f()));
		System.out.println(String.format("Distance %.8g%n%.8g%n", distance.x, distance.y));
		System.out.println(String.format("Velocity %.8g%n%.8g%n", vel.x, vel.y));
		float colTimeX = distance.x/(vel.x);
		float colTimeY = distance.y/(vel.y);
		
		colTimeX *= delta;
		colTimeY *= -delta; //delta = 1/fps
		if ((float)Math.abs(colTimeX) > 500) colTimeX = 0;
		if ((float)Math.abs(colTimeY) > 500) colTimeY = 0;
		float positiveTimeX = (float)Math.abs(colTimeX);
		float positiveTimeY = (float)Math.abs(colTimeY);
		System.out.println(String.format("Time %.8g%n%.8g%n", colTimeX, colTimeY));
		//if((colTimeX < delta && colTimeY < delta)&&(colTimeX > -delta && colTimeY > -delta)){
		if((colTimeX < delta || colTimeX > -delta)&&(colTimeY < delta || colTimeY > -delta)){
			float colTime = Math.min(colTimeX,colTimeY);
			if (correct){
				center.add(vel.x * (positiveTimeX),(vel.y * (positiveTimeY)));
				System.out.println("Correcting");
			}
		
			return new Collision(distance.mul(colTime), true);
		}
		
		//if ((positiveTimeX < 1 && positiveTimeY < 1)&&(positiveTimeX > 0 && positiveTimeY > 0)){
			

			
			
		//}
		else
		{
			return new Collision(distance,false);
			
			//distance = distance.
			//return new Collision(distance, false);
		}
		

		
		//return new Collision(distance,(distance.x < 0 && distance.y < 0));
	}
	
	public void correctPosition(AABB box2, Collision data){
		Vector2f correction = box2.center.sub(center, new Vector2f());
		if(data.distance.x > data.distance.y){
			if(correction.x > 0){
				center.add(data.distance.x,0);
			}
			else
			{
				center.add(-data.distance.x,0);
			}
		}
		else
		{
			if(correction.y > 0){
				center.add(0,data.distance.y);
			}
			else
			{
				center.add(0,-data.distance.y);
			}
		}
	}
	
	public Vector2f getCenter(){return center;}
	public Vector2f getHalfExtent(){return half_extent;}

}
