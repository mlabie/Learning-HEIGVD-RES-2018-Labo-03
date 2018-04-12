package ch.heigvd.res.labs.smtp.net.client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import ch.heigvd.res.labs.smtp.net.protocol.SMTPClientProtocol;
import org.omg.CORBA.TIMEOUT;

public class SMTPClientImpl implements ISMTPClient{

    private static final Logger LOG = Logger.getLogger(SMTPClientImpl.class.getName());

    private final int MAX_COUNTER_VAL =  1000;

    protected Socket       socket;
    protected InputStream  is;
    protected OutputStream os;

    protected BufferedReader br;
    protected PrintWriter    pw;

    protected String answer;


    SMTPClientImpl(){
    }

    @Override
    public void connect(String server, int port) throws IOException{
        if(isConnected())
            this.disconnect();

        socket = new Socket(server, port);
        is     = socket.getInputStream();
        os     = socket.getOutputStream();

        br = new BufferedReader(new InputStreamReader(is));
        pw = new PrintWriter(new OutputStreamWriter(os), true);

        // The server sends us "Hello. Online HELP is available. Will you find it?".
        // We ignore it.
        answer = br.readLine(); // connected....
        answer = br.readLine(); // Escape...
        answer = br.readLine(); // 220...

    }


    @Override
    public void disconnect() throws IOException{
        if(!isConnected())
            return;

        pw.println(SMTPClientProtocol.CMD_QUIT);  // quit
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
        pw.println(SMTPClientProtocol.CMD_EHLO + " " + helo);

        while(!answer.startsWith(SMTPClientProtocol.RESPONSE_250_SPACE) && (++counter < MAX_COUNTER_VAL));

        if(counter == MAX_COUNTER_VAL)
            throw new TimeoutException();

    }


    @Override
    public void mailFrom(String mail_from) throws IOException{
        pw.println(SMTPClientProtocol.CMD_MAIL_FROM + mail_from);  // MAIL_FROM
        answer = br.readLine(); // read answer...
    }


    @Override
    public boolean mailTo(String mail_to) throws IOException{
        pw.println(SMTPClientProtocol.CMD_RCPT_TO + mail_to);
        answer = br.readLine(); // read answer...

        return answer.equals(SMTPClientProtocol.RESPONSE_250_SPACE + SMTPClientProtocol.RESPONSE_ACCEPTED);
    }



    @Override
    public String sendMail(String mail_from, String mail_to, String subject, String text) throws IOException{
        
        pw.println(SMTPClientProtocol.MAIL_FROM + mail_from);
        pw.println(SMTPClientProtocol.MAIL_TO + mail_to);
        pw.println(SMTPClientProtocol.MAIL_SUBJECT + subject);
        pw.println("\n");
        pw.println(text);
        pw.println(SMTPClientProtocol.ENDING_CHARACTER);

        return br.readLine(); // read answer...;
    }
}
