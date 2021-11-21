/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.initializer;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import miage.toulouse.m2.helene.lautard.sender.SenderPremierChequesEncaisser;

/**
 *
 * @author Hélène
 */
@Singleton
@Startup
public class InitBean {
    
    SenderPremierChequesEncaisser sender = new SenderPremierChequesEncaisser();
    
    @PostConstruct
    public void initialiser() {
        this.sender.sendMsgChequesAEncaisser();
        
    }
}