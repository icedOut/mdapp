package medecin.ui;

import medecin.models.VisiteMedicale;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class FenetreListeVisites extends JPanel{


  private List<VisiteMedicale> visitesActives = new ArrayList();
  private JButton addVisite = new JButton();
  private JList visiteList = new JList();

  private JTextPane visiteNotesField = new JTextPane();
  private JTextPane visiteResumeField = new JTextPane();
  private JTextField visiteDiagnosticField;
  private JTextField visiteTraitementField;
  private JTextField visiteMedicamentField;


  JLabel visiteMedicalesLabel = new JLabel("Visites médicales");
  JLabel visiteNotesLabel = new JLabel("Notes");
  JLabel visiteResumeLabel = new JLabel("Resume");
  JLabel visiteDiagnosticLabel = new JLabel("Diagnostic");
  JLabel visiteTraitementLabel = new JLabel("Traitement");
  JLabel visiteMedicamentLabel = new JLabel("Medicament");

  private int currentVisiteIdx = 0;

  public FenetreListeVisites(){
    super();
    this.setLayout(null);
    afficherComponentVisite();
  }


  public void afficherListeVisites(List<VisiteMedicale> visites){
    this.visitesActives = visites;
    this.visiteList.setListData(visites.toArray());
  }

  public void showAddVisiteButton(boolean add){
    this.addVisite.setEnabled(add);
  }

  private void afficherVisiteSelectionnee(VisiteMedicale visite){
    visiteNotesField.setText(visite.notes);
    visiteResumeField.setText(visite.resume);
    visiteDiagnosticField.setText(visite.diagnostic);
    visiteTraitementField.setText(visite.traitement.nomTraitement);
    visiteMedicamentField.setText(visite.traitement.medicament);
  }

  private void afficherComponentVisite(){
    afficherVisiteLabel();
    afficherListeVisite();
    afficherBoutonAjouterVisite();
    afficherMedicament();
    afficherTraitement();
    afficherDiagnostic();
    afficherResume();
    afficherNotes();
  }


  private void afficherListeVisite(){
    visiteList.setBounds(6, 19, 450, 119);
    this.visiteList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        currentVisiteIdx = visiteList.getSelectedIndex();
        afficherVisiteSelectionnee(visitesActives.get(currentVisiteIdx));
      }
    });
    add(visiteList);
  }


  private void afficherBoutonAjouterVisite(){
    addVisite.setEnabled(false);
    addVisite.setText("Ajouter une visite");
    addVisite.setBounds(270, 0, 180, 16);
    addVisite.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          firePropertyChange("addVisite", true, false);
      }
    });
    add(addVisite);
  }

  private void afficherMedicament(){
    visiteMedicamentField = new JTextField();
    visiteMedicamentField.setBounds(101, 409, 350, 26);
    visiteMedicamentField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {}

      @Override
      public void keyPressed(KeyEvent e) {}

      @Override
      public void keyReleased(KeyEvent e) {
        visitesActives.get(currentVisiteIdx).traitement.medicament = visiteMedicamentField.getText();
        firePropertyChange("updateVisite", visitesActives, visitesActives);
      }
    });
    add(visiteMedicamentField);
    visiteMedicamentField.setColumns(10);
  }

  private void afficherTraitement(){
    visiteTraitementField = new JTextField();
    visiteTraitementField.setBounds(101, 372, 350, 27);
    visiteTraitementField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {}

      @Override
      public void keyPressed(KeyEvent e) {}

      @Override
      public void keyReleased(KeyEvent e) {
        visitesActives.get(currentVisiteIdx).traitement.nomTraitement = visiteTraitementField.getText();
        firePropertyChange("updateVisite", visitesActives, visitesActives);
      }
    });
    add(visiteTraitementField);
    visiteTraitementField.setColumns(10);
  }

  private void afficherDiagnostic(){
    visiteDiagnosticField = new JTextField();
    visiteDiagnosticField.setBounds(101, 340, 350, 20);
    visiteDiagnosticField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {}

      @Override
      public void keyPressed(KeyEvent e) {}

      @Override
      public void keyReleased(KeyEvent e) {
        visitesActives.get(currentVisiteIdx).diagnostic = visiteDiagnosticField.getText();
        firePropertyChange("updateVisite", visitesActives, visitesActives);
      }
    });
    add(visiteDiagnosticField);
    visiteDiagnosticField.setColumns(10);
  }

  private void afficherResume(){
    visiteResumeField.setBounds(6, 261, 450, 67);
    visiteResumeField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {}

      @Override
      public void keyPressed(KeyEvent e) {}

      @Override
      public void keyReleased(KeyEvent e) {
        visitesActives.get(currentVisiteIdx).resume = visiteResumeField.getText();
        firePropertyChange("updateVisite", visitesActives, visitesActives);
      }
    });
    add(visiteResumeField);
  }

  private void afficherNotes(){
    visiteNotesField.setBounds(6, 164, 450, 67);
    visiteNotesField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {}

      @Override
      public void keyPressed(KeyEvent e) {}

      @Override
      public void keyReleased(KeyEvent e) {
        visitesActives.get(currentVisiteIdx).notes = visiteNotesField.getText();
        firePropertyChange("updateVisite", visitesActives, visitesActives);
      }
    });
    add(visiteNotesField);
  }


  private void afficherVisiteLabel(){

    visiteMedicalesLabel.setBounds(6, 0, 148, 16);
    add(visiteMedicalesLabel);

    visiteNotesLabel.setBounds(6, 146, 61, 16);
    add(visiteNotesLabel);

    visiteResumeLabel.setBounds(6, 246, 61, 16);
    add(visiteResumeLabel);

    visiteDiagnosticLabel.setBounds(6, 344, 95, 16);
    add(visiteDiagnosticLabel);

    visiteTraitementLabel.setBounds(6, 383, 95, 16);
    add(visiteTraitementLabel);

    visiteMedicamentLabel.setBounds(6, 414, 76, 16);
    add(visiteMedicamentLabel);
  }


}
