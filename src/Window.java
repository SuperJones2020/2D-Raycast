
import javax.swing.JFrame;

public class Window extends JFrame{
	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Main main) {
		JFrame f = new JFrame();
		//f.setPreferredSize(new Dimension(width, height));
		//f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle(title);
		f.setLocationRelativeTo(null);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//f.setUndecorated(true);
		f.setVisible(true);
		f.add(main);
		main.start();
	}
}
