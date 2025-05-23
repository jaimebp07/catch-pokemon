package catchPokemons.view;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import catchPokemons.model.Entity.Latitude;
import catchPokemons.model.Entity.Length;
import catchPokemons.model.Entity.Player;
import catchPokemons.model.Entity.Pokemon;
import catchPokemons.model.dataStruture.Nodo;
import catchPokemons.model.dataStruture.SimpleList;

@SuppressWarnings("serial")
public class Screen extends JFrame {

	private JDialog dialogShowPokemons;
	private GameArea gameArea;
	private PlayerGUI playerGUI;
	public JDialog panelSeePokemons;
	private static final int centerInX = 683;
	private static final int centerInY = 384;
	private static final int deltaCenterX = 670;
	private static final int deltaCenterY = 335;
	private static final int limitY = centerInY + deltaCenterY;

	public Screen(Player player, String urlIhmage, int colorPayer) {
		createScreen();
		assingBackground();
		this.createPlayer(player, urlIhmage, colorPayer);
	}

	public Screen() {
		createScreen();
		assingBackground();
	}

	public void createScreen() {
		setSize(new Dimension(1366, 768));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setLayout(null);
		setVisible(true);
	}

	public boolean isWithinTheWorld(int Px, int Py) {
		if (Py > 0 && Py < limitY) {
			double r1 = (Math.pow((Px - centerInX), 2)) / (Math.pow(deltaCenterX, 2));
			double r2 = (Math.pow((Py - centerInY), 2)) / (Math.pow(deltaCenterY, 2));
			if ((r1 + r2) <= 1)
				return true;
		}
		return false;
	}

	public void assingBackground() {
		gameArea = new GameArea("means/images/map/map.png");
		gameArea.setVisible(true);
		gameArea.setBounds(new Rectangle(0, 0, getWidth(), getHeight()));
		add(gameArea);
		repaint();
	}

	/**
	 * Pinta el pokemon en la posicion indicada
	 * 
	 * @param x
	 * @param y
	 * @param positionSpriteX
	 * @param positionSpriteY
	 */
	private void paintPokemon(int x, int y, int positionSpriteX, int positionSpriteY) {
		PokemonGUI pokemonGUI = new PokemonGUI(positionSpriteX, positionSpriteY, 40,
				"means/images/pokemons/spriter_pokemons.png");
		pokemonGUI.setBounds(x - 20, y - 20, 40, 40);
		gameArea.add(pokemonGUI);
	}

	/**
	 * Recibe una lista simple, compuesta por los pokemones a dibujar
	 * 
	 * @param simpleListPokemons
	 */
	public void drawPokemons(SimpleList<Pokemon> simpleListPokemons) {
		gameArea.removeAll();
		gameArea.add(playerGUI);
		Nodo<Pokemon> nodoPokemon = simpleListPokemons.getHead();
		while (nodoPokemon != null) {
			paintPokemon(
					convertToPixelesX(nodoPokemon.getInfo().getCoordinate().getLatitude(),
							nodoPokemon.getInfo().getCoordinate().getLength()),
					convertToPixelesY(nodoPokemon.getInfo().getCoordinate().getLatitude()),
					nodoPokemon.getInfo().getPositionSpriterX(), nodoPokemon.getInfo().getPositionSpriterY());
			nodoPokemon = nodoPokemon.getNext();
		}
		gameArea.repaint();
	}

	/**
	 * convierte la longitud a pixeles para dibujarlo en el area de juego
	 * 
	 * @param latitude
	 * @param length
	 * 
	 * @return
	 */
	public int convertToPixelesX(Latitude latitude, Length length) {
		double pixeles = ((deltaCenterX) / ((111325 * 180) + (1855.42 * 60) + (30.92 * 60)))
				* positionXInMeters(latitude, length);
		return (int) (centerInX + pixeles);
	}

	/**
	 * retorna la pocion en el eje x, comvirtiendo la coordenada de longitud a
	 * metros
	 * 
	 * @param latitude
	 * 
	 * @param length
	 * 
	 * @return
	 */
	public int positionXInMeters(Latitude latitude, Length length) {
		int result = (int) (Math.cos(latitude.getDegrees() * ((3.1416 * 2) / 360))
				* ((length.getDegrees() * 111325) + (length.getMinutes() * 1855.42) + (length.getSeconds() * 30.92)));
//		result += shoreWorld * latitude.getDegrees() * (Math.sin(latitude.getDegrees() * ((3.1416 * 2) / 360)));
		if (length.getDirection() == 'W')
			result = (result * (-1));
		return result;
	}

//______________________________________________________________________________________________

