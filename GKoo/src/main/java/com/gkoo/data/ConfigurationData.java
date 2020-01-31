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
    
    public ConfigurationData() throws IOException {
        setFeesProperties();
    }
    
    public void setFeesProperties() throws IOException {
        InputStream inputStream = null;
        String feePercent = "";
        String mergingBoxFee = "";
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
            mergingBoxFee = prop.getProperty("mergingBoxFee");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        BUYING_SERVICE_FEE_PERCENT = Double.parseDouble(feePercent)/100;
        MERGING_BOX_FEE = Double.parseDouble(mergingBoxFee);
    }
}
