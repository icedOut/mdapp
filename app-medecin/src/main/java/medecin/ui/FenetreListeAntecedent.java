package medecin.ui;

import javax.swing.*;

import medecin.models.AntecedentMedical;
import medecin.utils.DateFormatter;

import java.awt.*;

public class FenetreListeAntecedent extends JPanel {





  JLabel antecedentDateDebutLabel;
  JLabel antecedentDateFinLabel;
  private JButton addAntecedent = new JButton();
  private JTextField antecedentDiagnosticField;
  private JTextField antecedentTraitementField;
  private JTextField antecedentMedicamentField;
  private int currentVisiteIdx = 0;
  private int currentAntecedetIdx = 0;
  private JList antecedentListe = new JList();


    public FenetreListeAntecedent() {
      super();
      this.setLayout(null);
      afficherComponentAntecendant();
    }

public void afficherComponentAntecendant(){

    JLabel antecedentLabel = new JLabel("Antecedent médicaux");
    antecedentLabel.setBounds(0, 0, 250, 16);
    add(antecedentLabel);

    addAntecedent.setEnabled(false);
    addAntecedent.setText("Ajouter un antécédent");
    addAntecedent.setBounds(200, 0, 250, 16);
    add(addAntecedent);


    JLabel lblDbutDeLa = new JLabel("Début de la maladie");
    lblDbutDeLa.setBounds(10, 280, 148, 16);
    add(lblDbutDeLa);

    JLabel lblFinDeLa = new JLabel("Fin de la maladie");
    lblFinDeLa.setBounds(10, 300, 132, 16);
    add(lblFinDeLa);

    antecedentDateDebutLabel = new JLabel("");
    antecedentDateDebutLabel.setBounds(80, 280, 194, 16);
    add(antecedentDateDebutLabel);

    antecedentDateFinLabel = new JLabel("");
    antecedentDateFinLabel.setBounds(80, 300, 194, 16);
    add(antecedentDateFinLabel);

    JLabel antecedentDiagnosticLabel = new JLabel("Diagnostic");
    antecedentDiagnosticLabel.setBounds(10, 320, 95, 16);
    add(antecedentDiagnosticLabel);


    JLabel antecedentTraitementLabel = new JLabel("Traitement");
    antecedentTraitementLabel.setBounds(10, 350, 95, 16);
    add(antecedentTraitementLabel);

    JLabel antecedentMedicamentLabel = new JLabel("Medicament");
    antecedentMedicamentLabel.setBounds(10, 380, 95, 16);
    add(antecedentMedicamentLabel);
    antecedentListe.setBounds(10, 30, 450, 240);
    /*antecedentListe.addListSelectionListener(new ListSelectionListener() {
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
    });*/

    add(antecedentListe);

    antecedentDiagnosticField = new JTextField();
    antecedentDiagnosticField.setColumns(10);
    antecedentDiagnosticField.setBounds(120, 320, 400, 27);

    add(antecedentDiagnosticField);
    antecedentTraitementField = new JTextField();
    antecedentTraitementField.setColumns(10);
    antecedentTraitementField.setBounds(120, 350, 400, 27);
    add(antecedentTraitementField);

    antecedentMedicamentField = new JTextField();
    antecedentMedicamentField.setColumns(10);
    antecedentMedicamentField.setBounds(120, 380, 2400, 27);
    add(antecedentMedicamentField);



  }

  public void afficherAntecedentSelectionne(AntecedentMedical antecedent){
    antecedentDiagnosticField.setText(antecedent.diagnostic);
    antecedentTraitementField.setText(antecedent.traitement.nomTraitement);
    antecedentMedicamentField.setText(antecedent.traitement.medicament);
    antecedentDateDebutLabel.setText(DateFormatter.dateToString(antecedent.debutMaladie));
    antecedentDateFinLabel.setText(DateFormatter.dateToString(antecedent.finMaladie));
  }

}
