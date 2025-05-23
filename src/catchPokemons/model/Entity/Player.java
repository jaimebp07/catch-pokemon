package catchPokemons.model.Entity;

import catchPokemons.model.dataStruture.SimpleList;

public class Player extends Anime {

	private SimpleList<Pokemon> catchPokemons;

	public Player(int id, String name, GeographicCoordinate geologicalCoordinate) {
		super(id, name, geologicalCoordinate);
		catchPokemons = new SimpleList<>();
	}

	public void setPokemon(Pokemon pokemon) {
		catchPokemons.add(pokemon);
	}

	public SimpleList<Pokemon> getCatchPokemons() {
		return catchPokemons;
	}
}