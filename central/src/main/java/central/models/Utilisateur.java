package central.models;

public class Utilisateur {

  public String codeUsager = "";
  public String hash = "";
  public String salt = "";
  public TypePermission[] permissions = new TypePermission[4];
}
