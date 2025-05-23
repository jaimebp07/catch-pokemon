package catchPokemons.testUnits;

import catchPokemons.model.DAO.DataHandler;
import catchPokemons.model.Entity.GeographicCoordinate;
import catchPokemons.model.Entity.Latitude;
import catchPokemons.model.Entity.Length;

public class TestCalculatePixelsForPaint {

	private DataHandler dataHandler;

	public TestCalculatePixelsForPaint() {
		dataHandler = new DataHandler();
		GeographicCoordinate geologicalCoordinate = new GeographicCoordinate(
				new Length((short) 90, (byte) 0, (float) 0, 'W'), new Latitude((byte) 90, (byte) 0, (float) 0, 'S'));
		System.out.println("x "
				+ dataHandler.convertToPixelesX(geologicalCoordinate.getLatitude(), geologicalCoordinate.getLength())
				+ "  y " + dataHandler.convertToPixelesY(geologicalCoordinate.getLatitude()));
	}

	public static void main(String[] args) {
		new TestCalculatePixelsForPaint();
	}
}