package ch.heigvd.res.labs.smtp.data;

/**
 * Implementation of the Victims of our client's forged e-mail.
 * It can be the sender or the receiver.
 *
 * @author David Jaquet & Marc Labie
 */
public class Victim implements Cloneable {

    private String emailAddress;
    private String smtpServer;

    public Victim(String emailAddress, String smtpServer){
        this.emailAddress = emailAddress;
        this.smtpServer   = smtpServer;
    }

    //TODO : Getter et Setter

    public String getEmailAddress(){
        return emailAddress;
    }

    public void setEmailAddress(String email){
        emailAddress = email;
    }

    public String getSmtpServer(){
        return smtpServer;
    }

    public void setSmtpServer(String server){
        smtpServer = server;
    }

    @Override
    public Victim clone() {
        try {
            return (Victim) super.clone();
        } catch (CloneNotSupportedException error) {
            System.out.println(error.getMessage());
        }

        return null;
    }
}
