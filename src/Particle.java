import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Particle {
	Vector2 pos;
	List<Ray> rays = new ArrayList<Ray>();
	double raysQuantity = 360/Main.raysQuantity;
	
	public Particle() {
		System.out.println(raysQuantity);
		this.pos = new Vector2(Main.WIDTH/2, Main.HEIGHT/2);
		for(double a = 0; a < 360; a += raysQuantity) {
			rays.add(new Ray(pos, Math.toRadians(a)));
		}
	}
	
	public void look(List<Boundary> walls) {
		for(int i = 0; i < rays.size(); i++) {
			double record = Main.WIDTH+Main.HEIGHT;
			Vector2 closest = null;
			Ray r = rays.get(i);
			for(int w = 0; w < walls.size(); w++) {
				Boundary wall = walls.get(w);
				r.cast(wall);
				
				if(r.pointing) {
					double d = Vector2.mag(pos, r.point);
					if(d < record) {
						record = d;
						closest = r.point;
					}
				}
			}
			if(closest != null) {
				r.point = closest;
				r.pointingClosest = true;
			}else r.pointingClosest = false;
		}
	}
	
	public void set(double x, double y) {
		pos.x = x;
		pos.y = y;
	}
	
	public void show(Graphics g) {
		g.fillOval(pos.x() - 4, pos.y() - 4, 8, 8);
		for(int i = 0; i < rays.size(); i++) {
			Ray r = rays.get(i);
			r.show(g);
		}
	}
	
}
