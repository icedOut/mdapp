package central.data;

import central.dao.*;
import central.dto.*;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;


public class DaoProvider {


  private static DAOAntecedentMedical antecedentDao = null;
  private static DAODossierMedical dossierMedicalDao = null;
  private static DAOEtablissementSante etablissementDao = null;
  private static DAOPatient patientDao = null;
  private static DAOPersonnelSante personnelSanteDao = null;
  private static DAOSession sessionDao = null;
  private static DAOUtilisateur utilisateurDao = null;
  private static DAOVisiteMedicale visiteDao = null;
  private static JdbcConnectionSource connectionSource = null;

  private DaoProvider(){

  }

  private static void connectToDatabase(){
    try{
      JDBCConnectionCreator connCreator = new SqLiteConnectionCreator();
      JDBCConnectionHelper connection = connCreator.createConnection(ConnectionType.POOLED);

      connectionSource = connection.connect();

      antecedentDao = DaoManager.createDao(connectionSource, DTOAntecedentMedical .class);
      dossierMedicalDao = DaoManager.createDao(connectionSource, DTODossierMedical .class);
      etablissementDao = DaoManager.createDao(connectionSource, DTOEtablissementSante .class);
      patientDao = DaoManager.createDao(connectionSource, DTOPatient .class);
      personnelSanteDao = DaoManager.createDao(connectionSource, DTOPersonnelSante .class);
      sessionDao = DaoManager.createDao(connectionSource, DTOSession.class);
      utilisateurDao = DaoManager.createDao(connectionSource, DTOUtilisateur.class);
      visiteDao = DaoManager.createDao(connectionSource, DTOVisiteMedicale.class);
    }
    catch(SQLException sqle){
      System.out.println(sqle.getCause());
    }

  }

  public static DAOAntecedentMedical getAntecedentDAO(){
    if(connectionSource == null || antecedentDao == null) connectToDatabase();
    return antecedentDao;
  }

  public static DAODossierMedical getDossierDAO(){
    if(connectionSource == null || dossierMedicalDao == null) connectToDatabase();
    return dossierMedicalDao;
  }

  public static DAOEtablissementSante getEtablissementDAO(){
    if(connectionSource == null || etablissementDao == null) connectToDatabase();
    return etablissementDao;
  }

  public static DAOPatient getPatientDAO(){
    if(connectionSource == null || patientDao == null) connectToDatabase();
    return patientDao;
  }

  public static DAOPersonnelSante getPersonnelSanteDAO(){
    if(connectionSource == null || personnelSanteDao == null) connectToDatabase();
    return personnelSanteDao;
  }

  public static DAOSession getSessionDAO(){
    if(connectionSource == null || sessionDao == null) connectToDatabase();
    return sessionDao;
  }

  public static DAOUtilisateur getUtilisateurDAO(){
    if(connectionSource == null || utilisateurDao == null) connectToDatabase();
    return utilisateurDao;
  }

  public static DAOVisiteMedicale getVisiteDAO(){
    if(connectionSource == null || visiteDao == null) connectToDatabase();
    return visiteDao;
  }








}
