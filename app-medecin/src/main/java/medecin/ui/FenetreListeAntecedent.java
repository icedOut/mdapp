package medecin.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import medecin.models.AntecedentMedical;
import medecin.utils.DateFormatter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FenetreListeAntecedent extends JPanel{

  private List<AntecedentMedical> antecedentsActifs = new ArrayList();
  private JButton addAntecedent = new JButton();
  private JButton addDateFin = new JButton();
  private JList antecedentListe = new JList();

  private JTextField antecedentDiagnosticField;
  private JTextField antecedentTraitementField;
  private JTextField antecedentMedicamentField;


  private JLabel antecedentDateDebutLabel = new JLabel("");
  private JLabel antecedentDateFinLabel = new JLabel("");
  private JLabel antecedentLabel = new JLabel("Antecedent médicaux");
  private JLabel antecedentDiagnosticLabel = new JLabel("Diagnostic");
  private JLabel antecedentTraitementLabel = new JLabel("Traitement");
  private JLabel antecedentMedicamentLabel = new JLabel("Medicament");
  private JLabel debutMaladieLabel = new JLabel("Début de la maladie");
  private JLabel finMaladieLabel = new JLabel("Fin de la maladie");



  private int currentAntecedetIdx = 0;



  public FenetreListeAntecedent() {
    super();
    this.setLayout(null);
    afficherComponentAntecendant();
  }



  public void afficherComponentAntecendant() {
    afficherLabels();
    afficherBoutonAjouterAntecedent();
    afficherListeAntecedents();
    afficherDiagnostifField();
    afficherMedicamentField();
    afficherTraitementField();
    afficherBoutonDateFin();
  }



  public void setListeAntecedent(List<AntecedentMedical> antecedents){
    this.antecedentsActifs = antecedents;
    this.antecedentListe.setListData(antecedents.toArray());
    if(currentAntecedetIdx >= 0 && currentAntecedetIdx < antecedents.size()){
      afficherAntecedentSelectionne(antecedents.get(currentAntecedetIdx));
    }
  }

  public void showAddAntecedentButton(boolean add){
    this.addAntecedent.setEnabled(add);
  }

  private void afficherTraitementField(){
    antecedentTraitementField = new JTextField();
    antecedentTraitementField.setColumns(10);
    antecedentTraitementField.setBounds(120, 372, 330, 20);
    antecedentTraitementField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {

      }

      @Override
      public void keyReleased(KeyEvent e) {
        antecedentsActifs.get(currentAntecedetIdx).traitement.nomTraitement = antecedentTraitementField.getText();
        firePropertyChange("updateAntecedent", null, antecedentsActifs);
      }
    });
    add(antecedentTraitementField);
  }

  private void afficherMedicamentField(){
    antecedentMedicamentField = new JTextField();
    antecedentMedicamentField.setColumns(10);
    antecedentMedicamentField.setBounds(120, 409, 330, 20);
    antecedentMedicamentField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {

      }

      @Override
      public void keyReleased(KeyEvent e) {
        antecedentsActifs.get(currentAntecedetIdx).traitement.medicament = antecedentMedicamentField.getText();
        firePropertyChange("updateAntecedent", null, antecedentsActifs);
      }
    });
    add(antecedentMedicamentField);
  }

  private void afficherDiagnostifField(){
    antecedentDiagnosticField = new JTextField();
    antecedentDiagnosticField.setColumns(10);
    antecedentDiagnosticField.setBounds(120, 340, 330, 20);
    antecedentDiagnosticField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {

      }

      @Override
      public void keyReleased(KeyEvent e) {
        antecedentsActifs.get(currentAntecedetIdx).diagnostic = antecedentDiagnosticField.getText();
        firePropertyChange("updateAntecedent", null, antecedentsActifs);
      }
    });
    add(antecedentDiagnosticField);
  }

  private void afficherListeAntecedents() {
    antecedentListe.setBounds(6, 19, 450, 212);
    antecedentListe.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if(antecedentListe.getSelectedIndex() < 0) return;
        currentAntecedetIdx = antecedentListe.getSelectedIndex();
        afficherAntecedentSelectionne(antecedentsActifs.get(currentAntecedetIdx));
      }
    });
    add(antecedentListe);
  }

  private void afficherAntecedentSelectionne(AntecedentMedical antecedent) {

    if(antecedent.finMaladie == null){
      antecedentDateFinLabel.setText("");
      addDateFin.setVisible(true);
    }
    else{
      addDateFin.setVisible(false);
      antecedentDateFinLabel.setText(antecedent.finMaladie == null ? "" : DateFormatter.dateToString(antecedent.finMaladie));
    }
    antecedentDiagnosticField.setText(antecedent.diagnostic);
    antecedentTraitementField.setText(antecedent.traitement.nomTraitement);
    antecedentMedicamentField.setText(antecedent.traitement.medicament);
    antecedentDateDebutLabel.setText(DateFormatter.dateToString(antecedent.debutMaladie));
  }

  private void afficherBoutonAjouterAntecedent() {
    addAntecedent.setEnabled(false);
    addAntecedent.setText("Ajouter un antécédent");
    addAntecedent.setBounds(200, 0, 250, 16);
    addAntecedent.setName("addAntecedent");
    addAntecedent.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        firePropertyChange("addAntecedent", null, antecedentsActifs);
      }
    });
    add(addAntecedent);
  }


  private void afficherBoutonDateFin(){
    addDateFin.setBounds(150, 310, 194, 16);
    addDateFin.setText("Terminer cet antecedent");
    addDateFin.setVisible(false);
    addDateFin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        antecedentsActifs.get(currentAntecedetIdx).finMaladie = new Date();
        firePropertyChange("updateAntecedent", null, antecedentsActifs);
      }
    });
    add(addDateFin);
  }



  private void afficherLabels() {
    antecedentLabel.setBounds(6, 0, 250, 16);
    add(antecedentLabel);

    // Label pour afficher le titre de la date
    debutMaladieLabel.setBounds(6, 261, 148, 16);
    add(debutMaladieLabel);

    finMaladieLabel.setBounds(6, 310, 132, 16);
    add(finMaladieLabel);


    // Label pour afficher la date
    antecedentDateDebutLabel.setBounds(150, 261, 194, 16);
    add(antecedentDateDebutLabel);

    antecedentDateFinLabel.setBounds(150, 310, 194, 16);
    add(antecedentDateFinLabel);


    antecedentDiagnosticLabel.setBounds(6, 344, 95, 16);
    add(antecedentDiagnosticLabel);

    antecedentTraitementLabel.setBounds(6, 378, 95, 16);
    add(antecedentTraitementLabel);

    antecedentMedicamentLabel.setBounds(6, 414, 95, 16);
    add(antecedentMedicamentLabel);
  }


}
