package central.utils;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {


    private static Properties config = null;


    private Config(){ }




    public static Properties getConfig() {
        try{
            config = new Properties();
            InputStream is = Config.class.getClassLoader().getResourceAsStream("application.properties");
            if(is != null){
                config.load(is);
            }
            else{
                throw new FileNotFoundException("File config.properties not found");
            }
        }
        catch(IOException e){
            System.out.println(e.toString());
        }

        return config;
    }



}
