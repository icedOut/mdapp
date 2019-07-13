package medecin.bui;

import medecin.controlleur.ControlleurConnexion;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreConnexion extends JPanel implements ActionListener {
	private JTextField username;
	private JTextField password;
	private JLabel errorMsg = new JLabel("");
	/**
	 * Create the panel.
	 */
	public FenetreConnexion() {
		setLayout(null);
		
		username = new JTextField();
		username.setBounds(0, 28, 281, 26);
		add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(0, 94, 281, 26);
		add(password);
		password.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(6, 66, 125, 16);
		add(lblMotDePasse);
		
		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur");
		lblNomDutilisateur.setBounds(6, 6, 221, 16);
		add(lblNomDutilisateur);
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.setBounds(59, 120, 185, 16);
		btnSeConnecter.addActionListener(this);
		add(btnSeConnecter);
		

		errorMsg.setBounds(56, 140, 300, 16);
		add(errorMsg);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean connectionSuccess = ControlleurConnexion.connexion(username.getText(), password.getText());
		System.out.println(connectionSuccess);
		if(connectionSuccess){
			this.errorMsg.setText("");
			this.firePropertyChange("connect", false, true);
		}
		else{
			this.errorMsg.setText("Le mot de passe est invalide!");
		}
	}



	public void effaceUsername(){
		this.username.setText("");
	}

	public void effacePassword(){
		this.password.setText("");
	}
}
