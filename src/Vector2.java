
public class Vector2 {
	public double x, y;
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Vector2(double xy) {
		this.x = xy;
		this.y = xy;
	}
	
	public int x() {
		return (int)x;
	}
	public int y() {
		return (int)y;
	}
	
	public void add(Vector2 v1) {
		x += v1.x;
		y += v1.y;
	}
	
	//general normalize method
	public static double normalize(double value, double min, double max, double minV, double maxV) {
		double norm = (value - min)/(max - min);
		return minV + (norm * maxV);
	}
	
	//zero
	public static Vector2 zero() {
		return new Vector2(0);
	}
	
	//equals
	public static boolean equals(Vector2 v1, Vector2 v2) {
		if(v1.x == v2.x && v1.y == v2.y) return true;
		return false;
	}
	public boolean equals(Vector2 v1) {
		if(this.x == v1.x && this.y == v1.y) return true;
		return false;
	}
	
	//magnitude
	public static double mag(Vector2 v1, Vector2 v2) {
		return Math.hypot(v1.x - v2.x, v1.y - v2.y);
	}
	public double mag(Vector2 v2) {
		return Math.hypot(Math.abs(this.x - v2.x), Math.abs(this.y - v2.y));
	}
	
	//subtract
	public static Vector2 sub(Vector2 v1, Vector2 v2) {
		return new Vector2(v2.x + ( v1.x - v2.x), v2.y + (v1.y - v2.y));
	}
	public Vector2 sub(Vector2 v2) {
		return new Vector2(v2.x + ( this.x - v2.x), v2.y + (this.y - v2.y));
	}
	
	//multiplication
	public static Vector2 mult(Vector2 v1, double value) {
		return new Vector2(v1.x * value, v1.y * value);
	}
	public Vector2 mult(double value) {
		return new Vector2(this.x * value, this.y * value);
	}

	//normalization
	public static Vector2 norm(Vector2 v1) {
		return new Vector2(v1.x / Math.hypot(v1.x, v1.y), v1.y / Math.hypot(v1.x, v1.y));
	}
	public Vector2 norm() {
		return new Vector2(this.x / Math.hypot(this.x, this.y), this.y / Math.hypot(this.x, this.y));
	}
	
	//dot product
	public static double dotProduct(Vector2 v1, Vector2 v2) {
		double inProduct = (v1.x * v2.x) + (v1.y * v2.y);
		double v1Mag = Math.hypot(v1.x, v1.y);
		double v2Mag = Math.hypot(v2.x, v2.y);
		double mags = v1Mag * v2Mag;
		double cos = inProduct/mags;
		return Math.acos(cos);
	}
	public double dotProduct(Vector2 v2) {
		double inProduct = (this.x * v2.x) + (this.y * v2.y);
		double v1Mag = Math.hypot(this.x, this.y);
		double v2Mag = Math.hypot(v2.x, v2.y);
		double mags = v1Mag * v2Mag;
		double cos = inProduct/mags;
		return Math.acos(cos);
	}
	
	//from angle to vector
	public static Vector2 fromAngle(double angle) {
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		return new Vector2(cos, sin);
	}
	
	//clamp
	public static double clamp(double value, double min, double max) {
		if(value < min) return value = min;
		if(value > max) return value = max;
		return value;
	}
	
}
