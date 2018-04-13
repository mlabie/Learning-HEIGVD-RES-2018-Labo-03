package ch.heigvd.res.labs.smtp.net.protocol;


/**
 * Set of command that are used by the client to send an e-mail in the SMTP Protocol
 * @author David Jaquet & Marc Labie
 */
public class SMTPClientProtocol {

    public final static int DEFAULT_PORT = 25;

    public final static String CMD_EHLO                 = "EHLO";
    public final static String CMD_MAIL_FROM            = "MAIL FROM: ";
    public final static String CMD_RCPT_TO              = "RCPT TO: ";
    public final static String CMD_DATA                 = "DATA";
    public final static String CMD_QUIT                 = "QUIT";


    public final static String RESPONSE_250_SPACE       = "250 ";
    public final static String RESPONSE_ACCEPTED        = "Accepted";

    public final static String MAIL_FROM                = "From: ";
    public final static String MAIL_TO                  = "To: ";
    public final static String MAIL_SUBJECT             = "Subject: ";
    public final static String ENDING_CHARACTER         = ".";


    public final static String[] SUPPORTED_COMMANDS = new String[]{CMD_EHLO, CMD_MAIL_FROM, CMD_RCPT_TO, CMD_DATA, CMD_QUIT};
}
