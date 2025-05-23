package catchPokemons.testUnits;

import catchPokemons.model.DAO.DataHandler;

public class TestFindPokemon {

	public TestFindPokemon() {
		DataHandler dataHandler = new DataHandler();
		System.out.println("Codigo no existente: " + (dataHandler.findPokemon(802) == null ? "OK" : "ERROR"));
		System.out.println("Codigo existente: " + (dataHandler.findPokemon(4) != null ? "OK" : "ERROR"));
	}

	public static void main(String[] args) {
		new TestFindPokemon();
	}
}
