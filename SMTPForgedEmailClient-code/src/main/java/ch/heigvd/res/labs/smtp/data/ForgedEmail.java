package ch.heigvd.res.labs.smtp.data;

/**
 * This class implements a Forged e-mail. It contains a subject and a text.
 *
 * @author David Jaquet & Marc Labie
 */
public class ForgedEmail {
    //private String mailFrom;          // Maybe not necessary
    //private String rcptTo;            // Maybe not necessary
    private String subject;
    private String text;

    //TODO : Implements the class with constructor, getter and setters.

    public ForgedEmail(String subject, String text){
        this.subject = subject;
        this.text = text;
    }

//    public ForgedEmail(String mailFrom, String rcptTo, String subject, String text){
//        this.mailFrom = mailFrom;
//        this.rcptTo = rcptTo;
//        this.subject = subject;
//        this.text = text;
//    }
//
//    public String getFrom(){
//        return mailFrom;
//    }
//
//    public void setFrom(String from){
//        mailFrom = from;
//    }
//
//    public String getRcpt(){
//        return rcptTo;
//    }
//
//    public void setRcpt(String rcpt){
//        rcptTo = rcpt
//    }

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
