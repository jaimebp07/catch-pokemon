package catchPokemons.testUnits;

import catchPokemons.model.DAO.FileOperations;
import catchPokemons.model.dataStruture.MyQueue;

public class TestReadPokemonsOfFile {

	public TestReadPokemonsOfFile() {
		MyQueue<String> queue = FileOperations.read();
		showQueue(queue);
	}

	private static void showQueue(MyQueue<String> queue) {
		while (queue.getTam() > 0) {
			String[] s = queue.remove().split(",");
			System.out.println("x: " + s[1] + "  y: " + s[2] + "  name: " + s[0]);
		}
	}

	public static void main(String[] args) {
		new TestReadPokemonsOfFile();
	}
}