	public double convertPxToMetersX(int pxX, Latitude latitude) {
		double meters = ((20151680.4 * (pxX - centerInX))
				/ (deltaCenterX * Math.cos(latitude.getDegrees() * ((3.1416 * 2) / 360))));
		return meters;
	}

	public Length convertMetersToLength(double meters) {
		char direction = 'E';
		if (meters < 0) {
			direction = 'W';
			meters = meters * (-1);
		}
		short degrees = (short) (meters / 111325);
		byte minutes = (byte) ((meters - (111325 * degrees)) / 1855.42);
		float seconds = (float) ((meters - ((111325 * degrees) + (1855.42 * minutes))) / (30.92));
		return new Length(degrees, minutes, seconds, direction);
	}

	// _________________________________________________________________________________________________________

	/**
	 * convierte la latitud a pixeles para dibujarlo en el area de juego
	 * 
	 * @param latitude
	 * 
	 * @return
	 */
	public int convertToPixelesY(Latitude latitude) {
		double pixeles = ((deltaCenterY) / ((111325 * 90) + (1855.42 * 60) + (30.92 * 60)))
				* positionYInMeters(latitude);
		return (int) (centerInY + pixeles);
	}

	/**
	 * retorna la pocion en el eje y, comvirtiendo la coordenada de latitud a metros
	 * 
	 * @param locationLatitude
	 * 
	 * @return
	 */
	public int positionYInMeters(Latitude locationLatitude) {
		int result = (int) ((locationLatitude.getDegrees() * 111325) + (locationLatitude.getMinutes() * 1855.42)
				+ (locationLatitude.getSeconds() * 30.92));
		if (locationLatitude.getDirection() == 'N') {
			result = (result * (-1));
		}
		return result;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * covierte los pixeles a metros
	 * 
	 * @param pxY
	 * @return
	 */
	public double convertPxToMetersY(int pxY) {
		double meters = (10132430.4 * (pxY - centerInY)) / deltaCenterY;
		return meters;
	}

	/**
	 * convierte los metros del eje y a coordenada geografica
	 * 
	 * @param meters
	 * @return
	 */
	public Latitude convertMetersToLatitude(double meters) {
		char direction = 'S';
		if (meters < 0) {
			direction = 'N';
			meters = meters * (-1);
		}
		byte degrees = (byte) (meters / 111325);
		byte minutes = (byte) ((meters - (111325 * degrees)) / 1855.42);
		float seconds = (float) ((meters - ((111325 * degrees) + (1855.42 * minutes))) / (30.92));
		return new Latitude(degrees, minutes, seconds, direction);
	}

	/**
	 * pinta el jugador en la posicion indicada
	 * 
	 * @param x
	 * @param y
	 * @param xSprite
	 * @param ySprite
	 */
	public void paintPlayer(int x, int y, int xSprite, int ySprite) {
		remove(playerGUI);
		playerGUI.xSprite = xSprite;
		playerGUI.ySprite = ySprite;
		playerGUI.setBounds(x - 24, y - 24, 48, 48);
		gameArea.add(playerGUI);
		gameArea.repaint();
	}

	public void createPlayer(Player player, String urlIhmage, int colorPayer) {
		playerGUI = new PlayerGUI(urlIhmage, colorPayer);
		playerGUI.setBounds(
				this.convertToPixelesX(player.getCoordinate().getLatitude(), player.getCoordinate().getLength()),
				this.convertToPixelesY(player.getCoordinate().getLatitude()), 48, 48);
		gameArea.add(playerGUI);
	}

	public void showDialogSeePokemons(Player player) {
		dialogShowPokemons = new JDialog(this);
		PanelPokemons panelPokemons = new PanelPokemons(player.getCatchPokemons());
		JScrollPane scrollPokemons = new JScrollPane(panelPokemons);
		dialogShowPokemons.add(scrollPokemons);
		dialogShowPokemons.setBounds(centerInX - 150, centerInY - 250, 300, 500);
		dialogShowPokemons.setVisible(true);
	}
}