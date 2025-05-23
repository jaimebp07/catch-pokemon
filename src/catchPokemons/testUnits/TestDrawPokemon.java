package catchPokemons.testUnits;

import catchPokemons.model.Entity.GeographicCoordinate;
import catchPokemons.model.Entity.Latitude;
import catchPokemons.model.Entity.Length;
import catchPokemons.model.Entity.Pokemon;
import catchPokemons.model.dataStruture.SimpleList;
import catchPokemons.view.Screen;

public class TestDrawPokemon {

	public TestDrawPokemon() {
		Screen screen = new Screen();
		SimpleList<Pokemon> list = new SimpleList<>();
		GeographicCoordinate coordinate = new GeographicCoordinate(new Length((short) 180, (byte) 59, (float) 59, 'W'),
				new Latitude((byte) 20, (byte) 59, 59, 'N'));
		list.add(new Pokemon(4, "Andres", coordinate, (byte) 2, (byte) 0));
		screen.drawPokemons(list);
		screen.repaint();
	}

	public static void main(String[] args) {
		new TestDrawPokemon();
	}
}