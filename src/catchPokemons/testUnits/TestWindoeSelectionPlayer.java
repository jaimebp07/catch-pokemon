package catchPokemons.testUnits;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import catchPokemons.view.WindowSelectionPlayer;

public class TestWindoeSelectionPlayer implements ActionListener {

	public TestWindoeSelectionPlayer() {
		new WindowSelectionPlayer(this);
	}

	public static void main(String[] args) {
		new TestWindoeSelectionPlayer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
