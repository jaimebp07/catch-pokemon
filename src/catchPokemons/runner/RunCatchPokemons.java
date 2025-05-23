package catchPokemons.runner;

import catchPokemons.controller.Control;

public class RunCatchPokemons {

	public static void main(String[] args) {
		Control control = new Control();
		control.startThread();
	}
}
