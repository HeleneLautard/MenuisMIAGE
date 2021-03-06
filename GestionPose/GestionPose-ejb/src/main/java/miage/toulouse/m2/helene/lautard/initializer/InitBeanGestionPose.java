/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.initializer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import miage.toulouse.m2.helene.lautard.sender.SenderAffairePoseEffectuee;

/**
 *
 * @author Hélène
 */
@Singleton
@Startup
public class InitBeanGestionPose {
    
    SenderAffairePoseEffectuee sender = new SenderAffairePoseEffectuee();
    
    @PostConstruct
    public void initialiser() {
        this.sender.sendMsgPoseEffectuee();
    }
}
