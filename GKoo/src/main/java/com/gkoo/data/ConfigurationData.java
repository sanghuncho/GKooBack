package com.gkoo.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationData {
    
    public static double BUYING_SERVICE_FEE_PERCENT;
    public static double MERGING_BOX_FEE;
    public static double BUYING_SERVICE_MINIMUM_COMMISION;
    public static String POSTGRE_DB_CONNECTION;
    public static String POSTGRE_DB_USER;
    public static String POSTGRE_DB_PASSWORD;
    public static String POSTGRE_DB_DRIVER;
    public static final String EMPTY = "";
    
    public ConfigurationData() throws IOException {
        setProperties();
    }
    
    public void setProperties() throws IOException {
        InputStream inputStream = null;
        String feePercent = EMPTY;
        String mergingBoxFee = EMPTY;
        String dbConnection = EMPTY;
        String dbUser = EMPTY;
        String dbPassword = EMPTY;
        String dbDriver = EMPTY;
        String minimumCommision = EMPTY;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
                
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
                 
            feePercent = prop.getProperty("buyingservice_fee_percentage");
            minimumCommision = prop.getProperty("buyingservice_minimum_commision");
            mergingBoxFee = prop.getProperty("mergingBoxFee");
            dbConnection = prop.getProperty("db_connection");
            dbUser = prop.getProperty("db_user");
            dbPassword = prop.getProperty("db_password");
            dbDriver = prop.getProperty("db_driver");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        
        BUYING_SERVICE_FEE_PERCENT = Double.parseDouble(feePercent)/100;
        MERGING_BOX_FEE = Double.parseDouble(mergingBoxFee);
        BUYING_SERVICE_MINIMUM_COMMISION = Double.parseDouble(minimumCommision);
        POSTGRE_DB_CONNECTION = dbConnection;
        POSTGRE_DB_USER = dbUser;
        POSTGRE_DB_PASSWORD = dbPassword;
        POSTGRE_DB_DRIVER = dbDriver;
    }
}