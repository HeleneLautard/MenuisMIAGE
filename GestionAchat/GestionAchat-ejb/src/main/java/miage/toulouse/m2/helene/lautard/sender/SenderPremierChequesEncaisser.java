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
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Hélène
 */
@MessageDriven(mappedName = "QUEUE_CHEQUES_A_ENCAISSER", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class SenderPremierChequesEncaisser implements MessageListener {
    
    InitialContext context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "MenuisMiageConnectionFactory";
    String destName = "QUEUE_CHEQUES_A_ENCAISSER";
    Destination dest = null;
    int count = 1;
    Session session = null;
    MessageProducer sender = null;
    String text = "Message (gestion achat) N° ";
    
    public SenderPremierChequesEncaisser() {
    }
    
    public void sendMsgEncaisserCheque1(){
        try {
            // create the JNDI initial context.
            context = new InitialContext();

            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);

            // look up the Destination
            dest = (Destination) context.lookup(destName);

            // create the connection
            connection = factory.createConnection();

            // create the session
            session = connection.createSession(
                false, Session.AUTO_ACKNOWLEDGE);

            // create the sender
            sender = session.createProducer(dest);

            // start the connection, to enable message sends
            connection.start();

            for (int i = 0; i < count; ++i) {
                TextMessage message  = session.createTextMessage();
                message.setText(text + (i + 1));
                sender.send(message);
                System.out.println("Sent: " + message.getText());
            }
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage msg = (TextMessage) message;
            try {
                System.out.println("ACK (Gestion Achat) : " + msg.getText());
            } catch (JMSException ex) {
                Logger.getLogger(SenderPremierChequesEncaisser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Non Text message (Sender Gestion Achat)");
        }
        
        
    }    
}
