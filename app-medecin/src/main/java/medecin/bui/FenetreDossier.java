package medecin.bui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JToggleButton;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

public class FenetreDossier extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public FenetreDossier() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(155, 1, 155, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCodeRamq = new JLabel("Code RAMQ à scanner");
		lblCodeRamq.setBounds(6, 6, 155, 16);
		add(lblCodeRamq);
		
		JButton btnChargerLeDossier = new JButton("Charger le dossier");
		btnChargerLeDossier.setBounds(322, 1, 148, 29);
		add(btnChargerLeDossier);
		
		FenetreCoordonnee fenetreCoordonnee = new FenetreCoordonnee();
		fenetreCoordonnee.setBounds(0, 34, 235, 67);
		add(fenetreCoordonnee);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(34, 34, 567, -2);
		add(separator);
		
		JList list = new JList();
		list.setBounds(569, 132, 525, 119);
		add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(6, 132, 525, 119);
		add(list_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(6, 277, 525, 182);
		add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(569, 277, 525, 182);
		add(textPane_1);
		
		JLabel lblAntecedentsMdicaux = new JLabel("Antecedents médicaux");
		lblAntecedentsMdicaux.setBounds(6, 113, 148, 16);
		add(lblAntecedentsMdicaux);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(543, 132, 14, 341);
		add(separator_1);
		
		JLabel lblVisitesMdicales = new JLabel("Visites médicales");
		lblVisitesMdicales.setBounds(569, 113, 117, 16);
		add(lblVisitesMdicales);

	}
}
