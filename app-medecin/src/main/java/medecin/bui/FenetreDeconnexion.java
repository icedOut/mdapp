package medecin.bui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreDeconnexion extends JPanel implements ActionListener {

  public FenetreDeconnexion(){
    JButton btnSeConnecter = new JButton("Se déconnecter");
    btnSeConnecter.setBounds(59, 120, 185, 16);
    btnSeConnecter.addActionListener(this);
    add(btnSeConnecter);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.firePropertyChange("disconnect", false, true);
  }
}
