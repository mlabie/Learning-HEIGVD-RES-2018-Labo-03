package ch.heigvd.res.labs.smtp.net.client;

import java.io.IOException;
import java.util.List;

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
     */
    public void EHLO();


    /**
     * choose the mail's sender.
     *
     * @param mail_from : the sender's email address.
     */
    public void mailFrom(String mail_from);

    /**
     * choose the mail's receiver.
     *
     * @param mail_to : the receiver's email address.
     */
    public String mailTo(String mail_to);

    /**
     * Check if the mail receiver address we send to the server was accepted.
     *
     * @return true is it was accepted, no otherwise.
     */
    public boolean checkIfReceiverAccepted();

    /**
     * Send an e-mail
     *
     * @param mail_from
     * @param mail_to
     * @param subject
     * @param text
     * @return the response of the server.
     */
    public String sendMail(String mail_from, String mail_to, String subject, String text);

}
