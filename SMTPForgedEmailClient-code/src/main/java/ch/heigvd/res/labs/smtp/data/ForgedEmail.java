package ch.heigvd.res.labs.smtp.data;

/**
 * This class implements a Forged e-mail. It contains a subject and a text.
 *
 * @author David Jaquet & Marc Labie
 */
public class ForgedEmail {

    private String subject;
    private String text;


    /**
     * Constuctor of the class.
     *
     * @param subject :     The subject of the forged E-mail
     * @param text :        The text in the e-mail
     */
    public ForgedEmail(String subject, String text){
        this.subject = subject;
        this.text = text;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
}
