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
	
	public Collision getPredictiveCollisionWithStatic(AABB box2, Vector2f vel, boolean correct){
		
		float speed = vel.length();
		Vector2f distance = box2.center.sub(center, new Vector2f());
		distance.sub(half_extent.add(box2.half_extent, new Vector2f()));
		distance.x = (float)Math.abs(distance.x);
		distance.y = (float)Math.abs(distance.y);
		
		
		float colTimeX = distance.x/vel.x;
		float colTimeY = distance.y/vel.y;
		if (colTimeX < 1 && colTimeY < 1){
			float colTime = Math.min(colTimeX,colTimeY);
			if (correct){
				center.add(distance.mul(colTime));
			}
			return new Collision(distance.mul(colTime), true);
		}
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
