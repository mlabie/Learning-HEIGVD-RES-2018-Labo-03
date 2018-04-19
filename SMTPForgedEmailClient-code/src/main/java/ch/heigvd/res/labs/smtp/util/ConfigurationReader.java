package ch.heigvd.res.labs.smtp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Class that implements a configuration properties file Reader. This properties file must contains
 * the following properties :
 *
 * smtpServerAddress :      The SMTP address of the server
 * smtpServerPort :         The SMTP port number
 * numberOfGroup :          The number of groups that it will create
 * witnessesToCC :          The e-mail address of a witness to whom the mail will be copied.
 *
 * If one of those properties is not included, the constructor will throw an IllegalArgumentException.
 *
 *  @author David Jaquet & Marc Labie
 */
public class ConfigurationReader {


    private String smtpServerAddress;
    private int    smtpServerPort;
    private int    numberOfGroup;
    private String witnessesToCC;


    /**
     * Constructor of the class.
     * Search in the fiven file the properties needed.
     *
     * @param file
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public ConfigurationReader(String file) throws IOException, IllegalArgumentException{

        Properties  properties  = new Properties();
        InputStream input       = null;

        String smtpServerAddress;
        String smtpServerPort;
        String numberOfGroup;
        String witnessesToCC;

        input = new FileInputStream(file);
        properties.load(input);

        smtpServerAddress = properties.getProperty("smtpServerAddress");
        smtpServerPort    = properties.getProperty("smtpServerPort");
        numberOfGroup     = properties.getProperty("numberOfGroup");
        witnessesToCC     = properties.getProperty("witnessesToCC");

        if(smtpServerAddress.equals(""))
            throw new IllegalArgumentException("SMTP Server Address not found in the config file.");

        if(smtpServerPort.equals(""))
            throw new IllegalArgumentException("SMTP Server Port not found in the config file.");

        if(numberOfGroup.equals(""))
            throw new IllegalArgumentException("Number of groups not found in the config file.");

        if(witnessesToCC.equals(""))
            throw new IllegalArgumentException("Witness To CC not found in the config file.");

        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort    = Integer.parseInt(smtpServerPort);
        this.numberOfGroup     = Integer.parseInt(numberOfGroup);
        this.witnessesToCC     = witnessesToCC;

    }

    public String getSmtpServerAddress(){ return smtpServerAddress; }
    public int getSmtpServerPort(){ return  smtpServerPort; }
    public int getNumberOfGroup(){ return  numberOfGroup; }
    public String getWitnessesToCC(){ return witnessesToCC; }

}
