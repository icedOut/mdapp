package medecin;

import medecin.ui.*;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JSeparator;

public class FenetrePrincipale implements PropertyChangeListener {

	private JFrame frame;
	FenetreConnexion fenetreConnexion = new FenetreConnexion();
	FenetreDeconnexion fenetreDeconnexion = new FenetreDeconnexion();
	FenetreDossierMedical fenetreDossier = new FenetreDossierMedical();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipale window = new FenetrePrincipale();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetrePrincipale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		fenetreConnexion.setBounds(6, 6, 295, 158);
		fenetreConnexion.addPropertyChangeListener(this);
		frame.getContentPane().add(fenetreConnexion);

		fenetreDeconnexion.setBounds(506, 6, 295, 158);
		fenetreDeconnexion.addPropertyChangeListener(this);
		frame.getContentPane().add(fenetreDeconnexion);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 165, 1100, 12);
		frame.getContentPane().add(separator);

		fenetreDossier.setBounds(6, 180, 1100, 623);
		fenetreDossier.addPropertyChangeListener(this);

	}


	private void afficherFenetreDossier(){
		fenetreConnexion.effacePassword();
		fenetreConnexion.effaceUsername();
		frame.getContentPane().remove(fenetreConnexion);
		frame.getContentPane().add(fenetreDossier);
		frame.repaint();
	}

	private void cacherFenetreDossier(){
		frame.getContentPane().remove(fenetreDossier);
		frame.repaint();
	}



	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		String property = evt.getPropertyName();
		if(property.compareTo("connect") == 0 && (boolean)evt.getNewValue() == true){
			afficherFenetreDossier();
		}
		if(property.compareTo("disconnect") == 0 && (boolean)evt.getNewValue() == true){
			frame.getContentPane().add(fenetreConnexion);
			cacherFenetreDossier();
		}
	}
}
