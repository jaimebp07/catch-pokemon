package catchPokemons.model.Entity;

public class Anime {

	private String name;
	private int id;
	private GeographicCoordinate geographicCoordinate;

	public Anime() {
	}

	public Anime(int id, String name, GeographicCoordinate geographicCoordinate) {
		this.geographicCoordinate = geographicCoordinate;
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public GeographicCoordinate getCoordinate() {
		return geographicCoordinate;
	}

	public int getId() {
		return id;
	}

	public void setGeologicalCoordinate(GeographicCoordinate coordinate) {
		this.geographicCoordinate = coordinate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Anime [name=" + name + ", id=" + id + ", Coordinate=" + geographicCoordinate + "]";
	}
}