package medecin.ui;

import javax.swing.*;
import java.awt.*;

public class FenetreDeconnexion extends JPanel {

  public JButton submit;

  public FenetreDeconnexion (){
    super();
    submit = new JButton();
    submit.setText("Se déconnecter");
    submit.setBounds(0, 10, 200, 25);
    this.add(submit);
  }

}
