package ch.heigvd.res.labs.smtp.data;

/**
 * Implementation of the Victims of our client's forged e-mail.
 * It can be the sender or the receiver.
 *
 * @author David Jaquet & Marc Labie
 */
public class Victim {

    private String emailAddress;
    private String smtpServer;

    public Victim(String emailAddress, String smtpServer){
        this.emailAddress = emailAddress;
        this.smtpServer   = smtpServer;
    }

    //TODO : Getter et Setter

    /* à compléter.... */
}
