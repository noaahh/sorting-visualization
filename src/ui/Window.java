package ui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public static final int WIN_WIDTH = 1280;
    public static final int WIN_HEIGHT = 720;
	
	public Window() {
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		this.setTitle("Sorting Visualization");
		this.setBounds(100, 100, 948, 533);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
