package medecin.ui;

import javax.swing.*;
import java.awt.*;

public class FenetreCoordonnee extends JPanel {


  JLabel nom = new JLabel();

  public FenetreCoordonnee(){
    this.setBackground(Color.gray);
    this.setVisible(true);
  }


  public void setNomPatient(String nom){

    this.nom.setText(nom);
  }





}
