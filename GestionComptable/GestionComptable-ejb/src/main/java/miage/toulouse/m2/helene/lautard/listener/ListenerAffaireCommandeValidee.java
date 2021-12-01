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
import miage.toulouse.m2.helene.lautard.facades.ChequeFacadeLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.AffaireDTO;

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
    private ChequeFacadeLocal chequeFacade;
    
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
                // Création des chèques
                this.chequeFacade.creerCheque(aff.getMontant1(), "1", aff.getNumAffaire());
                this.chequeFacade.creerCheque(aff.getMontant2(), "2", aff.getNumAffaire());
            } catch (JMSException ex) {
                Logger.getLogger(ListenerAffaireCommandeValidee.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (message != null) {
            System.out.println("(GESTION COMPTABLE) Le message reçu n'est pas un ObjectMessage");
        }
    }
    
}
