/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.sender;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.AffaireDTO;

/**
 *
 * @author Hélène
 */
public class SenderDemandeAffaire {
    
    InitialContext context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "MenuisMiageConnectionFactory";
    String destName = "QUEUE_DEMANDE_AFFAIRE";
    Destination dest = null;
    Session session = null;
    MessageProducer sender = null;
    MessageConsumer consumer = null;
            
    public SenderDemandeAffaire() {
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
            consumer = session.createConsumer(dest);
            // start the connection, to enable message sends
            connection.start();
        } catch (NamingException | JMSException ex) {
            Logger.getLogger(SenderDemandeAffaire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Envoyer une demande d'affaire selon le numéro de la commande concernée
     * @param numCommande numéro de la commande rattachée à l'affaire
     * @return Affaire correspondante
     * @throws javax.jms.JMSException
     */
    public AffaireDTO sendDemandeAffaire(int numCommande) throws JMSException{
        try {
            AffaireDTO affaire = new AffaireDTO(numCommande);
            ObjectMessage message = session.createObjectMessage(affaire);
            sender.send(message);
            //Attente de la réponse
            Message messageAffaire = this.consumer.receive();
            System.out.println("message : " + messageAffaire);
            if(messageAffaire instanceof ObjectMessage){
                affaire = (AffaireDTO) ((ObjectMessage) messageAffaire).getObject();
            }
            return affaire;
        } catch (JMSException ex) {
            throw ex;
        }
    }

}
