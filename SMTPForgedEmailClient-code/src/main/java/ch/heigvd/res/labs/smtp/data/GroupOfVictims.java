package ch.heigvd.res.labs.smtp.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * Group of vitcims to whom a forged mail will be sent.
 * There should be at least one sender and two receiver.
 *
 * @author David Jaquet & Marc Labie
 */
public class GroupOfVictims {

    private Victim       sender;
    private List<Victim> victims;


    private static final Logger LOG = Logger.getLogger(GroupOfVictims.class.getName());

    /**
     * Constructor of the class.
     * @param sender :      The victim that will be seen as the sender of the forged e-mail
     * @param victims :     The victims that will receive the e-mail from the sender.
     */
    public GroupOfVictims(Victim sender, List<Victim> victims){
        this.sender  = sender;
        this.victims = victims;
    }

    public Victim getSender(){
        return sender.clone();
    }


    public void setSender(Victim newSender){
        sender.setEmailAddress(newSender.getEmailAddress());
    }


    public List<Victim> getVictims(){
        List<Victim> clone = new ArrayList<Victim>(victims.size());
        for(Victim victim : victims)
            clone.add(victim.clone());

        return clone;
    }

    public void setVictims(List<Victim> newVictims){
        victims.clear();
        for(Victim victim : newVictims)
            victims.add(victim);
    }

    public void addVictims(Victim... newVictims){
        for(Victim victim : newVictims)
            victims.add(victim);
    }
}
