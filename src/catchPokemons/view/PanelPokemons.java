package catchPokemons.view;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import catchPokemons.model.Entity.Pokemon;
import catchPokemons.model.dataStruture.Nodo;
import catchPokemons.model.dataStruture.SimpleList;

@SuppressWarnings("serial")
public class PanelPokemons extends JPanel {

	JPanel containerPokemons = new JPanel(new GridLayout(1, 3));

	public PanelPokemons(SimpleList<Pokemon> simpleListPokemons) {
		int rows = defineRows(findSizeSimpleList(simpleListPokemons)) + 1;
		if (rows < 4)
			rows = 4;
		setLayout(new GridLayout(rows, 1));
		ArrayList<JPanel> listPanels = new ArrayList<>();
		Nodo<Pokemon> nodo = simpleListPokemons.getHead();
		byte count = 0;
		while (nodo != null) {
			if (count == 0)
				containerPokemons = new JPanel(new GridLayout(1, 3));
			count++;
			containerPokemons.add(new PanelInfoPokemon(nodo.getInfo()));
			if (count > 2) {
				listPanels.add(containerPokemons);
				add(listPanels.get(listPanels.size() - 1));
				containerPokemons = null;
				count = 0;
			}
			nodo = nodo.getNext();
		}
		listPanels.add(containerPokemons);
		if (containerPokemons != null && (listPanels.size() > 0)) {
			add(listPanels.get(listPanels.size() - 1));
		}
		repaint();
	}

	private short defineRows(short sizeSimpleList) {
		short s = (short) (sizeSimpleList / 3);
		return s;
	}

	private short findSizeSimpleList(SimpleList<Pokemon> simpleListPokemons) {
		short count = 0;
		Nodo<Pokemon> nodo = simpleListPokemons.getHead();
		while (nodo != null) {
			count++;
			nodo = nodo.getNext();
		}
		return count;
	}
}
