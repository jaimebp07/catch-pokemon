package catchPokemons.controller;

import java.util.Timer;
import java.util.TimerTask;

import catchPokemons.model.DAO.DataHandler;
import catchPokemons.model.Entity.Latitude;
import catchPokemons.view.Screen;

public class Control implements Runnable {

	private Screen screen;
	private DataHandler dataHandler;
	private Thread thread;
	private boolean isWorking;
	private static final int timeUpdatePokemons = 15000;
	private static KeyBoard keyBoard;
	private String namePlayer;
	private int colorPayer;
	private int xSprite = 0;
	private int ySprite;
	private byte stepShift = 0;
	private byte frequencyOfMoved = 0;
	private int yPlayer = 384;
	private int xPlayer = 683;

	public Control() {
		dataHandler = new DataHandler();
		keyBoard = new KeyBoard();
		startInterface();
		startThread();
		toUpdateComponets();
	}

	private void startInterface() {
		createPlayer();
		screen = new Screen(dataHandler.getPlayer(), namePlayer, colorPayer);
		screen.addKeyListener(keyBoard);
		screen.drawPokemons(dataHandler.getListOfPokemos());
	}

	private void createPlayer() {
		namePlayer = "P_boy_1.png";
		colorPayer = 4;
		dataHandler.createPlayer(1001, "Andres");
	}

	public synchronized void startThread() {
		thread = new Thread(this);
		thread.start();
		isWorking = true;
	}

	private void toUpdateComponets() {
		Timer timer = new Timer();
		TimerTask homework = new TimerTask() {
			@Override
			public void run() {
				toUpdatePokemons();
//				createPlayer();
			}
		};
		timer.schedule(homework, 3000, timeUpdatePokemons);
	}

	/**
	 * se encarga de realizar actualizaciones de pokemones tanto en el modelo como
	 * en la vista
	 */
	private void toUpdatePokemons() {
		dataHandler.toUpdateLocationPokemons();
		screen.drawPokemons(dataHandler.getListOfPokemos());
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (isWorking) {
			keyboardReader();
			try {
				thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void keyboardReader() {
		frequencyOfMoved++;
		keyBoard.toUpdate();
		if (frequencyOfMoved > 0) {
			if (keyBoard.goToUp) {
				if (screen.isWithinTheWorld(xPlayer, yPlayer - 3))
					goToUp();
			}
			if (keyBoard.goToLeft) {
				if (screen.isWithinTheWorld(xPlayer - 3, yPlayer))
					goToLeft();
			}
			if (keyBoard.goToRight) {
				if (screen.isWithinTheWorld(xPlayer + 3, yPlayer))
					goToRight();
			}
			if (keyBoard.goToDown) {
				if (screen.isWithinTheWorld(xPlayer, yPlayer + 3))
					goToDown();
			}
			frequencyOfMoved = 0;
		}
		if (keyBoard.showBox && keyBoard.once) {
			keyBoard.once = false;
			keyBoard.showBox = false;
			screen.showDialogSeePokemons(dataHandler.getPlayer());
		}
		if (keyBoard.catchPokemon) {
			Latitude latitude = screen.convertMetersToLatitude(screen.convertPxToMetersY(yPlayer));
			dataHandler.catchPokemon(screen.convertMetersToLength(screen.convertPxToMetersX(xPlayer, latitude)),
					latitude);
			screen.drawPokemons(dataHandler.getListOfPokemos());
		}
		if (keyBoard.goUot) {
			System.exit(0);
		}
	}

	private void goToDown() {
		yPlayer = yPlayer + 3;
		ySprite = 0;
		toUpdateStepShift();
		screen.paintPlayer(xPlayer, yPlayer, xSprite, ySprite);
	}

	private void goToRight() {
		xPlayer = xPlayer + 3;
		ySprite = 2;
		toUpdateStepShift();
		screen.paintPlayer(xPlayer, yPlayer, xSprite, ySprite);
	}

	private void goToLeft() {
		xPlayer = xPlayer - 3;
		ySprite = 1;
		toUpdateStepShift();
		screen.paintPlayer(xPlayer, yPlayer, xSprite, ySprite);
	}

	private void goToUp() {
		yPlayer = yPlayer - 3;
		ySprite = 3;
		toUpdateStepShift();
		screen.paintPlayer(xPlayer, yPlayer, xSprite, ySprite);
	}

	private void toUpdateStepShift() {
		stepShift++;
		switch (stepShift) {
		case 0:
			xSprite = 0;
			break;
		case 1:
			xSprite = 1;
			break;
		case 2:
			xSprite = 2;
			break;
		default:
			xSprite = 1;
			stepShift = -1;
			break;
		}
	}
}