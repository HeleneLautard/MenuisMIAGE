/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.initializer;



import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import miage.toulouse.m2.helene.lautard.entities.Menuiserie;
import miage.toulouse.m2.helene.lautard.metier.GestionAchatLocal;
import miage.toulouse.m2.helene.lautard.sender.SenderAffairesCommandeReceptionnee;
import miage.toulouse.m2.helene.lautard.sender.SenderPremierChequesEncaisser;

/**
 *
 * @author Hélène
 */
@Singleton
@Startup
public class InitBeanGestionAchat {

    @EJB
    private GestionAchatLocal gestionAchat;
    
    SenderPremierChequesEncaisser senderCheque = new SenderPremierChequesEncaisser();
    SenderAffairesCommandeReceptionnee senderAffaire = new SenderAffairesCommandeReceptionnee();
    
    
    
    @PostConstruct
    public void initialiser() {
        //this.senderCheque.sendMsgEncaisserCheque1();
        //this.senderAffaire.sendMsgCommandeReceptionnee();
        
        Menuiserie portail = this.gestionAchat.creeerMenuiserie("Portail Electrique", "MenuisMIAGE Fabrik");
        Menuiserie fenetre = this.gestionAchat.creeerMenuiserie("Fenêtre coulissante", "Fenêtre Pro");
                
    }
}