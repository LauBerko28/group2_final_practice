package ext.group3.utilities.Utilities_API;

import ext.group3.utilities.Utilities_UI.ConfigurationReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Dynamically loads test data to variables, according to the env value.
 * First checks if "env" value was passed from maven command line.
 * If yes, it will use that value.
 * If not, it will use value in local configuration.properties file
 */

public class Environment {

    public static final String URL;
    public static final String BASE_URL;
    public static final String DB_USERNAME;
    public static final String DB_PASSWORD;
    public static final String DB_URL;
    public static final String CLIENT_EMAIL;
    public static final String CLIENT_PASSWORD;
    public static final String SUPERVISOR_EMAIL;
    public static final String SUPERVISOR_PASSWORD;
    public static final String ADVISOR_EMAIL;
    public static final String ADVISOR_PASSWORD;
    public static final String EMPLOYEE_EMAIL;
    public static final String EMPLOYEE_PASSWORD;

    static { //runs once in beginning when we use the class- static block
        //class to read from .properties files
        Properties properties = null;
        String environment = System.getProperty("env" ) != null ? System.getProperty("env" ) : ConfigurationReader.getProperties("env" );
        //String environment = ConfigurationReader.get("environment");

        try {

            String path = System.getProperty("user.dir" ) + "/src/test/resources/env/" + environment + ".properties";

            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        URL = properties.getProperty("url" );
        BASE_URL = properties.getProperty("base_url" );
        DB_USERNAME = properties.getProperty("dbUsername" );
        DB_PASSWORD = properties.getProperty("dbPassword" );
        DB_URL = properties.getProperty("dbUrl" );
        CLIENT_EMAIL = properties.getProperty("client_email" );
        CLIENT_PASSWORD = properties.getProperty("client_password" );
        SUPERVISOR_EMAIL = properties.getProperty("supervisor_email" );
        SUPERVISOR_PASSWORD = properties.getProperty("supervisor_password" );
        ADVISOR_EMAIL = properties.getProperty("advisor_email" );
        ADVISOR_PASSWORD = properties.getProperty("advisor_password" );
        EMPLOYEE_EMAIL = properties.getProperty("employee_email" );
        EMPLOYEE_PASSWORD = properties.getProperty("employee_password" );


    }

}
