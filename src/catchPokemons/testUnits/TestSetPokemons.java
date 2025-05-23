package catchPokemons.testUnits;

import catchPokemons.model.DAO.DataHandler;
import catchPokemons.model.Entity.Anime;
import catchPokemons.model.Entity.Latitude;
import catchPokemons.model.Entity.Length;
import catchPokemons.model.dataStruture.MyMatrix;
import catchPokemons.model.dataStruture.Nodo;
import catchPokemons.model.dataStruture.SimpleList;

public class TestSetPokemons {

	private DataHandler dataHandler;

	public TestSetPokemons() {
		dataHandler = new DataHandler();
		MyMatrix<Length, Latitude, Anime> matrix = dataHandler.getMatrixAnimes();
		dataHandler.addPokemons(4);
		SimpleList<Anime> list = matrix.makeTravelRows();
		showList(list);
	}

	private void showList(SimpleList<Anime> list) {
		Nodo<Anime> aux = list.getHead();
		while (aux != null) {
			System.out.println("" + aux.getInfo().toString());
			aux = aux.getNext();
		}
	}

	public static void main(String[] args) {
		new TestSetPokemons();
	}
}