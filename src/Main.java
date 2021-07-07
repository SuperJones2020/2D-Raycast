import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Canvas implements Runnable, MouseMotionListener{
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 1200;
	public static int HEIGHT = 700;
	
	public static double mx, my;
	
	public static Random rand = new Random();
	Thread thread;
	
	private int wallsQuantity = 7;
	public static double raysQuantity = 320*4;
	public static int lightIntensity = 30;
	
	List<Boundary> walls = new ArrayList<Boundary>();
	Particle particle;
	
	public static void main(String[] args) {
		new Main();
	}
	public Main() {
		this.addMouseMotionListener(this);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = screenSize.width;
		HEIGHT = screenSize.height;
		
		//WALLS
		walls.add(new Boundary(0, 0, 0, HEIGHT));
		walls.add(new Boundary(WIDTH, 0, WIDTH, HEIGHT));
		walls.add(new Boundary(0, 0, WIDTH, 0));
		walls.add(new Boundary(0, HEIGHT, WIDTH, HEIGHT));
		
		for(int i = 0; i < wallsQuantity; i++) {
			int x1 = rand.nextInt(WIDTH);
			int x2 = rand.nextInt(WIDTH);
			int y1 = rand.nextInt(HEIGHT);
			int y2 = rand.nextInt(HEIGHT);
			walls.add(new Boundary(x1, y1, x2, y2));
		}
		
		particle = new Particle();
		
		new Window(WIDTH, HEIGHT, "2D Raycasting", this);
	}
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}
	public int getN(double value) {
		return (int)value;
	}
	public static double normalize(double value, double min, double max, double minV, double maxV) {
		double norm = (value - min)/(max - min);
		return minV + (norm * maxV);
	}
	
	//UPDATE--
	private void update() {

		particle.set(mx, my);
		particle.look(walls);
		
		
		//ray.cast(wall.a, wall.b);
		//ray.lookAt(mx, my);
		//ray.dir = ray.dir.norm();
		//ray.dir = ray.dir.mult(900);
	}
	
	//RENDER--
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < walls.size(); i++) {
			Boundary w = walls.get(i);
			w.show(g);
		}
		particle.show(g);

		//ray.show(g);
		
		g.dispose();
		bs.show();
	}
	

	@Override
	public void run() {
		while(true) {
			try {
				render();
				update();
				
				Thread.sleep(1000/60);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
	}
	
}
