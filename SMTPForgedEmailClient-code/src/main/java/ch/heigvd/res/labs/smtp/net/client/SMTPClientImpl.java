package ch.heigvd.res.labs.smtp.net.client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.heigvd.res.labs.smtp.net.protocol.SMTPClientProtocol;

/**
 * Implementation of the SMTP Protocol Client.
 * @author David Jaquet & Marc Labie
 */
public class SMTPClientImpl implements ISMTPClient{

    private static final Logger LOG = Logger.getLogger(SMTPClientImpl.class.getName());

    private final int MAX_COUNTER_VAL =  1000;

    private Socket       socket;
    private InputStream  is;
    private OutputStream os;

    private BufferedReader br;
    private PrintWriter    pw;

    private String answer;


    public SMTPClientImpl(){}


    @Override
    public void connect(String server, int port) throws IOException{
        if(isConnected())
            this.disconnect();

        LOG.log(Level.INFO, "Connecting on {0}", server+":"+port);

        socket = new Socket(server, port);
        is     = socket.getInputStream();
        os     = socket.getOutputStream();

        br = new BufferedReader(new InputStreamReader(is));
        pw = new PrintWriter(new OutputStreamWriter(os), true);

        LOG.log(Level.INFO, br.readLine());
    }


    @Override
    public void disconnect() throws IOException{
        if(!isConnected()){
            LOG.log(Level.INFO, "Client not connected.");
            return;
        }

        pw.println(SMTPClientProtocol.CMD_QUIT);  // quit

        LOG.log(Level.INFO, SMTPClientProtocol.CMD_QUIT);
        //LOG.log(Level.INFO, br.readLine());

        pw.close();
        br.close();
        is.close();
        os.close();
        socket.close();

    }


    @Override
    public boolean isConnected(){
        try {
            return socket.isConnected();
        }
        catch (NullPointerException e){
            return false;
        }
    }


    @Override
    public void EHLO(String helo) throws IOException, TimeoutException{


        int counter = 0;
        pw.println(SMTPClientProtocol.CMD_EHLO + " " + helo + SMTPClientProtocol.CARRIAGE_RETURN);
        LOG.log(Level.INFO, SMTPClientProtocol.CMD_EHLO + " " + helo);

        do{
            answer = br.readLine();
            LOG.log(Level.INFO, answer);
        }while (!answer.startsWith(SMTPClientProtocol.RESPONSE_250_SPACE) && (++counter < MAX_COUNTER_VAL));


        if(counter == MAX_COUNTER_VAL)
            throw new TimeoutException();
    }


    @Override
    public void mailFrom(String mail_from) throws IOException{
        pw.println(SMTPClientProtocol.CMD_MAIL_FROM + mail_from + SMTPClientProtocol.CARRIAGE_RETURN);  // MAIL_FROM
        LOG.log(Level.INFO, SMTPClientProtocol.CMD_MAIL_FROM + mail_from);

        answer = br.readLine(); // read answer...
        LOG.log(Level.INFO, answer);
    }


    @Override
    public boolean mailTo(String mail_to) throws IOException{
        pw.println(SMTPClientProtocol.CMD_RCPT_TO + mail_to + SMTPClientProtocol.CARRIAGE_RETURN);
        LOG.log(Level.INFO, SMTPClientProtocol.CMD_RCPT_TO + mail_to);
        answer = br.readLine(); // read answer...
        LOG.log(Level.INFO, answer);


        return answer.startsWith(SMTPClientProtocol.RESPONSE_250_SPACE);
    }



    @Override
    public void sendMail(String mail_from, String mail_to, String subject, String text) throws IOException{

        pw.println(SMTPClientProtocol.CMD_DATA + SMTPClientProtocol.CARRIAGE_RETURN);
        LOG.log(Level.INFO, SMTPClientProtocol.CMD_DATA);
        answer = br.readLine();
        LOG.log(Level.INFO, answer);

        pw.println(SMTPClientProtocol.MAIL_FROM + mail_from + SMTPClientProtocol.CARRIAGE_RETURN);
        pw.println(SMTPClientProtocol.MAIL_TO + mail_to + SMTPClientProtocol.CARRIAGE_RETURN);
        pw.println(SMTPClientProtocol.MAIL_SUBJECT + subject + SMTPClientProtocol.CARRIAGE_RETURN);
        pw.println("\n" + SMTPClientProtocol.CARRIAGE_RETURN);
        pw.println(text + SMTPClientProtocol.CARRIAGE_RETURN);
        pw.println(SMTPClientProtocol.ENDING_CHARACTER + SMTPClientProtocol.CARRIAGE_RETURN);

        LOG.log(Level.INFO, SMTPClientProtocol.MAIL_FROM + mail_from);
        LOG.log(Level.INFO, SMTPClientProtocol.MAIL_TO + mail_to);
        LOG.log(Level.INFO, SMTPClientProtocol.MAIL_SUBJECT + subject);
        LOG.log(Level.INFO, "\n");
        LOG.log(Level.INFO, text);
        LOG.log(Level.INFO, SMTPClientProtocol.ENDING_CHARACTER );


        LOG.log(Level.INFO, br.readLine());
    }
}
