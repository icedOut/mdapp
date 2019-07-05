package central.models;

public class ReponseAuthentification {

  public boolean success;
  public String msgErr;

  public ReponseAuthentification(boolean success){
    this.success = success;
    this.msgErr = "";
  }

  public void setErrMessage(String errMessage){
    this.msgErr = errMessage;
  }

}
