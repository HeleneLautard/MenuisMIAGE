/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.sender;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
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
})
public class SenderAffaires implements MessageListener {

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "MenuisMiageConnectionFactory";
    String destName = "TOPIC_AFFAIRES";
    Destination dest = null;
    Session session = null;
    MessageProducer sender = null;

    public SenderAffaires() {
        try {
            // create the JNDI initial context.
            this.context = new InitialContext();
            // look up the ConnectionFactory
            this.factory = (ConnectionFactory) context.lookup(factoryName);
            // look up the Destination
            this.dest = (Destination) context.lookup(destName);
            // create the connection
            this.connection = factory.createConnection();
            // start the connection, to enable message sends
            this.connection.start();
            // create the session
            this.session = connection.createSession(
                    false, Session.AUTO_ACKNOWLEDGE);
            // create the sender
            this.sender = session.createProducer(dest);
        } catch (JMSException | NamingException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        //System.out.println("ACK ");
    }

    /**
     * Envoyer notification commande validée
     */
    public void sendMsgCommandeValidée() {
        this.sendMsg("CommandeValidée", "Commande de menuiserie Validée pour l'affaire N°XXX");
    }

    /**
     * Envoyer notification Attente de pose
     */
    public void sendMsgAttentePose() {
        this.sendMsg("AttentePose", "Attente de pose de maenuiserie pour l'affaire N°XXX");
    }

    /**
     * Methode générique pour l'envoie de message sur le topic affaire
     * @param jmsType JMSType appliqué au message envoyé
     * @param textToSend Text à envoyer dans le textMessage
     */
    private void sendMsg(String jmsType, String textToSend) {
        //TODO Change TextMessage to ObjectMessage(Affaire)
        TextMessage message;
        try {
            message = this.session.createTextMessage();
            message.setText(textToSend);
            message.setJMSType(jmsType);
            sender.send(message);
            System.out.println("Sent depuis GestionAffaire: " + message.getText());
        } catch (JMSException ex) {
            Logger.getLogger(SenderAffaires.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
