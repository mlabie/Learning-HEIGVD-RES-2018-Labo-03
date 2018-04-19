package ch.heigvd.res.labs.smtp.util;

import ch.heigvd.res.labs.smtp.data.ForgedEmail;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that implements a static method that read a file containing Forged e-mails,
 * and return a list of ForgedEmail.
 *
 * The file containing the forged e-mail must have the folowing structure :
 *
 *  - Each mail should start with the line "Subject: " followed by a subject name.
 *  - To indicate the end of an e-mail, write on a single line the following String : "=="
 *
 * @author David Jaquet & Marc Labie
 */
public class MailReader {

    private MailReader(){}

    private static final String SEPARATOR = "==";
    private static final String SUBJECT   = "Subject: ";


    public static List<ForgedEmail> getForgedEMail(String file) throws IOException, IllegalArgumentException{

        List<ForgedEmail> forgedEmails   = new ArrayList<>();
        BufferedReader    bufferedReader = new BufferedReader(new FileReader(file));

        String line;
        String subject = "";
        String email   = "";

        boolean newMail = true;

        while ((line = bufferedReader.readLine()) != null){
            // If the SEPARATOR string appear, then we arrived at the end of a forged e-mail.
            if(line.equals(SEPARATOR)){
                forgedEmails.add(new ForgedEmail(subject, email));
                newMail = true;
                continue;
            }

            if(newMail){
                // A new forged e-mail must always Start with the SUBJECT string.
                if(!line.startsWith(SUBJECT))
                    throw new IllegalArgumentException("One email doesn't start with \"Subject: ...\".");

                subject = line.replace(SUBJECT,"");

                if(subject.equals(""))
                    throw new IllegalArgumentException("No Subject name have been mentionned.");

                email   = "";
                newMail = false;
                continue;
            }
            email += line + "\n";
        }

        return forgedEmails;
    }
}
