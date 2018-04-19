package ch.heigvd.res.labs.smtp;

import ch.heigvd.res.labs.smtp.data.ForgedEmail;
import ch.heigvd.res.labs.smtp.data.GroupOfVictims;
import ch.heigvd.res.labs.smtp.data.Prank;
import ch.heigvd.res.labs.smtp.data.Victim;
import ch.heigvd.res.labs.smtp.net.client.ISMTPClient;
import ch.heigvd.res.labs.smtp.net.client.SMTPClientImpl;
import ch.heigvd.res.labs.smtp.net.protocol.SMTPClientProtocol;
import ch.heigvd.res.labs.smtp.util.ConfigurationReader;
import ch.heigvd.res.labs.smtp.util.MailReader;
import ch.heigvd.res.labs.smtp.util.VictimReader;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author David Jaquet & Marc Labie
 */
public class SMTPClient {
    public static void main(String args[]){

        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");

        try {
            ConfigurationReader config = new ConfigurationReader("config.properties");

            List<Victim> victims           = VictimReader.getVictims("victims.utf8");
            List<ForgedEmail> forgedEmails = MailReader.getForgedEMail("message.utf8");

            int nbGroup         = config.getNumberOfGroup();
            int nbPeopleInGroup = victims.size() / nbGroup;

            if(nbPeopleInGroup < 3){
                Logger.getLogger(SMTPClient.class.getName()).log(Level.SEVERE, "Group too small !");
                return;
            }

            if(forgedEmails.size() < 1){
                Logger.getLogger(SMTPClient.class.getName()).log(Level.SEVERE, "Add at least 1 email !");
                return;
            }

            ForgedEmail lastEmail = forgedEmails.remove(0);

            for(int i = 0; i < nbGroup; i++) {
                GroupOfVictims group = new GroupOfVictims();

                group.setSender(victims.remove(0));

                for(int j = 0; j < nbPeopleInGroup - 1; j++)
                    group.addVictims(victims.remove(0));

                Prank prank = new Prank(group, lastEmail, config.getSmtpServerAddress(), config.getSmtpServerPort(), config.getWitnessesToCC());
                prank.prankThemAll();

                if(forgedEmails.size() > 0)
                    lastEmail = forgedEmails.remove(0);
            }
        }
        catch (IOException | IllegalArgumentException | TimeoutException ex) {
            Logger.getLogger(SMTPClient.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
}
