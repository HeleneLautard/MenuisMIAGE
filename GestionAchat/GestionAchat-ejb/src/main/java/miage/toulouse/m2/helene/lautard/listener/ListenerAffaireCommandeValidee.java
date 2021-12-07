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
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import miage.toulouse.m2.helene.lautard.entities.Commande;
import miage.toulouse.m2.helene.lautard.metier.GestionAchatLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.AffaireDTO;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CommandeNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException;

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
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "JMSType IN ('CommandeValidée')")
})
public class ListenerAffaireCommandeValidee implements MessageListener {

    @EJB
    private GestionAchatLocal gestionAchat;

    private Context context = null;
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private String factoryName = "MenuisMiageConnectionFactory";
    private String destName = "TOPIC_AFFAIRES";
    private Destination dest = null;
    private Session session = null;
    private MessageConsumer receiver = null;
    
    

    public ListenerAffaireCommandeValidee() {
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
            Logger.getLogger(ListenerAffaireCommandeValidee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage msg = (ObjectMessage) message;
                AffaireDTO aff = (AffaireDTO) msg.getObject();
                int numCommande = aff.getNumCommande();
                Commande commande = this.gestionAchat.findCommande(numCommande);
                this.gestionAchat.checkTotalAmount(commande, aff.getMontant1(), aff.getMontant2());
                // Modification du statut de la commande 
                commande.setStatut("validée");
            } catch (JMSException | CommandeNotFoundException | WrongTotalAmountException ex) {
                Logger.getLogger(ListenerAffaireCommandeValidee.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (message != null) {
            System.out.println("(GESTION ACHAT) Le message reçu n'est pas un ObjectMessage");
        }
    }

}
