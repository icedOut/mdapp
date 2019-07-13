package medecin.bui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSeparator;

public class FenetrePrincipale {

	private JFrame frame;

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
		
		FenetreConnexion fenetreConnexion = new FenetreConnexion();
		fenetreConnexion.setBounds(6, 6, 295, 158);
		frame.getContentPane().add(fenetreConnexion);
		
		FenetreDossier fenetreDossier = new FenetreDossier();
		fenetreDossier.setBounds(6, 180, 1100, 623);
		frame.getContentPane().add(fenetreDossier);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 165, 1100, 12);
		frame.getContentPane().add(separator);
	}
}
