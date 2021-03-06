/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.listener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Hélène
 */
@MessageDriven(mappedName = "QUEUE_CHEQUES_A_ENCAISSER", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QUEUE_CHEQUES_A_ENCAISSER")
})
public class ListenerChequesAEncaisser implements MessageListener {
        
    public ListenerChequesAEncaisser() {
    }
    
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage msg = (TextMessage) message;
            try {
                System.out.println(" \t (Gestion Comptable) Received : " + msg.getText() + " at " + java.time.LocalDateTime.now());
            } catch (JMSException ex) {
                System.err.println("Failed to get message text: " + ex );
            }
        } else if (message != null) {
            System.out.println("Non Text Message Received");
        }
    }
    
}
