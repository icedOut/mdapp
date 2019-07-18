package medecin.ui;

import medecin.models.VisiteMedicale;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.List;

public class FenetreCoordonnee extends JPanel{


  private JLabel lblPlaceholderNom = new JLabel(" ");
  private JLabel lblPlaceholderPrenom = new JLabel(" ");
  private JLabel lblPlaceholderAddresse = new JLabel(" ");
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


    lblPlaceholderNom.setBounds(79, 6, 105, 16);
    add(lblPlaceholderNom);


    lblPlaceholderPrenom.setBounds(79, 27, 140, 16);
    add(lblPlaceholderPrenom);


    lblPlaceholderAddresse.setBounds(79, 46, 140, 16);
    add(lblPlaceholderAddresse);

  }


  public void setNom(String nom){
    this.lblPlaceholderNom.setText(nom);
  }

  public void setPrenom(String prenom){
    this.lblPlaceholderPrenom.setText(prenom);
  }

  public void setAddresse(String address){
    this.lblPlaceholderAddresse.setText(address);
  }


}
