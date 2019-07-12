package medecin.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;

public class FenetrePrincipale extends JFrame {

  JPanel connexion;
  JPanel deconnexion;
  JPanel dossier;

  public FenetrePrincipale(String title){
    super(title);
    this.setSize(900, 800);
    setResizable(false);
    //getContentPane().setLayout(new BorderLayout());
    setVisible(true);
    afficherFenetreConnexion();
    afficherFenetreDeconnexion();
    afficherFenetreDossierPatient();
  }

  public void afficherFenetreConnexion(){
    connexion = new FenetreConnexion();
    connexion.setBounds(0, 0, 300, 150);
    this.add(connexion);
    this.repaint();
  }

  public void afficherFenetreDeconnexion(){
    deconnexion = new FenetreDeconnexion();
    deconnexion.setBounds(400, 0, 300, 150);
    this.add(deconnexion);
    this.repaint();
  }

  public void afficherFenetreDossierPatient(){
    dossier = new FenetreDossierMedical();
    dossier.setBackground(Color.orange);
    dossier.setBounds(0, 190, 900, 500);
    this.add(dossier);
  }



}
