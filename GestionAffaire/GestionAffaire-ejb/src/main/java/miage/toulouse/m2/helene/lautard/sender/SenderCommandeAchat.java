/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.sender;

import java.util.Random;
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
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miage.toulouse.m2.helene.lautard.entities.Affaire;
import miage.toulouse.m2.helene.lautard.metier.GestionAffaireLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.CommandeDTO;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException;

/**
 *
 * @author Hélène
 */
public class SenderCommandeAchat implements MessageListener {

    GestionAffaireLocal gestionAffaire = lookupGestionAffaireLocal();
    
    InitialContext context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "MenuisMiageConnectionFactory";
    String destName = "QUEUE_COMMANDE_ACHAT";
    Destination dest = null;
    Session session = null;
    MessageProducer sender = null;
    
    int numCommande;
    Affaire affaire;
    
    

    public SenderCommandeAchat(Affaire affaire) {
        this.affaire = affaire;
    }

    public Affaire getAffaire() {
        return affaire;
    }

    
    public int getNumCommande(){
        return this.numCommande;
    }
    
    

    @Override
    public void onMessage(Message message) {
       if(message instanceof TextMessage){
            try {
                TextMessage msg = (TextMessage) message;
                this.numCommande = this.getNumCommandeCreee(msg);
                System.out.println("Commande créée, N° " + ((TextMessage) message).getText());
                Affaire aff = this.gestionAffaire.findAffaire(this.affaire.getNumaffaire());
                aff.setKeynumcommande(this.numCommande);
                aff.setStatut("Commande passée");
                System.out.println(aff.toString());
            } catch (JMSException | AffaireNotFoundException ex) {
                Logger.getLogger(SenderCommandeAchat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sendDemandeCommande(String cotes, float montant, int numAffaire, int numMenuiserie){
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
            
            
            Destination tempDest = session.createTemporaryQueue();
            MessageConsumer responseConsumer = session.createConsumer(tempDest);
            responseConsumer.setMessageListener(this);
            

            CommandeDTO demandeCommande = new CommandeDTO(cotes, montant, numAffaire, numMenuiserie);
            ObjectMessage message = session.createObjectMessage(demandeCommande);
            message.setJMSReplyTo(tempDest);
            String correlationId = this.createRandomString();
            message.setJMSCorrelationID(correlationId);
            System.out.println("Sent : Demande de commande " + demandeCommande.toString());
            sender.send(message);
        } catch (JMSException | NamingException exception) {
            System.out.println(exception);
        }
    }
    
    private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }
    
    private int getNumCommandeCreee(TextMessage message) throws JMSException{
        return Integer.parseInt(message.getText());
    }

    private GestionAffaireLocal lookupGestionAffaireLocal() {
        try {
            Context c = new InitialContext();
            return (GestionAffaireLocal) c.lookup("java:global/miage.toulouse.m2.helene.lautard_GestionAffaire-ear_ear_1.0/miage.toulouse.m2.helene.lautard_GestionAffaire-ejb_ejb_1.0/GestionAffaire!miage.toulouse.m2.helene.lautard.metier.GestionAffaireLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
   
}
