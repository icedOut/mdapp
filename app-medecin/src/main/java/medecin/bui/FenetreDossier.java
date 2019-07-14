package medecin.bui;

import medecin.controlleur.ControllerModifierDossier;
import medecin.controlleur.ControlleurChargerDossier;
import medecin.models.AntecedentMedical;
import medecin.models.DossierMedical;
import medecin.models.VisiteMedicale;
import medecin.models.VisitePrototype;
import medecin.utils.Config;
import medecin.utils.DateFormatter;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FenetreDossier extends JPanel {

	FenetreCoordonnee fenetreCoordonnee = new FenetreCoordonnee();
	DossierMedical dossierActif;


	private JTextPane visiteNotesField = new JTextPane();
	private JTextPane visiteResumeField = new JTextPane();

	JLabel antecedentDateDebutLabel;
	JLabel antecedentDateFinLabel;

	private JList antecedentListe = new JList();
	private JList visiteList = new JList();

	private JTextField codeRAMQTextField;
	private JTextField visiteDiagnosticField;
	private JTextField visiteTraitementField;
	private JTextField visiteMedicamentField;
	private JTextField antecedentDiagnosticField;
	private JTextField antecedentTraitementField;
	private JTextField antecedentMedicamentField;


	private JButton addVisite = new JButton();
	private JButton addAntecedent = new JButton();
	private JButton annulerModification = new JButton();

	private int currentVisiteIdx = 0;
	private int currentAntecedetIdx = 0;




	/**
	 * Create the panel.
	 */
	public FenetreDossier() {
		setLayout(null);
		afficherComponentAntecedent();
		afficherComponentVisite();

		codeRAMQTextField = new JTextField();
		codeRAMQTextField.setBounds(155, 1, 155, 26);
		add(codeRAMQTextField);
		codeRAMQTextField.setColumns(10);

		JLabel lblCodeRamq = new JLabel("Code RAMQ à scanner");
		lblCodeRamq.setBounds(6, 6, 155, 16);
		add(lblCodeRamq);

		JLabel visiteMedicalesLabel = new JLabel("Visites médicales");
		visiteMedicalesLabel.setBounds(6, 113, 148, 16);
		add(visiteMedicalesLabel);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(543, 132, 14, 341);
		add(separator_1);

		JLabel antecedentLabel = new JLabel("Antecedent médicaux");
		antecedentLabel.setBounds(569, 113, 148, 16);
		add(antecedentLabel);



		JButton btnChargerLeDossier = new JButton("Charger le dossier");
		btnChargerLeDossier.setBounds(322, 1, 148, 29);
		btnChargerLeDossier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DossierMedical dossier = ControlleurChargerDossier.chargerDossier(codeRAMQTextField.getText());
				if(dossier == null) erreurChargerDossier(codeRAMQTextField.getText());
				dossierActif = dossier;
				afficherDossier();
			}
		});
		add(btnChargerLeDossier);


		fenetreCoordonnee.setBounds(0, 34, 235, 67);
		add(fenetreCoordonnee);

		JSeparator separator = new JSeparator();
		separator.setBounds(34, 34, 567, -2);
		add(separator);


		annulerModification.setEnabled(false);
		annulerModification.setText("Annuler les dernières modifications");
		annulerModification.setForeground(Color.RED);
		annulerModification.setBounds(300, 575, 400, 40);
		annulerModification.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dossierActif = ControllerModifierDossier.annnulerDerniereModification();
				afficherDossier();
			}
		});
		add(annulerModification);






	}


	public void afficherDossier(){
		if(dossierActif == null){
			addAntecedent.setEnabled(false);
			addVisite.setEnabled(false);
			annulerModification.setEnabled(false);
		}
		else{
			addAntecedent.setEnabled(true);
			addVisite.setEnabled(true);
			annulerModification.setEnabled(true);
		}




		fenetreCoordonnee.setPrenom(dossierActif.patient.prenom);
		fenetreCoordonnee.setNom(dossierActif.patient.nom);
		fenetreCoordonnee.setAddresse(dossierActif.patient.coordonnee.adresse.ville);
		afficherListeVisites(dossierActif.visites);
		afficherListeAntecedents(dossierActif.antecedents);
		System.out.println(currentAntecedetIdx);
		System.out.println(currentVisiteIdx);
		if(dossierActif.antecedents.size() > currentAntecedetIdx && currentAntecedetIdx >= 0){
			afficherAntecedentSelectionne(dossierActif.antecedents.get(currentAntecedetIdx));
		}
		if(dossierActif.visites.size() > currentVisiteIdx && currentVisiteIdx >= 0){
			afficherVisiteSelectionnee(dossierActif.visites.get(currentVisiteIdx));
		}
	}

	public void erreurChargerDossier(String codeRAMQ){
		System.out.println("Erreur pour le chargement du dossier" + codeRAMQ);
	}

	public void afficherListeVisites(List<VisiteMedicale> visites){

		this.visiteList.setListData(visites.toArray());
	}

	public void afficherListeAntecedents(List<AntecedentMedical> antecedents){
		this.antecedentListe.setListData(antecedents.toArray());
	}

	public void afficherVisiteSelectionnee(VisiteMedicale visite){
		visiteNotesField.setText(visite.notes);
		visiteResumeField.setText(visite.resume);
		visiteDiagnosticField.setText(visite.diagnostic);
		visiteTraitementField.setText(visite.traitement.nomTraitement);
		visiteMedicamentField.setText(visite.traitement.medicament);
	}

	public void afficherAntecedentSelectionne(AntecedentMedical antecedent){
		antecedentDiagnosticField.setText(antecedent.diagnostic);
		antecedentTraitementField.setText(antecedent.traitement.nomTraitement);
		antecedentMedicamentField.setText(antecedent.traitement.medicament);
		antecedentDateDebutLabel.setText(DateFormatter.dateToString(antecedent.debutMaladie));
		antecedentDateFinLabel.setText(DateFormatter.dateToString(antecedent.finMaladie));
	}


	public void afficherComponentVisite(){

		afficherVisiteLabel();


		visiteList.setBounds(6, 132, 525, 119);
		visiteList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try{
					if(visiteList.getSelectedIndex() < 0) return;
					currentVisiteIdx = visiteList.getSelectedIndex();
					System.out.println("Visite selectionne est " + currentVisiteIdx);
					afficherVisiteSelectionnee(dossierActif.visites.get(currentVisiteIdx));
				}
				catch(Exception ex){
					System.out.println(ex.toString());
				}

			}
		});
		add(visiteList);


		visiteNotesField.setBounds(6, 277, 525, 67);
		visiteNotesField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				dossierActif.visites.get(currentVisiteIdx).notes = visiteNotesField.getText();
				dossierActif = ControllerModifierDossier.modifierDossier(dossierActif);
				afficherVisiteSelectionnee(dossierActif.visites.get(currentVisiteIdx));
			}
		});
		add(visiteNotesField);



		visiteResumeField.setBounds(6, 374, 525, 67);
		visiteResumeField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				dossierActif.visites.get(currentVisiteIdx).resume = visiteResumeField.getText();
				dossierActif = ControllerModifierDossier.modifierDossier(dossierActif);
				afficherVisiteSelectionnee(dossierActif.visites.get(currentVisiteIdx));
			}
		});
		add(visiteResumeField);



		visiteDiagnosticField = new JTextField();
		visiteDiagnosticField.setBounds(101, 453, 430, 20);
		visiteDiagnosticField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				dossierActif.visites.get(currentVisiteIdx).diagnostic = visiteDiagnosticField.getText();
				dossierActif = ControllerModifierDossier.modifierDossier(dossierActif);
				afficherVisiteSelectionnee(dossierActif.visites.get(currentVisiteIdx));
			}
		});
		add(visiteDiagnosticField);
		visiteDiagnosticField.setColumns(10);


		visiteTraitementField = new JTextField();
		visiteTraitementField.setBounds(101, 485, 430, 27);
		visiteTraitementField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				dossierActif.visites.get(currentVisiteIdx).traitement.nomTraitement = visiteTraitementField.getText();
				dossierActif = ControllerModifierDossier.modifierDossier(dossierActif);
				afficherVisiteSelectionnee(dossierActif.visites.get(currentVisiteIdx));
			}
		});
		add(visiteTraitementField);
		visiteTraitementField.setColumns(10);

		visiteMedicamentField = new JTextField();
		visiteMedicamentField.setBounds(101, 522, 430, 26);
		visiteMedicamentField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				dossierActif.visites.get(currentVisiteIdx).traitement.medicament = visiteMedicamentField.getText();
				dossierActif = ControllerModifierDossier.modifierDossier(dossierActif);
				afficherVisiteSelectionnee(dossierActif.visites.get(currentVisiteIdx));
			}
		});
		add(visiteMedicamentField);
		visiteMedicamentField.setColumns(10);

		addVisite.setEnabled(false);
		addVisite.setText("Ajouter une visite");
		addVisite.setBounds(383, 113, 148, 16);
		addVisite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dossierActif = ControllerModifierDossier.ajouterVisite();
				afficherDossier();
			}
		});
		add(addVisite);
	}

	public void afficherVisiteLabel(){
		JLabel visiteNotesLabel = new JLabel("Notes");
		visiteNotesLabel.setBounds(6, 259, 61, 16);
		add(visiteNotesLabel);

		JLabel visiteResumeLabel = new JLabel("Resume");
		visiteResumeLabel.setBounds(6, 356, 61, 16);
		add(visiteResumeLabel);

		JLabel visiteDiagnosticLabel = new JLabel("Diagnostic");
		visiteDiagnosticLabel.setBounds(6, 457, 95, 16);
		add(visiteDiagnosticLabel);

		JLabel visiteTraitementLabel = new JLabel("Traitement");
		visiteTraitementLabel.setBounds(6, 496, 95, 16);
		add(visiteTraitementLabel);

		JLabel visiteMedicamentLabel = new JLabel("Medicament");
		visiteMedicamentLabel.setBounds(6, 527, 76, 16);
		add(visiteMedicamentLabel);
	}


	public void afficherComponentAntecedent(){

		JLabel lblDbutDeLa = new JLabel("Début de la maladie");
		lblDbutDeLa.setBounds(569, 370, 148, 16);
		add(lblDbutDeLa);

		JLabel lblFinDeLa = new JLabel("Fin de la maladie");
		lblFinDeLa.setBounds(569, 425, 132, 16);
		add(lblFinDeLa);

		antecedentDateDebutLabel = new JLabel("");
		antecedentDateDebutLabel.setBounds(727, 370, 194, 16);
		add(antecedentDateDebutLabel);

		antecedentDateFinLabel = new JLabel("");
		antecedentDateFinLabel.setBounds(727, 425, 194, 16);
		add(antecedentDateFinLabel);

		JLabel antecedentDiagnosticLabel = new JLabel("Diagnostic");
		antecedentDiagnosticLabel.setBounds(569, 457, 95, 16);
		add(antecedentDiagnosticLabel);


		JLabel antecedentTraitementLabel = new JLabel("Traitement");
		antecedentTraitementLabel.setBounds(569, 496, 95, 16);
		add(antecedentTraitementLabel);

		JLabel antecedentMedicamentLabel = new JLabel("Medicament");
		antecedentMedicamentLabel.setBounds(569, 527, 76, 16);
		add(antecedentMedicamentLabel);


		antecedentListe.setBounds(569, 132, 525, 205);
		antecedentListe.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try{
					if(antecedentListe.getSelectedIndex() < 0) return;
					currentAntecedetIdx = antecedentListe.getSelectedIndex();
					afficherAntecedentSelectionne(dossierActif.antecedents.get(currentAntecedetIdx));
				}
				catch(Exception ex){
					System.out.println(ex.toString());
				}

			}
		});
		add(antecedentListe);


		antecedentDiagnosticField = new JTextField();
		antecedentDiagnosticField.setColumns(10);
		antecedentDiagnosticField.setBounds(664, 453, 430, 20);
		antecedentDiagnosticField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				dossierActif.antecedents.get(currentAntecedetIdx).diagnostic = antecedentDiagnosticField.getText();
				dossierActif = ControllerModifierDossier.modifierDossier(dossierActif);
				afficherAntecedentSelectionne(dossierActif.antecedents.get(currentVisiteIdx));
			}
		});
		add(antecedentDiagnosticField);

		antecedentTraitementField = new JTextField();
		antecedentTraitementField.setColumns(10);
		antecedentTraitementField.setBounds(664, 485, 430, 27);
		antecedentTraitementField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				dossierActif.antecedents.get(currentAntecedetIdx).traitement.nomTraitement = antecedentTraitementField.getText();
				dossierActif = ControllerModifierDossier.modifierDossier(dossierActif);
				afficherAntecedentSelectionne(dossierActif.antecedents.get(currentVisiteIdx));
			}
		});
		add(antecedentTraitementField);

		antecedentMedicamentField = new JTextField();
		antecedentMedicamentField.setColumns(10);
		antecedentMedicamentField.setBounds(664, 522, 430, 26);
		antecedentMedicamentField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				dossierActif.antecedents.get(currentAntecedetIdx).traitement.medicament = antecedentMedicamentField.getText();
				dossierActif = ControllerModifierDossier.modifierDossier(dossierActif);
				afficherAntecedentSelectionne(dossierActif.antecedents.get(currentVisiteIdx));
			}
		});
		add(antecedentMedicamentField);


		addAntecedent.setEnabled(false);
		addAntecedent.setText("Ajouter un antécédent");
		addAntecedent.setBounds(923, 113, 148, 16);
		addAntecedent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dossierActif = ControllerModifierDossier.ajouterAntecedent();
				afficherDossier();
			}
		});
		add(addAntecedent);

	}

}
