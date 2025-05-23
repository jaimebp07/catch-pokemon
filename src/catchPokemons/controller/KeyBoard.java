package catchPokemons.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {

	private boolean[] keyBoard;
	protected boolean goUot;
	protected boolean goToLeft;
	protected boolean goToUp;
	protected boolean goToRight;
	protected boolean goToDown;
	protected boolean catchPokemon;
	protected boolean showBox;
	protected boolean once = false;
	private static final int maxNumberOfKeys = 600;

	public KeyBoard() {
		keyBoard = new boolean[maxNumberOfKeys];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() != 49) {
			keyBoard[e.getKeyCode()] = true;
		} else {
			once = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode() == 49) && (once)) {
			keyBoard[e.getKeyCode()] = true;
		} else {
			keyBoard[e.getKeyCode()] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void toUpdate() {
		goUot = keyBoard[KeyEvent.VK_ESCAPE];
		goToLeft = keyBoard[KeyEvent.VK_LEFT];
		goToUp = keyBoard[KeyEvent.VK_UP];
		goToRight = keyBoard[KeyEvent.VK_RIGHT];
		goToDown = keyBoard[KeyEvent.VK_DOWN];
		catchPokemon = keyBoard[KeyEvent.VK_ENTER];
		showBox = keyBoard[KeyEvent.VK_1];
	}
}