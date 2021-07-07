import java.awt.Color;
import java.awt.Graphics;

public class Ray {
	Vector2 pos, dir;
	Vector2 point;
	boolean pointing;
	boolean pointingClosest;
	
	public Ray(Vector2 pos, double angle) {
		this.pos = pos;
		this.dir = Vector2.fromAngle(angle);
	}
	
	public void show(Graphics g) {		
		if(pointingClosest) {
			//g.fillOval(point.x() - 4, point.y() - 4, 8, 8);
			Main.lightIntensity = (int) Vector2.clamp(Main.lightIntensity, 0, 255);
			g.setColor(new Color(255, 255, 255, Main.lightIntensity));
			g.drawLine(pos.x(), pos.y(), point.x(), point.y());
				
			g.translate(pos.x(), pos.y());
			g.drawLine(0, 0, dir.x(), dir.y());
			g.translate(-pos.x(), -pos.y());
		}
	}
	
	public void lookAt(double x, double y) {
		dir.x = x - pos.x;
		dir.y = y - pos.y;
	}
	
	public void cast(Boundary wall) {
		double x1 = wall.a.x;
		double y1 = wall.a.y;
		double x2 = wall.b.x;
		double y2 = wall.b.y;
	
		double x3 = this.pos.x;
		double y3 = this.pos.y;
		double x4 = this.pos.x + this.dir.x;
		double y4 = this.pos.y + this.dir.y;
		
		double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
		if(den == 0) return;
		
		double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
		double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
		
		if(t > 0 && t < 1 && u > 0) {
			point = new Vector2(x1 + t * (x2 - x1), y1 + t *(y2 - y1));
			pointing = true;
		}else {
			pointing = false;
		}
		
	}
	
}
