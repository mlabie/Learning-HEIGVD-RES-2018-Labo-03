package ch.heigvd.res.labs.smtp.data;

import ch.heigvd.res.labs.smtp.net.client.ISMTPClient;
import ch.heigvd.res.labs.smtp.net.client.SMTPClientImpl;
import ch.heigvd.res.labs.smtp.net.protocol.SMTPClientProtocol;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that implements an e-mail prank.
 *
 * @author David Jaquet & Marc Labie
 */
public class Prank {

    public final static int VICTIM_MIN = 2;

    private GroupOfVictims group;
    private ForgedEmail    mail;
    private String         smtpServerAddress;
    private String         witness;
    private int            smtpServerPort;


    private static final Logger LOG = Logger.getLogger(Prank.class.getName());


    /**
     * Constructor of the class
     *
     * set the smtpServerPort to the Default SMTP Protocol port, which is 25.
     *
     * @param group :               The group of vitcims
     * @param mail :                The forged e-mail
     * @param smtpServerAddress :   The SMTP server address
     */
    public Prank(GroupOfVictims group, ForgedEmail mail, String smtpServerAddress){
        this(group, mail, smtpServerAddress, SMTPClientProtocol.DEFAULT_PORT);
    }


    /**
     * Constructor of the class
     *
     * @param group :               The group of vitcims
     * @param mail :                The forged e-mail
     * @param smtpServerAddress :   The SMTP server address
     * @param smtpServerPort :      The SMTP server port
     */
    public Prank(GroupOfVictims group, ForgedEmail mail, String smtpServerAddress, int smtpServerPort){
        this.group             = group;
        this.mail              = mail;
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort    = smtpServerPort;
    }


    /**
     * Constructor of the class
     *
     * @param group :               The group of vitcims
     * @param mail :                The forged e-mail
     * @param smtpServerAddress :   The SMTP server address
     * @param smtpServerPort :      The SMTP server port
     * @param witness :              The witness of the prank to CC
     */
    public Prank(GroupOfVictims group, ForgedEmail mail, String smtpServerAddress, int smtpServerPort, String witness){
        this.group             = group;
        this.mail              = mail;
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort    = smtpServerPort;
        this.witness           = witness;
    }


    /**
     * Send the forged e-mail to the group of victims.
     * @throws IOException
     * @throws TimeoutException
     */
    public void prankThemAll() throws IOException, TimeoutException{
        if(group.getSender() == null || group.getVictims().size() < VICTIM_MIN){
            LOG.log(Level.SEVERE, "You need to have 1 sender and 2 receivers at least");
            return;
        }

        List<Victim> victims = group.getVictims();
        Victim       sender  = group.getSender();
        ISMTPClient  client  = new SMTPClientImpl();


        client.connect(smtpServerAddress, smtpServerPort);
        client.EHLO("Saucisse");

        // Sends 1 forged e-mail for each victim.
        for(Victim victim : victims){

            client.mailFrom(sender.getEmailAddress());

            if (client.mailTo(victim.getEmailAddress()))
                client.sendMail(sender.getEmailAddress(),
                                victim.getEmailAddress(),
                                mail.getSubject(),
                                mail.getText());
        }

        client.disconnect();
    }
}
