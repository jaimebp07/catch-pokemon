package catchPokemons.model.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import catchPokemons.model.dataStruture.MyQueue;

public class FileOperations {

	public static MyQueue<String> read() {
		MyQueue<String> list = new MyQueue<String>();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File("means/files/pokemons.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				list.offer(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
}

//http://es.pokemon.wikia.com/wiki/Lista_de_Pok%C3%A9mon

//Users
//https://www.3djuegos.com/comunidad-foros/tema/40377487/0/hilo-unico-recopilacion-sprites-rpg-maker-mv/