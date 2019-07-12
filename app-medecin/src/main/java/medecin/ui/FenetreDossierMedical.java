package medecin.ui;

import medecin.controlleur.ControlleurChargerDossier;
import medecin.models.DossierMedical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreDossierMedical extends JPanel implements ActionListener {


  JTextField codeRAMQ;
  JLabel codeRAMQLabel;
  JButton getDossierButton;

  FenetreCoordonnee coordWindow = new FenetreCoordonnee();


  public FenetreDossierMedical(){
    super();
    this.codeRAMQLabel = new JLabel();
    this.codeRAMQLabel.setText("Code RAMQ Scanné");
    this.codeRAMQLabel.setSize(150, 25);
    this.codeRAMQLabel.setLocation(0, 0);
    this.add(codeRAMQLabel);


    this.codeRAMQ = new JTextField();
    this.codeRAMQ.setSize(150, 25);
    this.codeRAMQ.setLocation(150, 0);
    this.add(codeRAMQ);

    this.getDossierButton = new JButton();
    this.getDossierButton.setText("Charger dossier");
    this.getDossierButton.addActionListener(this);
    this.getDossierButton.setSize(150, 25);
    this.getDossierButton.setLocation(0, 35);
    this.add(getDossierButton);


    this.repaint();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    DossierMedical dossier = ControlleurChargerDossier.chargerDossier(this.codeRAMQ.getText());
    if(dossier == null){
      this.setBackground(Color.blue);
      this.repaint();
      return;
    }

    this.setBackground(Color.green);
    this.repaint();
    return;
  }
}
