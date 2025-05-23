package catchPokemons.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerGUI extends JLabel {

	private Image sprite;
	private int colorImage;
	protected int xSprite;
	protected int ySprite;

	public PlayerGUI(String urlIhmage, int colorPayer) {
		this.sprite = (new ImageIcon("means/images/players/" + urlIhmage)).getImage();
		this.colorImage = colorPayer;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int i = (colorImage * 144) + (48 * xSprite);
		int j = (48 * ySprite);
		g.drawImage(sprite, 0, 0, 48, 48, i, j, i + 48, j + 48, this);
	}
}