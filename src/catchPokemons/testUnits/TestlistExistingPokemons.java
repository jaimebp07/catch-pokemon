package catchPokemons.testUnits;

import catchPokemons.model.DAO.DataHandler;
import catchPokemons.model.Entity.Pokemon;
import catchPokemons.model.dataStruture.Nodo;

public class TestlistExistingPokemons {

	public TestlistExistingPokemons() {
		DataHandler dataHandler = new DataHandler();
		Nodo<Pokemon> nodo = dataHandler.getListOfPokemos().getHead();
		while (nodo != null) {
			System.out.println(nodo.getInfo().getName());
			nodo = nodo.getNext();
		}
	}

	public static void main(String[] args) {
		new TestlistExistingPokemons();
	}
}
