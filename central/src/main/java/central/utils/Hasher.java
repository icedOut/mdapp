package central.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {


  public static String hashPassword(String pwd, String salt){
    try{
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      String fullPwd = new StringBuilder().append(pwd).append(salt).toString();
      byte[] byteHash = md.digest(fullPwd.getBytes());
      BigInteger no = new BigInteger(1, byteHash);
      String hashtext = no.toString(16);
      return hashtext;
    }
    catch(NoSuchAlgorithmException noAlgEx){
      System.out.println(noAlgEx.getCause());
      return String.valueOf("");
    }
  }


}
