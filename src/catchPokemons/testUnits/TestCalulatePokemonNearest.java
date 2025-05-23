package catchPokemons.testUnits;

import java.util.Comparator;

import catchPokemons.model.DAO.DataHandler;
import catchPokemons.model.Entity.Anime;
import catchPokemons.model.Entity.GeographicCoordinate;
import catchPokemons.model.Entity.Latitude;
import catchPokemons.model.Entity.Length;
import catchPokemons.model.Entity.Pokemon;
import catchPokemons.model.Entity.Player;
import catchPokemons.model.dataStruture.MyHeader;
import catchPokemons.model.dataStruture.MyMatrix;
import catchPokemons.model.dataStruture.MyQueue;
import catchPokemons.model.dataStruture.Nodo;
import catchPokemons.model.dataStruture.SimpleList;

public class TestCalulatePokemonNearest {

	private MyQueue<Player> myQueue;
	private MyMatrix<Length, Latitude, Anime> matrix;

	public TestCalulatePokemonNearest() {
		DataHandler dataHandler = new DataHandler();
		matrix = new MyMatrix<>(new Comparator<MyHeader<Length, Anime>>() {

			@Override
			public int compare(MyHeader<Length, Anime> o1, MyHeader<Length, Anime> o2) {
				return Length.convertToMetersCompare(o1.getInfo()) - Length.convertToMetersCompare(o2.getInfo());
			}
		}, new Comparator<MyHeader<Latitude, Anime>>() {
			@Override
			public int compare(MyHeader<Latitude, Anime> o1, MyHeader<Latitude, Anime> o2) {
				return Latitude.convertToMetersCompare(o1.getInfo()) - Latitude.convertToMetersCompare(o2.getInfo());
			}
		});
		myQueue = new MyQueue<>();
		addPokemons();
		addUserQueue();
		addUsersMatrix();
		dataHandler.setMatrixAnimes(matrix);

		SimpleList<Anime> list = dataHandler.getMatrixAnimes().makeTravelRows();
//		showList(list);
		System.out.println("rrr " + myQueue.peek().getName());
		Anime nearestPokemon = dataHandler.calulatePokemonNearest(myQueue.peek());
		System.out.println("el pokemon mas cercano a " + myQueue.peek().getName() + " es " + nearestPokemon.getName());
	}

	private void showList(SimpleList<Anime> list) {
		Nodo<Anime> nodo = list.getHead();
		while (nodo != null) {
			System.out.println("" + nodo.getInfo().toString());
			nodo = nodo.getNext();
		}
	}

	private void addUsersMatrix() {
		Nodo<Player> nodo = myQueue.getFirts();
		while (nodo != null) {
			matrix.set(nodo.getInfo().getCoordinate().getLength(), nodo.getInfo().getCoordinate().getLatitude(),
					nodo.getInfo());
			nodo = nodo.getNext();
		}

	}

	private void addUserQueue() {
		GeographicCoordinate coordinate = new GeographicCoordinate(new Length((short) 0, (byte) 0, (float) 0, 'E'),
				new Latitude((byte) 19, (byte) 19, (float) 19, 'N'));
		myQueue.offer(new Player(3, "Javier", coordinate));
		coordinate = new GeographicCoordinate(new Length((short) 0, (byte) 0, (float) 0, 'E'),
				new Latitude((byte) 80, (byte) 21, (float) 43.20, 'N'));
		myQueue.offer(new Player(2, "Andres", coordinate));
		coordinate = new GeographicCoordinate(new Length((short) 179, (byte) 59, (float) 59, 'E'),
				new Latitude((byte) 0, (byte) 0, (float) 0, 'N'));
		myQueue.offer(new Player(1, "Camilo", coordinate));
	}

	private void addPokemons() {
		int id = 0;
		GeographicCoordinate coordinate = new GeographicCoordinate(new Length((short) 173, (byte) 9, (float) 7, 'W'),
				new Latitude((byte) 19, (byte) 19, (float) 19, 'N'));
		matrix.set(coordinate.getLength(), coordinate.getLatitude(),
				new Pokemon(id++, "charmander", coordinate, (byte) 0, (byte) 0));

		coordinate = new GeographicCoordinate(new Length((short) 90, (byte) 31, (float) 9, 'E'),
				new Latitude((byte) 80, (byte) 21, (float) 43.20, 'N'));
		matrix.set(coordinate.getLength(), coordinate.getLatitude(),
				new Pokemon(id++, "ivy", coordinate, (byte) 0, (byte) 0));

		coordinate = new GeographicCoordinate(new Length((short) 179, (byte) 59, (float) 59, 'E'),
				new Latitude((byte) 30, (byte) 19, (float) 30, 'S'));
		matrix.set(coordinate.getLength(), coordinate.getLatitude(),
				new Pokemon(id++, "nidoran", coordinate, (byte) 0, (byte) 0));

		coordinate = new GeographicCoordinate(new Length((short) 173, (byte) 9, (float) 7, 'W'),
				new Latitude((byte) 73, (byte) 31, (float) 52, 'S'));
		matrix.set(coordinate.getLength(), coordinate.getLatitude(),
				new Pokemon(id++, "newToo", coordinate, (byte) 0, (byte) 0));

		coordinate = new GeographicCoordinate(new Length((short) 0, (byte) 0, (float) 0, 'E'),
				new Latitude((byte) 73, (byte) 31, (float) 53, 'S'));
		matrix.set(coordinate.getLength(), coordinate.getLatitude(),
				new Pokemon(id++, "Nidorina", coordinate, (byte) 0, (byte) 0));

		coordinate = new GeographicCoordinate(new Length((short) 0, (byte) 0, (float) 0, 'E'),
				new Latitude((byte) 0, (byte) 0, (float) 0, 'N'));
		matrix.set(coordinate.getLength(), coordinate.getLatitude(),
				new Pokemon(id++, "meowth", coordinate, (byte) 0, (byte) 0));
	}

	public static void main(String[] args) {
		new TestCalulatePokemonNearest();
	}
}
