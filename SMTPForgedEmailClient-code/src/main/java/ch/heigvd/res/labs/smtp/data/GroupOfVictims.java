package ch.heigvd.res.labs.smtp.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.heigvd.res.labs.smtp.SMTPClient;
import ch.heigvd.res.labs.smtp.net.client.ISMTPClient;
import ch.heigvd.res.labs.smtp.net.client.SMTPClientImpl;
import ch.heigvd.res.labs.smtp.net.protocol.SMTPClientProtocol;

import java.util.concurrent.TimeoutException;

/**
 * Group of vitcims to whom a forged mail will be sent.
 * There should be at least one sender and two receiver.
 *
 * @author David Jaquet & Marc Labie
 */
public class GroupOfVictims {

    private Victim sender;
    private ArrayList<Victim> victims;
    private static final Logger LOG = Logger.getLogger(GroupOfVictims.class.getName());

    //TODO : implement the class respecting the condition (1 sender and 2 receiver at least).
    //TODO : implement getter and setters.
    //TODO : implement a method that can send a given forged e-mail to the list of victims from the receiver, using the STMPClientImpl class.

    /* Reprend mon exemple du main pour envoyer les mails.... */
    /* à compléter.... */

    public GroupOfVictims(Victim sender, ArrayList<Victim> victims){
        this.sender = sender;
        this.victims = victims;
    }

    public Victim getSender(){
        return sender.clone();
    }

    public void setSender(Victim newSender){
        sender.setEmailAddress(newSender.getEmailAddress());
        sender.setSmtpServer(newSender.getSmtpServer());
    }

    public ArrayList<Victim> getVictims(){
        ArrayList<Victim> clone = new ArrayList<Victim>(victims.size());
        for(Victim victim : victims)
            clone.add(victim.clone());

        return clone;
    }

    public void setVictims(ArrayList<Victim> newVictims){
        for(int i = 0; i < newVictims.size(); i++)
            victims.set(i, newVictims.get(i).clone());
    }

    public void addVictims(Victim... newVictims){
        for(int i = 0; i < newVictims.length; i++)
            victims.add(newVictims[i]);
    }
}
