package catchPokemons.testUnits;

import java.util.Timer;
import java.util.TimerTask;

import catchPokemons.model.DAO.DataHandler;
import catchPokemons.model.Entity.Pokemon;
import catchPokemons.model.dataStruture.Nodo;

public class TestToUpdateLocationPokemons {

	private Nodo<Pokemon> pokemons;

	public TestToUpdateLocationPokemons() {
		DataHandler dataHandler = new DataHandler();
		pokemons = dataHandler.getListOfPokemos().getHead();
		int i = 1;

		Timer timer = new Timer();
		TimerTask homework = new TimerTask() {
			@Override
			public void run() {
				dataHandler.toUpdateLocationPokemons();
			}
		};
		timer.schedule(homework, 3000, 10);
		while (pokemons != null) {
			System.out.println("" + i + "  "
					+ (!pokemons.getInfo().getCoordinate().equals(dataHandler.findPokemon(pokemons.getInfo().getId()))
							? "OK"
							: "Error"));
			pokemons = pokemons.getNext();
			i++;
		}
	}

	public static void main(String[] args) {
		new TestToUpdateLocationPokemons();
	}
}
