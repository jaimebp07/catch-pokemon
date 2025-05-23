package catchPokemons.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class rr extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rr frame = new rr();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public rr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 423, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea txtrNombre = new JTextArea();
		txtrNombre.setBounds(74, 104, 77, 22);
		contentPane.add(txtrNombre);

		textField = new JTextField();
		textField.setBounds(175, 132, 141, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblName = new JLabel("name");
		lblName.setBounds(96, 189, 77, 27);
		contentPane.add(lblName);

		textField_1 = new JTextField();
		textField_1.setBounds(209, 189, 86, 27);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
