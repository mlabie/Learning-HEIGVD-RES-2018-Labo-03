package ch.heigvd.res.labs.smtp.net.client;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Set of function that need to be implemented by the Client to send mails using the SMTP protocol.
 * @author David Jaquet & Marc Labie
 */
public interface ISMTPClient {

    /**
     * @brief Taken from the RoulletteProtocol lab.
     * @author olivier Liechti
     *
     * Establishes a connection with the server, given its IP address or DNS name
     * and its port.
     *
     * @author olivier Liechti
     * @param server the IP address or DNS name of the servr
     * @param port the TCP port on which the server is listening
     * @throws java.io.IOException
     */
    public void connect(String server, int port) throws IOException;

    /**
     * @brief Taken from the RoulletteProtocol lab.
     * @author olivier Liechti
     *
     * Disconnects from the server by issuing the 'BYE' command.
     *
     * @throws IOException
     */
    public void disconnect() throws IOException;

    /**
     * @brief Taken from the RoulletteProtocol lab.
     * @author olivier Liechti
     *
     * Checks if the client is connected with the server
     *
     * @return true if the client is connected with the server
     */
    public boolean isConnected();


    /**
     * Send the Hello command to the server.
     *
     * @param helo the helo word.
     */
    public void EHLO(String helo) throws IOException, TimeoutException;


    /**
     * choose the mail's sender.
     *
     * @param mail_from : the sender's email address.
     */
    public void mailFrom(String mail_from) throws IOException;

    /**
     * choose the mail's receiver.
     *
     * @param mail_to : the receiver's email address.
     * @return True if the mail was accepted. False otherwise.
     */
    public boolean mailTo(String mail_to) throws IOException;


    /**
     * Send an e-mail
     *
     * @param mail_from
     * @param mail_to
     * @param subject
     * @param text
     * @return the response of the server.
     */
    public void sendMail(String mail_from, String mail_to, String subject, String text) throws IOException;

}
