package ch.heigvd.res.labs.smtp.data;

/**
 * Implementation of the Victims of our client's forged e-mail.
 * It can be the sender or the receiver.
 *
 * @author David Jaquet & Marc Labie
 */
public class Victim implements Cloneable {

    private String emailAddress;

    /**
     * Constructor of the Class.
     *
     * @param emailAddress :    The e-mail address of the victim.
     */
    public Victim(String emailAddress){
        this.emailAddress = emailAddress;
    }


    public String getEmailAddress(){
        return emailAddress;
    }

    public void setEmailAddress(String email){
        emailAddress = email;
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
