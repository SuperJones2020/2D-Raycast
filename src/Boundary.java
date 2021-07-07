import java.awt.Color;
import java.awt.Graphics;

public class Boundary {
	public Vector2 a, b;
	
	public Boundary(double x1, double y1, double x2, double y2) {
		this.a = new Vector2(x1, y1);
		this.b = new Vector2(x2, y2);
	}
	
	public void show(Graphics g) {
		g.setColor(Color.white);
		g.drawLine(a.x(), a.y(), b.x(), b.y());
	}
}
