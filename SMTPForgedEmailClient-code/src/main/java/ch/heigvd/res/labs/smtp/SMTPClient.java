package ch.heigvd.res.labs.smtp;

import ch.heigvd.res.labs.smtp.net.client.ISMTPClient;
import ch.heigvd.res.labs.smtp.net.client.SMTPClientImpl;
import ch.heigvd.res.labs.smtp.net.protocol.SMTPClientProtocol;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author David Jaquet & Marc Labie
 */
public class SMTPClient {
    public static void main(String args[]){

        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");

        ISMTPClient client = new SMTPClientImpl();
        try {
            client.connect("mailcl0.heig-vd.ch", SMTPClientProtocol.DEFAULT_PORT);
            client.EHLO("test");
            for(int i = 0;  i < 1; i++){
                client.mailFrom("marc.labie@heig-vd.ch");
                if (client.mailTo("david.jaquet@heig-vd.ch"))
                    client.sendMail("marc.labie@heig-vd.ch",
                            "david.jaquet@heig-vd.ch",
                            "Si tu recois ce mail, c'est que mon client SMTP fonctionne !",
                            "Je t'aime <3");
            }

            client.disconnect();


        }catch (IOException ex){
            Logger.getLogger(SMTPClient.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        catch (TimeoutException ex){
            Logger.getLogger(SMTPClient.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
