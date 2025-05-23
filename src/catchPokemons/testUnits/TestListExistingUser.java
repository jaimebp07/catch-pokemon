package catchPokemons.testUnits;

import catchPokemons.model.DAO.DataHandler;
import catchPokemons.model.Entity.Player;
import catchPokemons.model.dataStruture.Nodo;

public class TestListExistingUser {

	public TestListExistingUser() {
		DataHandler dataHandler = new DataHandler();
		Nodo<Player> nodo = dataHandler.getListOfUsers().getHead();
		while (nodo != null) {
			System.out.println(nodo.getInfo().getName());
			nodo = nodo.getNext();
		}
	}

	public static void main(String[] args) {
		new TestListExistingUser();
	}
}
