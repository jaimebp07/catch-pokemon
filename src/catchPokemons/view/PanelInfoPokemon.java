package catchPokemons.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import catchPokemons.model.Entity.Pokemon;

public class PanelInfoPokemon extends JPanel {

	@SuppressWarnings("unused")
	private static final Image sprite = (new ImageIcon("means/images/pokemons/spriter_pokemons.png")).getImage();

	public PanelInfoPokemon(Pokemon pokemon) {
		setLayout(new BorderLayout());
		add(new JLabel("      "), BorderLayout.NORTH);
		add(createLblPhoto(pokemon.getPositionSpriterX(), pokemon.getPositionSpriterY()), BorderLayout.CENTER);
		add(createLblName(pokemon.getName()), BorderLayout.SOUTH);
		setMinimumSize(new Dimension(100, 120));
		repaint();
	}

	public PanelInfoPokemon() {
		setLayout(new BorderLayout());
		add(new JLabel("      "), BorderLayout.NORTH);
		add(createLblPhoto((byte) 0, (byte) 0), BorderLayout.CENTER);
		add(createLblName("1  wwwwwwwwww   "), BorderLayout.SOUTH);
		repaint();
	}

	private JLabel createLblPhoto(byte i, byte j) {
		PokemonGUI gui = new PokemonGUI(i, j, 80, "means/images/pokemons/spriter_pokemones.png");
		gui.setBackground(Color.BLACK);
		gui.setMinimumSize(new Dimension(80, 80));
		return gui;
	}

	private JLabel createLblName(String name) {
		JLabel lblName = new JLabel();
		lblName.setText(name);
		lblName.setBounds(0, 0, 100, 20);
		return lblName;
	}

	private JLabel createLblId(int id) {
		JLabel lblId = new JLabel();
		lblId.setText(String.valueOf(id));
		lblId.setBounds(0, 0, 100, 40);
		return lblId;
	}

}
