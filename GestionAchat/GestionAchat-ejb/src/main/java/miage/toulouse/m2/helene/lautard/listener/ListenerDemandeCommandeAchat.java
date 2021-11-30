/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miage.toulouse.m2.helene.lautard.entities.Commande;
import miage.toulouse.m2.helene.lautard.metier.GestionAchatLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.CommandeDTO;

/**
 *
 * @author Hélène
 */
@MessageDriven( activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QUEUE_COMMANDE_ACHAT")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ListenerDemandeCommandeAchat implements MessageListener {

    @EJB
    private GestionAchatLocal gestionAchat;

    InitialContext context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "MenuisMiageConnectionFactory";
    String destName = "QUEUE_COMMANDE_ACHAT";
    Destination dest = null;
    Session session = null;
    MessageProducer sender = null;

    public ListenerDemandeCommandeAchat() {

        try {
            this.context = new InitialContext();
            this.factory = (ConnectionFactory) context.lookup(factoryName);
            this.dest = (Destination) context.lookup(destName);
            this.connection = factory.createConnection();
            this.connection.start();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.sender = this.session.createProducer(null);
            this.sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        } catch (NamingException | JMSException ex) {
            Logger.getLogger(ListenerDemandeCommandeAchat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage msg = (ObjectMessage) message;
                CommandeDTO demandeCommande = (CommandeDTO) ((ObjectMessage) message).getObject();
                Commande cmd = this.gestionAchat.creerCommande(demandeCommande.getCotes(), demandeCommande.getMontant(), demandeCommande.getNumAffaire(), demandeCommande.getNumMenuiserie());
                
                TextMessage response = this.session.createTextMessage(cmd.getNumcommande().toString());
                response.setJMSCorrelationID(msg.getJMSCorrelationID());
                System.out.println(msg.getJMSReplyTo());
                this.sender.send(msg.getJMSReplyTo(), response);

                System.out.println("(GESTION ACHAT) Demande de commande reçue : " + demandeCommande.toString());
            } catch (JMSException ex) {
                Logger.getLogger(ListenerDemandeCommandeAchat.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("(GESTION ACHAT) Le message reçu n'est pas une demande de commande d'achat.");
        }
    }

    
}
