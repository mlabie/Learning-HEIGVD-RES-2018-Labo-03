package ch.heigvd.res.labs.smtp.data;

import ch.heigvd.res.labs.smtp.SMTPClient;
import ch.heigvd.res.labs.smtp.net.client.ISMTPClient;
import ch.heigvd.res.labs.smtp.net.client.SMTPClientImpl;
import ch.heigvd.res.labs.smtp.net.protocol.SMTPClientProtocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prank {
    private GroupOfVictims group;
    private ForgedEmail mail;

    private static final Logger LOG = Logger.getLogger(Prank.class.getName());

    Prank(GroupOfVictims group, ForgedEmail mail){
        this.group = group;
        this.mail = mail;
    }

    // TODO : Getters & Setters

    public void prankThemAll(ForgedEmail mail){
        if(group.getSender() == null || group.getVictims().size() < 2){
            LOG.log(Level.SEVERE, "You need to have 1 sender and 2 receivers at least");
            return;
        }

        ArrayList<Victim> victims = group.getVictims();
        Victim sender = group.getSender();

        ISMTPClient client = new SMTPClientImpl();
        try {
            client.connect("mailcl0.heig-vd.ch", SMTPClientProtocol.DEFAULT_PORT);
            client.EHLO("test");

            for(int i = 0;  i < victims.size(); i++){
                client.mailFrom(sender.getEmailAddress());
                if (client.mailTo(victims.get(i).getEmailAddress()))
                    client.sendMail(sender.getEmailAddress(),
                                    victims.get(i).getEmailAddress(),
                                    mail.getSubject(),
                                    mail.getText());
            }

            client.disconnect();
        }
        catch (IOException | TimeoutException ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
