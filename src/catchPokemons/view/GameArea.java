package catchPokemons.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameArea extends JPanel {

	private ImageIcon ico;
	private Image backgroundimage;

	public GameArea(String url) {
		setLayout(null);
		setSize(1366, 768);
		ico = new ImageIcon(url);
		backgroundimage = ico.getImage();
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(backgroundimage, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
	}
}
