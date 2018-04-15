package ch.heigvd.res.labs.smtp.data;

import java.util.List;

/**
 * Group of vitcims to whom a forged mail will be sent.
 * There should be at least one sender and two receiver.
 *
 * @author David Jaquet & Marc Labie
 */
public class GroupOfVictims {

    private Victim sender;
    private List<Victim> victims;
    private static final Logger LOG = Logger.getLogger(GroupOfVictims.class.getName());

    //TODO : implement the class respecting the condition (1 sender and 2 receiver at least).
    //TODO : implement getter and setters.
    //TODO : implement a method that can send a given forged e-mail to the list of victims from the receiver, using the STMPClientImpl class.

    /* Reprend mon exemple du main pour envoyer les mails.... */
    /* à compléter.... */

    public GroupOfVictims(Victim sender, List<Victim> victims){
        this.sender = sender;
        this.victims = victims;
    }

    public Victim getSender(){
        return new Victim(sender.getEmailAddress(), sender.getSmtpServer());
    }

    public void setSender(Victim newSender){
        sender.setEmailAddress(newSender.getEmailAddress());
        sender.setSmtpServer(newSender.getSmtpServer());
    }

    public List<Victim> getVictims(){
        // TODO : Test si renvoie une copie
        return victims;
    }

    public void setVictims(List<Victim> victims){
        // TODO : Test si fait une copie
        this.victims = victims;
    }

    public void addVictims(Victim... newVictims){
        for(int i = 0; i < newVictims.length; i++)
            victims.add(newVictims[i]);
    }

    public void prankThemAll(ForgedEmail mail){
        if(sender == null || victims.size() < 2){
            LOG.log(Level.INFO, "You need to have 1 sender and 2 receivers at least");
            return;
        }

        ISMTPClient client = new SMTPClientImpl();
        try {
            client.connect("mailcl0.heig-vd.ch", SMTPClientProtocol.DEFAULT_PORT);
            client.EHLO("test");

            for(int i = 0;  i < 1; i++){
                client.mailFrom(sender.getEmailAddress());
                if (client.mailTo(victims[i].getEmailAddress()))
                    client.sendMail(sender.getEmailAddress(),
                                    victims[i].getEmailAddress()),
                                    mail.getSubject(),
                                    mail.getText();
            }

            client.disconnect();
        }
        catch (IOException ex){
            Logger.getLogger(SMTPClient.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        catch (TimeoutException ex){
            Logger.getLogger(SMTPClient.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}
