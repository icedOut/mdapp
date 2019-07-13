package medecin.bui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class FenetreConnexion extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public FenetreConnexion() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 28, 281, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(0, 94, 281, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(6, 66, 125, 16);
		add(lblMotDePasse);
		
		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur");
		lblNomDutilisateur.setBounds(6, 6, 221, 16);
		add(lblNomDutilisateur);
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.setBounds(59, 132, 185, 16);
		add(btnSeConnecter);
		
		JLabel label = new JLabel("");
		label.setBounds(119, 160, 61, 16);
		add(label);

	}
}
