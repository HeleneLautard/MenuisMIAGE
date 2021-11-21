/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Hélène
 */
@MessageDriven(mappedName = "TOPIC_AFFAIRES", activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "TOPIC_AFFAIRES")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "TOPIC_AFFAIRES")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
    ,
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "JMSType IN ('AttentePose')")
})
public class ListenerAffaireAttentePose implements MessageListener {

    private Context context = null;
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private String factoryName = "MenuisMiageConnectionFactory";
    private String destName = "TOPIC_AFFAIRES";
    private Destination dest = null;
    private Session session = null;
    private MessageConsumer receiver = null;

    public ListenerAffaireAttentePose() {
        try {
            // create the JNDI initial context
            this.context = new InitialContext();

            // look up the ConnectionFactory
            this.factory = (ConnectionFactory) context.lookup(this.factoryName);

            // look up the Destination
            this.dest = (Destination) this.context.lookup(this.destName);

            // create the connection
            this.connection = this.factory.createConnection();

            // create the session
            this.session = this.connection.createSession(
                    false, Session.AUTO_ACKNOWLEDGE);

        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(ListenerAffaireAttentePose.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Message reçu sur Gestion Pose ");
        if (message instanceof TextMessage) {
            TextMessage msg = (TextMessage) message;
            try {
                System.out.println(" \t Received : " + msg.getText() + " (JMS Type : " + msg.getJMSType() + ") at " + java.time.LocalDateTime.now());
            } catch (JMSException ex) {
                System.err.println("Failed to get message text: " + ex);
            }
        } else if (message != null) {
            System.out.println("Non Text Message Received");
        }
    }

}
