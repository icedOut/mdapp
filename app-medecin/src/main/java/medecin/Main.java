package medecin;

import medecin.models.DossierMedical;
import medecin.utils.ClientHTTP;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Medecin RAMQ");//creating instance of JFrame
		JLabel label = new JLabel();


		DossierMedical dossier = ClientHTTP.getDossier("RAMQ00000001");
		if(dossier != null) {
			String infoPatient = new StringBuilder()
							.append(dossier.patient.prenom)
							.append(" ")
							.append(dossier.patient.nom)
							.append(" - ")
							.append(dossier.patient.genre.toString())
							.toString();
			label.setText(infoPatient);
		}
		else{
			label.setText("marche pas");
		}

		f.setSize(900,600);//400 width and 500 height
		f.setLayout(null);//using no layout managers

		label.setLocation(300, 300);
		label.setSize(500,100);//400 width and 500 height
		label.setVisible(true);
		f.add(label);
		f.setVisible(true);//making the frame visible
		f.repaint();

	}

}

