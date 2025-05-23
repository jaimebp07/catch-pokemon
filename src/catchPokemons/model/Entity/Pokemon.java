package catchPokemons.model.Entity;

public class Pokemon extends Anime {

	private byte positionSpriterX;
	private byte positionSpriterY;

	public Pokemon(int id, String name, GeographicCoordinate geologicalCoordinate, byte x, byte y) {
		super(id, name, geologicalCoordinate);
		this.positionSpriterX = x;
		this.positionSpriterY = y;
	}

	public byte getPositionSpriterX() {
		return positionSpriterX;
	}

	public byte getPositionSpriterY() {
		return positionSpriterY;
	}
}