package medecin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {


  private static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



  public static String dateToString(Date d){
    return dateFormater.format(d);
  }

  public static Date stringToDate(String date){
    try{
      return dateFormater.parse(date);
    }
    catch(ParseException pe){
      return null;
    }
  }



}