package catchPokemons.testUnits;

import catchPokemons.model.DAO.DataHandler;

public class TestSetUsers {

	private DataHandler dataHandler;

	public TestSetUsers() {
		dataHandler = new DataHandler();
//		dataHandler.addUsers(3);
	}

	public static void main(String[] args) {
		new TestSetUsers();
	}
}