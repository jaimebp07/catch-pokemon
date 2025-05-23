package catchPokemons.model.Entity;

import java.util.Random;

public class Latitude {

	private short degrees;
	private byte minutes;
	private float seconds;
	private char direction;

	public Latitude(byte degrees, byte minutes, float seconds, char direction) {
		this.degrees = degrees;
		this.minutes = minutes;
		this.seconds = seconds;
		this.direction = direction;
	}

	public Latitude() {
		Random random = new Random();
		degrees = (short) random.nextInt(90);
		minutes = (byte) random.nextInt(60);
		seconds = (byte) random.nextInt(60);
		if (random.nextInt(2) == 1) {
			direction = 'S';
		} else {
			direction = 'N';
		}
	}

	public short getDegrees() {
		return degrees;
	}

	public short getMinutes() {
		return minutes;
	}

	public float getSeconds() {
		return seconds;
	}

	public char getDirection() {
		return direction;
	}

	public static int convertToMetersCompare(Latitude info) {
		int result = (int) ((info.degrees * 111325) + (info.minutes * 1855.42) + (info.seconds * 30.92));
		if (info.direction == 'N')
			result = result * (-1);
		return result;
	}

	@Override
	public String toString() {
		return "Latitude [" + degrees + "°" + minutes + "'" + seconds + "''" + direction + "]";
	}
}