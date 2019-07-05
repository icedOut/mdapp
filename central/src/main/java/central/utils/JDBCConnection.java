package central.utils;

import central.dao.*;
import central.dto.*;
import central.models.Session;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

import java.sql.*;
import java.util.Properties;

public class JDBCConnection {

    private static JdbcPooledConnectionSource conn = null;

    private static DAOAntecedentMedical antecedentDao = null;
    private static DAODossierMedical dossierMedicalDao = null;
    private static DAOEtablissementSante etablissementDao = null;
    private static DAOPatient patientDao = null;
    private static DAOPersonnelSante personnelSanteDao = null;
    //private static DAOSession sessionDao = null;
    private static DAOUtilisateur utilisateurDao = null;
    private static DAOVisiteMedicale visiteDao = null;
    private JDBCConnection() {

    }

    private static JdbcPooledConnectionSource connect() {
        if (conn != null) return conn;
        try {

            Properties conf = Config.getConfig();
            String url = conf.getProperty("db_conn_string");
            String user = conf.getProperty("db_user");
            String password = conf.getProperty("db_password");

            conn = new JdbcPooledConnectionSource(url);
            conn.setMaxConnectionsFree(5);
            System.out.println("Connected to the RAMQ database successfully.");
            System.out.println(conn.isOpen("DOSSIER_MEDICAL"));

            antecedentDao = DaoManager.createDao(conn, DTOAntecedentMedical.class);
            dossierMedicalDao = DaoManager.createDao(conn, DTODossierMedical.class);
            etablissementDao = DaoManager.createDao(conn, DTOEtablissementSante.class);
            patientDao = DaoManager.createDao(conn, DTOPatient.class);
            personnelSanteDao = DaoManager.createDao(conn, DTOPersonnelSante.class);
            //sessionDao = DaoManager.createDao(conn, Session.class);
            utilisateurDao = DaoManager.createDao(conn, DTOUtilisateur.class);
            visiteDao = DaoManager.createDao(conn, DTOVisiteMedicale.class);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }


    public static DAOAntecedentMedical getAntecedentDAO(){
        if(conn == null) connect();
        return antecedentDao;
    }

    public static DAODossierMedical getDossierDAO(){
        if(conn == null) connect();
        return dossierMedicalDao;
    }

    public static DAOEtablissementSante getEtablissementDAO(){
        if(conn == null) connect();
        return etablissementDao;
    }

    public static DAOPatient getPatientDAO(){
        if(conn == null) connect();
        return patientDao;
    }

    public static DAOPersonnelSante getPersonnelSanteDAO(){
        if(conn == null) connect();
        return personnelSanteDao;
    }



    /*
    public static DAOSession getSessionDAO(){
        if(conn == null) connect();
        return sessionDao;
    }
     */

    public static DAOUtilisateur getUtilisateurDAO(){
        if(conn == null) connect();
        return utilisateurDao;
    }


    public static DAOVisiteMedicale getVisiteDAO(){
        if(conn == null) connect();
        return visiteDao;
    }
}
