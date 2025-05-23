package catchPokemons.model.Entity;

public class GeographicCoordinate {

	private Latitude latitude;
	private Length length;

	public GeographicCoordinate() {
		latitude = new Latitude();
		length = new Length();
	}

	public GeographicCoordinate(Length length, Latitude latitude) {
		super();
		this.latitude = latitude;
		this.length = length;
	}

	public Latitude getLatitude() {
		return latitude;
	}

	public void setLatitude(Latitude latitude) {
		this.latitude = latitude;
	}

	public Length getLength() {
		return length;
	}

	public void setLength(Length length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "Coordinate [latitude=" + latitude + ", length=" + length + "]";
	}
}