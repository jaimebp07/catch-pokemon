package catchPokemons.view;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WindowSelectionPlayer extends JFrame {

	private ActionListener listenerControl;

	private JPanel contentPane;

	public WindowSelectionPlayer(ActionListener listenerControl) {
		this.listenerControl = listenerControl;
		assingCpmponents();
		createWindow();
	}

	private void assingCpmponents() {
//		setBounds(100, 100, 423, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		contentPane.add(createLblPlayer());
	}

	private void createWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 500);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JLabel createLblPlayer() {
		JLabel lblPlayer = new JLabel();
		lblPlayer.setIcon(new ImageIcon());
		lblPlayer.setBounds(62, 0, 175, 415);
		return lblPlayer;
	}
}
