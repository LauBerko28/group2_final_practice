package ext.group3.utilities.Utilities_UI;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    //this class reads what is inside configuration properties
    private static Properties properties;

    static{
        try {
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e){
            System.out.println("File not found in ConfigurationReader class");
            e.printStackTrace();
        }
    }

    public static String getProperties(String keyName){
        return properties.getProperty(keyName);
    }
}
