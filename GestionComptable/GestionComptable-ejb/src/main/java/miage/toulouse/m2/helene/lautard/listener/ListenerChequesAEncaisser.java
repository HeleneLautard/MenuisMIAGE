/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.listener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

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
    }
    
}
