package central.models;

public interface Utilisateur {

  public String codeUsager = "";
  public String hash = "";
  public String salt = "";
  public TypePermission[] permissions = new TypePermission[4];
}
