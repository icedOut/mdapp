package medecin.bui;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class FenetreCoordonnee extends JPanel{

	/**
	 * Create the panel.
	 */
	public FenetreCoordonnee() {
		setLayout(null);
		
		JLabel lblNom = new JLabel("Nom: ");
		lblNom.setBounds(6, 6, 61, 16);
		add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom:");
		lblPrenom.setBounds(6, 27, 61, 16);
		add(lblPrenom);
		
		JLabel lblAddresse = new JLabel("Addresse: ");
		lblAddresse.setBounds(6, 46, 72, 16);
		add(lblAddresse);
		
		JLabel lblPlaceholderNom = new JLabel("placeholder nom");
		lblPlaceholderNom.setBounds(79, 6, 105, 16);
		add(lblPlaceholderNom);
		
		JLabel lblPlaceholderPrenom = new JLabel("placeholder prenom");
		lblPlaceholderPrenom.setBounds(79, 27, 140, 16);
		add(lblPlaceholderPrenom);
		
		JLabel lblPlaceholderAddresse = new JLabel("placeholder addresse");
		lblPlaceholderAddresse.setBounds(79, 46, 140, 16);
		add(lblPlaceholderAddresse);

	}

}
