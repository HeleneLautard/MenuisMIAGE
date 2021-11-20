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
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Hélène
 */
@MessageDriven(mappedName = "QUEUE_CHEQUES_A_ENCAISSER", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ListenerChequesAEncaisser implements MessageListener {
    
    public ListenerChequesAEncaisser() {
    }
    
    @Override
    public void onMessage(Message message) {
        System.out.println("Message reçu - chèque à encaisser");
        if(message instanceof TextMessage){
            TextMessage msg = (TextMessage) message;
            try {
                System.out.println("Received : " + msg.getText());
            } catch (JMSException ex) {
                Logger.getLogger(ListenerChequesAEncaisser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (message != null) {
            System.out.println("Non Object Message Received");
        }
    }
    
}
