package medecin.ui;

import medecin.controlleur.ControlleurConnexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreConnexion extends JPanel implements ActionListener {


  public JTextField username;
  public JTextField password;
  public JLabel titre;
  public JButton submit;

  public FenetreConnexion (){
    super();

    titre = new JLabel();
    titre.setText("Connexion");
    titre.setBounds(0, 10, 150, 25);

    username = new JTextField();
    username.setText("Identifiant ici");
    username.setBounds(0, 40, 150, 25);

    password = new JTextField();
    password.setText("Mot de passe ici");
    password.setBounds(0, 70, 150, 25);

    submit = new JButton();
    submit.addActionListener(this);
    submit.setText("Se connecter");
    submit.setBounds(0, 105, 150, 25);

    this.add(titre);
    this.add(username);
    this.add(password);
    this.add(submit);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    boolean success = ControlleurConnexion.connexion(this.username.getText(), this.password.getText());
    if(success) this.setBackground(Color.GREEN);
    else this.setBackground(Color.red);
  }
}
