package catchPokemons.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PokemonGUI extends JLabel {

	private Image sprite;
	private int positionSpriteX;
	private int positionSpriteY;
	private int side;

	public PokemonGUI() {
		sprite = (new ImageIcon("means/images/pokemons/spriter_pokemons.png")).getImage();
	}

	public PokemonGUI(int positionSpriteX, int positionSpriteY, int side, String url) {
		sprite = (new ImageIcon(url)).getImage();
		this.side = side;
		this.positionSpriteX = positionSpriteX * side;
		this.positionSpriteY = positionSpriteY * side;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(sprite, 0, 0, side, side, positionSpriteX, positionSpriteY, positionSpriteX + side,
				positionSpriteY + side, this);
	}
}