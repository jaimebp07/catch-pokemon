package catchPokemons.model.DAO;

import java.util.Comparator;

import catchPokemons.model.Entity.Anime;
import catchPokemons.model.Entity.GeographicCoordinate;
import catchPokemons.model.Entity.Latitude;
import catchPokemons.model.Entity.Length;
import catchPokemons.model.Entity.Player;
import catchPokemons.model.Entity.Pokemon;
import catchPokemons.model.dataStruture.MyHeader;
import catchPokemons.model.dataStruture.MyMatrix;
import catchPokemons.model.dataStruture.MyQueue;
import catchPokemons.model.dataStruture.Nodo;
import catchPokemons.model.dataStruture.SimpleList;

public class DataHandler {

	private MyMatrix<Length, Latitude, Anime> myMatrixAnime;
	private MyQueue<String> pokemonsFiles;
	private MyQueue<Integer> mQIdPokemonsToUpate;
	private Player player;

	public DataHandler() {
		loadDataStructure();
	}

	private void loadDataStructure() {
		mQIdPokemonsToUpate = new MyQueue<>();
		createMatrix();
		this.addPokemons(7);
	}

	private void createMatrix() {
		myMatrixAnime = new MyMatrix<>(new Comparator<MyHeader<Length, Anime>>() {
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
	}

	/**
	 * retorna en una lista los pokemones existentes en la matrix
	 * 
	 * @return
	 */
	public SimpleList<Pokemon> getListOfPokemos() {
		SimpleList<Pokemon> listPokemons = new SimpleList<>();
		Nodo<Anime> nodo = myMatrixAnime.makeTravelRows().getHead();
		while (nodo != null) {
			if (isPokemon(nodo.getInfo().getClass().getName()) == true)
				listPokemons.add((Pokemon) nodo.getInfo());
			nodo = nodo.getNext();
		}
		return listPokemons;
	}

	/**
	 * retorna en una lista los usuarios existentes en la matris
	 * 
	 * @return
	 */
	public SimpleList<Player> getListOfUsers() {
		SimpleList<Player> listUsers = new SimpleList<>();
		Nodo<Anime> nodo = myMatrixAnime.makeTravelRows().getHead();
		while (nodo != null) {
			if (isPokemon(nodo.getInfo().getClass().getName()) == false)
				listUsers.add((Player) nodo.getInfo());
			nodo = nodo.getNext();
		}
		return listUsers;
	}

//	/**
//	 * calcula el pokemon mas cercano
//	 * 
//	 * @param user
//	 * @return
//	 */
//	public Anime calulatePokemonNearest(Anime user) {
//		Nodo<Anime> nodoAnime = this.myMatrixAnime.makeTravelColumns().getHead();
//		while (isPokemon(nodoAnime.getInfo().getClass().getName()) == false) {
//			nodoAnime = nodoAnime.getNext();
//		}
//		Anime nearestPokemon = nodoAnime.getInfo();
//		while (nodoAnime != null) {
//			if (isPokemon(nodoAnime.getInfo().getClass().getName())) {
//				double a = calculateDistance(nodoAnime.getInfo(), user);
//				double b = calculateDistance(nearestPokemon, user);
//				if (a < b) {
//					nearestPokemon = nodoAnime.getInfo();
//				}
//			}
//			nodoAnime = nodoAnime.getNext();
//		}
//		return nearestPokemon;
//	}

	/**
	 * verifica si el codigo pertenece a un pokemo o un usuario
	 * 
	 * @param string
	 * @return
	 */
	private boolean isPokemon(String className) {
		if ((int) className.charAt(className.length() - 1) == 110) {
			return true;
		}
		return false;
	}

//	/**
//	 * calcula la diatancia que hay entre el usuario, y un pokemon
//	 * 
//	 * @param anime
//	 * @param user
//	 * @return
//	 */
//	private double calculateDistance(Anime pokemon, Anime user) {
//		int x = this.convertToPixelesX(pokemon.getCoordinate().getLatitude(), pokemon.getCoordinate().getLength())
//				- this.convertToPixelesX(user.getCoordinate().getLatitude(), user.getCoordinate().getLength());
//		int y = this.convertToPixelesY(pokemon.getCoordinate().getLatitude())
//				- this.convertToPixelesY(user.getCoordinate().getLatitude());
//		return (Math.pow(x, 2) + Math.pow(y, 2));
//	}

	/**
	 * retorna la pocion en el eje x, comvirtiendo la coordenada de longitud a
	 * metros
	 * 
	 * @param latitude
	 * 
	 * @param length
	 * 
	 * @return
	 */
	public int positionXInMeters(Length length, Latitude latitude) {
		int result = (int) (Math.cos(latitude.getDegrees() * ((3.1416 * 2) / 360))
				* ((length.getDegrees() * 111325) + (length.getMinutes() * 1855.42) + (length.getSeconds() * 30.92)));
		if (length.getDirection() == 'W')
			result = (result * (-1));
		return result;
	}

	/**
	 * retorna la pocion en el eje y, comvirtiendo la coordenada de latitud a metros
	 * 
	 * @param locationLatitude
	 * 
	 * @return
	 */
	public int positionYInMeters(Latitude locationLatitude) {
		int result = (int) ((locationLatitude.getDegrees() * 111325) + (locationLatitude.getMinutes() * 1855.42)
				+ (locationLatitude.getSeconds() * 30.92));
		if (locationLatitude.getDirection() == 'N')
			result = (result * (-1));
		return result;
	}

//	/**
//	 * convierte la longitud a pixeles para dibujarlo en el area de juego
//	 * 
//	 * @param latitude
//	 * @param length
//	 * 
//	 * @return
//	 */
//	public int convertToPixelesX(Latitude latitude, Length length) {
//		double pixeles = ((670) / ((111325 * 180) + (1855.42 * 60) + (30.92 * 60)))
//				* positionXInMeters(latitude, length);
//		return (int) (683 + pixeles);
//	}

	/**
	 * convierte la latitud a pixeles para dibujarlo en el area de juego
	 * 
	 * @param latitude
	 * 
	 * @return
	 */
	public int convertToPixelesY(Latitude latitude) {
		double pixeles = ((335) / ((111325 * 90) + (1855.42 * 60) + (30.92 * 60))) * positionYInMeters(latitude);
		return (int) (384 + pixeles);
	}

	/**
	 * Retorna la matriz
	 * 
	 * @return
	 */
	public MyMatrix<Length, Latitude, Anime> getMatrixAnimes() {
		return myMatrixAnime;
	}

	/**
	 * Asigna a la matrix local una matrix conocida para realizar casos de pruevas
	 * 
	 * @param matrix
	 */
	public void setMatrixAnimes(MyMatrix<Length, Latitude, Anime> matrix) {
		this.myMatrixAnime = matrix;
	}

	/**
	 * Agrega los pokemones a la matrix
	 * 
	 * @param numbersOfPokemos
	 */
	public void addPokemons(int numbersOfPokemos) {
		pokemonsFiles = FileOperations.read();
		while (numbersOfPokemos > 0) {
			GeographicCoordinate gC = new GeographicCoordinate();
			pokemonsFiles.offer(pokemonsFiles.peek());
			String[] s = pokemonsFiles.remove().split(",");
			myMatrixAnime.set(gC.getLength(), gC.getLatitude(),
					new Pokemon(Integer.parseInt(s[0]), s[1], gC, Byte.parseByte(s[2]), Byte.parseByte(s[3])));
			mQIdPokemonsToUpate.offer(Integer.parseInt(s[0]));
			numbersOfPokemos--;
		}
	}

	/**
	 * Actulaza a una pocicion aleatoria la ubicacion de un pokon si este existe
	 */
	public void toUpdateLocationPokemons() {
		int idPokemon = mQIdPokemonsToUpate.remove();
		Anime pokemon = findPokemon(idPokemon);
		if (pokemon != null) {
			myMatrixAnime.remove(pokemon.getCoordinate().getLength(), pokemon.getCoordinate().getLatitude());
			pokemon.setGeologicalCoordinate(new GeographicCoordinate());
			myMatrixAnime.set(pokemon.getCoordinate().getLength(), pokemon.getCoordinate().getLatitude(), pokemon);
			mQIdPokemonsToUpate.offer(idPokemon);
		}
	}

	/**
	 * Busca un pokemon de acuerdo al id recibido por parametro
	 * 
	 * @param idPokemon
	 * @return
	 */
	@SuppressWarnings("null")
	public Anime findPokemon(int idPokemon) {
		Nodo<Anime> nodoAnime = myMatrixAnime.makeTravelRows().getHead();
		while (nodoAnime != null) {
			if ((nodoAnime.getInfo().getId()) == idPokemon)
				return nodoAnime.getInfo();
			nodoAnime = nodoAnime.getNext();
		}
		return null;
	}

	/**
	 * crea el usuario y lo agrega a la matriz
	 * 
	 * @param id
	 * @param name
	 */
	public void createPlayer(int id, String name) {
		GeographicCoordinate coordinate = new GeographicCoordinate();
		player = new Player(id, name, coordinate);
		myMatrixAnime.set(coordinate.getLength(), coordinate.getLatitude(), player);
	}

	/**
	 * Retorna el usuario
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * se encarga de invocar los metodos que realizan las diferentes tares para
	 * atrapar el pokemon
	 * 
	 * @param playerLength
	 * @param palyerLatitude
	 */
	public void catchPokemon(Length playerLength, Latitude palyerLatitude) {
		Nodo<Pokemon> nodoPokemon = getListOfPokemos().getHead();
		int distance = 445300;
		Pokemon pokemon = null;
		while (nodoPokemon != null && distance > 0) {
			int distance2 = calculateDistance(playerLength, palyerLatitude, nodoPokemon.getInfo());
			if (distance > distance2) {
				pokemon = nodoPokemon.getInfo();
				distance = distance2;
			}
			nodoPokemon = nodoPokemon.getNext();
		}
		if (pokemon != null) {
			reemplacePokemon(pokemon);
			player.setPokemon(pokemon);
		}
	}

	/**
	 * remplaza el pokemon atrapado en el matriz
	 * 
	 * @param pokemon
	 */
	private void reemplacePokemon(Pokemon pokemon) {
		myMatrixAnime.remove(pokemon.getCoordinate().getLength(), pokemon.getCoordinate().getLatitude());
		mQIdPokemonsToUpateToUpdate();
		GeographicCoordinate gC = new GeographicCoordinate();
		pokemonsFiles.offer(pokemonsFiles.peek());
		String[] s = pokemonsFiles.remove().split(",");
		myMatrixAnime.set(gC.getLength(), gC.getLatitude(),
				new Pokemon(Integer.parseInt(s[0]), s[1], gC, Byte.parseByte(s[2]), Byte.parseByte(s[3])));
		mQIdPokemonsToUpate.offer(Integer.parseInt(s[0]));
	}

	private void mQIdPokemonsToUpateToUpdate() {
		for (int i = 0; i < mQIdPokemonsToUpate.getTam(); i++) {
			mQIdPokemonsToUpate.remove();
		}
		Nodo<Pokemon> nodoPokemons = getListOfPokemos().getHead();
		while (nodoPokemons != null) {
			mQIdPokemonsToUpate.offer(nodoPokemons.getInfo().getId());
			nodoPokemons = nodoPokemons.getNext();
		}
	}

	/**
	 * calcula la distancia existente entre el jugador y en pokemon
	 * 
	 * @param playerLength
	 * @param palyerLatitude
	 * @param pokemon
	 * @return
	 */
	private int calculateDistance(Length playerLength, Latitude palyerLatitude, Pokemon pokemon) {
		int a = positionXInMeters(playerLength, palyerLatitude)
				- positionXInMeters(pokemon.getCoordinate().getLength(), pokemon.getCoordinate().getLatitude());
		int b = positionYInMeters(palyerLatitude) - positionYInMeters(pokemon.getCoordinate().getLatitude());
		return (int) Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
	}
}