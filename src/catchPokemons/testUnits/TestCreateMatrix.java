package catchPokemons.testUnits;

import catchPokemons.model.DAO.DataHandler;
import catchPokemons.model.Entity.Anime;
import catchPokemons.model.Entity.GeographicCoordinate;
import catchPokemons.model.Entity.Latitude;
import catchPokemons.model.Entity.Length;
import catchPokemons.model.Entity.Pokemon;
import catchPokemons.model.Entity.Player;
import catchPokemons.model.dataStruture.MyCell;
import catchPokemons.model.dataStruture.MyHeader;
import catchPokemons.model.dataStruture.MyMatrix;
import catchPokemons.model.dataStruture.Nodo;
import catchPokemons.model.dataStruture.SimpleList;

public class TestCreateMatrix {

	private DataHandler dataHandler;
	private MyMatrix<Length, Latitude, Anime> matrix;

	public TestCreateMatrix() {
		dataHandler = new DataHandler();
		matrix = dataHandler.getMatrixAnimes();
		addAnimes();
		makePrint(dataHandler.getMatrixAnimes());
		SimpleList<Anime> list = matrix.makeTravelRows();
		this.showList(list);
	}

	private void addAnimes() {
		int id = 0;
		GeographicCoordinate coordinate = new GeographicCoordinate(new Length((short) 179, (byte) 59, (float) 59, 'E'),
				new Latitude((byte) 0, (byte) 0, (float) 0, 'N'));
		matrix.set(coordinate.getLength(), coordinate.getLatitude(), new Player(1000 + id++, "Camilo", coordinate));

		coordinate = new GeographicCoordinate(new Length((short) 0, (byte) 0, (float) 0, 'E'),
				new Latitude((byte) 80, (byte) 21, (float) 43.20, 'N'));
		matrix.set(coordinate.getLength(), coordinate.getLatitude(), new Player(1000 + id++, "Andres", coordinate));

		coordinate = new GeographicCoordinate(new Length((short) 0, (byte) 0, (float) 0, 'E'),
				new Latitude((byte) 19, (byte) 19, (float) 19, 'N'));
		matrix.set(coordinate.getLength(), coordinate.getLatitude(), new Player(1000 + id++, "Javier", coordinate));

//------------------------------------------------------------------------------------------------------------------------------------------

		coordinate = new GeographicCoordinate(new Length((short) 173, (byte) 9, (float) 7, 'W'),
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

	private void makePrint(MyMatrix<Length, Latitude, Anime> myMatrix) {
		System.out.println(
				"\n ____________________________________________________________________________________________");
		System.out.println("\nCOLUMNAS");
		Nodo<MyHeader<Length, Anime>> nodo = myMatrix.getColumns().getHead();
		MyHeader<Length, Anime> aux = null;
		while (nodo != null) {
			aux = nodo.getInfo();
			System.out.print(aux.getInfo() + "   ");
			nodo = nodo.getNext();
		}
		System.out.println(" ");
		System.out.println("HEADERS");
		nodo = myMatrix.getColumns().getHead();
		while (nodo != null) {
			aux = nodo.getInfo();
			MyCell<Anime> auxCell = nodo.getInfo().getFirstCell();
			System.out.print(aux.getInfo() + ": ");
			while (auxCell != null) {
				System.out.print("" + auxCell.getInfo() + ", ");
				auxCell = auxCell.getNextDown();
			}
			nodo = nodo.getNext();
		}

		System.out.println("\nFILAS ");
		Nodo<MyHeader<Latitude, Anime>> nodoTwo = myMatrix.getRows().getHead();
		MyHeader<Latitude, Anime> auxTwo = null;
		while (nodoTwo != null) {
			auxTwo = nodoTwo.getInfo();
			System.out.println(auxTwo.getInfo());
			nodoTwo = nodoTwo.getNext();
		}
		System.out.println("HEADERS");
		nodoTwo = myMatrix.getRows().getHead();
		while (nodoTwo != null) {
			auxTwo = nodoTwo.getInfo();
			MyCell<Anime> auxCell = nodoTwo.getInfo().getFirstCell();
			System.out.print(auxTwo.getInfo() + ": ");
			while (auxCell != null) {
				System.out.print("" + auxCell.getInfo() + ", ");
				auxCell = auxCell.getNextRigth();
			}
			nodoTwo = nodoTwo.getNext();
		}
		System.out.println("");
	}

	private static void showList(SimpleList<Anime> list) {
		Nodo<Anime> aux = list.getHead();
		while (aux != null) {
			System.out.println("" + aux.getInfo().getName());
			aux = aux.getNext();
		}
	}

	public static void main(String[] args) {
		new TestCreateMatrix();
	}
}
