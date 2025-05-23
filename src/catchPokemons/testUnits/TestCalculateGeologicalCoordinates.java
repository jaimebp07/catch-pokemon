package catchPokemons.testUnits;

import catchPokemons.model.DAO.DataHandler;
import catchPokemons.model.Entity.GeographicCoordinate;

public class TestCalculateGeologicalCoordinates {

	private DataHandler dataHandler;

	public TestCalculateGeologicalCoordinates() {
		dataHandler = new DataHandler();
		GeographicCoordinate coordinate = new GeographicCoordinate();

		System.out.println("Longitud: " + coordinate.getLength());
		System.out.println("Lattitud: " + coordinate.getLatitude());
	}

	public static void main(String[] args) {
		new TestCalculateGeologicalCoordinates();
	}
}

// 1366 * 768
