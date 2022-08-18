
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CSE 274 Final Project
 * 
 *  Instantiating this class created a GUI with the Control Panel on the left and
 *  places a visual image of the graph on the right for the user to see.
 */
public class UI extends JFrame {
		
	public UI() {
		this.setSize(1200, 800);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("GPS Shortest Path Appplication");
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		ControlPanel controlPanel = new ControlPanel();			
		JPanel picturePanel = getPicturePanel();
		
		container.add(controlPanel, BorderLayout.WEST);
		container.add(picturePanel, BorderLayout.CENTER);		
	}
	
	public JPanel getPicturePanel() {
		try {
			JPanel picturePanel = new JPanel();
			picturePanel.setSize(800, 800);
			BufferedImage picture = ImageIO.read(new File("FinalProjectGraph_Final_400x400.png"));
			ImageIcon icon = new ImageIcon(picture);
			Image scaleImage = icon.getImage().getScaledInstance(650, 650, Image.SCALE_DEFAULT);
			JLabel pictureLabel = new JLabel(new ImageIcon(scaleImage));
			picturePanel.add(pictureLabel);
			return picturePanel;
		} catch (IOException e) {
			e.printStackTrace();
			return new JPanel();
		}	
	}
	
}
