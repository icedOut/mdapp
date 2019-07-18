package medecin.ui;

import medecin.controlleur.ControllerModifierDossier;
import medecin.controlleur.ControlleurChargerDossier;
import medecin.models.AntecedentMedical;
import medecin.models.DossierMedical;
import medecin.models.VisiteMedicale;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class FenetreDossierMedical extends JPanel {

  FenetreCoordonnee fenetreCoordonnee = new FenetreCoordonnee();
  FenetreListeAntecedent fenetreListeAntecedent = new FenetreListeAntecedent();
  FenetreListeVisites fenetreListeVisite = new FenetreListeVisites();
  DossierMedical dossierActif;

  private JTextField codeRAMQTextField;

  private JButton annulerModification = new JButton();


  /**
   * Create the panel.
   */
  public FenetreDossierMedical() {
    setLayout(null);


    codeRAMQTextField = new JTextField();
    codeRAMQTextField.setBounds(155, 1, 155, 26);
    add(codeRAMQTextField);
    codeRAMQTextField.setColumns(10);

    JLabel lblCodeRamq = new JLabel("Code RAMQ à scanner");
    lblCodeRamq.setBounds(6, 6, 155, 16);
    add(lblCodeRamq);


    JButton btnChargerLeDossier = new JButton("Charger le dossier");
    btnChargerLeDossier.setBounds(322, 1, 220, 38);
    btnChargerLeDossier.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        DossierMedical dossier = ControlleurChargerDossier.chargerDossier(codeRAMQTextField.getText());
        if (dossier == null) erreurChargerDossier(codeRAMQTextField.getText());
        dossierActif = dossier;
        afficherDossier();
      }
    });
    add(btnChargerLeDossier);


    fenetreCoordonnee.setBounds(0, 34, 235, 67);
    add(fenetreCoordonnee);


    afficherBoutonAnnulerModification();
    afficherListeVisite();
    afficherListeAntecedent();

  }


  private void afficherListeAntecedent() {
    fenetreListeAntecedent.setBounds(480, 125, 450, 500);
    fenetreListeAntecedent.addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().compareTo("addAntecedent") == 0) {

          dossierActif = ControllerModifierDossier.ajouterAntecedent();
          afficherDossier();

        } else if (evt.getPropertyName().compareTo("updateAntecedent") == 0) {

          dossierActif.antecedents = (List<AntecedentMedical>) evt.getNewValue();
          dossierActif = ControllerModifierDossier.modifierDossier(dossierActif);
          afficherDossier();

        }
      }
    });
    add(fenetreListeAntecedent);
  }

  private void afficherListeVisite() {
    fenetreListeVisite.setBounds(0, 125, 450, 500);
    fenetreListeVisite.addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().compareTo("addVisite") == 0) {

          dossierActif = ControllerModifierDossier.ajouterVisite();
          afficherDossier();

        } else if (evt.getPropertyName().compareTo("updateVisite") == 0) {

          dossierActif.visites = (List<VisiteMedicale>) evt.getNewValue();
          dossierActif = ControllerModifierDossier.modifierDossier(dossierActif);
          afficherDossier();

        }
      }
    });
    add(fenetreListeVisite);
  }

  private void afficherBoutonAnnulerModification(){
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

  private void afficherDossier() {
    if (dossierActif == null) {
      this.fenetreListeAntecedent.showAddAntecedentButton(false);
      this.fenetreListeVisite.showAddVisiteButton(false);
      annulerModification.setEnabled(false);
      return;
    }

    this.fenetreListeAntecedent.showAddAntecedentButton(true);
    this.fenetreListeVisite.showAddVisiteButton(true);
    annulerModification.setEnabled(true);

    fenetreCoordonnee.setPrenom(dossierActif.patient.prenom);
    fenetreCoordonnee.setNom(dossierActif.patient.nom);
    fenetreCoordonnee.setAddresse(dossierActif.patient.coordonnee.adresse.ville);

    fenetreListeAntecedent.setListeAntecedent(dossierActif.antecedents);
    fenetreListeVisite.setListeVisites(dossierActif.visites);
  }

  private void erreurChargerDossier(String codeRAMQ) {
    System.out.println("Erreur pour le chargement du dossier" + codeRAMQ);
  }


}
