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

/**
 *
 * @author Hélène
 */
@Singleton
@Startup
public class InitBeanGestionAchat {

    @EJB
    private GestionAchatLocal gestionAchat;
    
    
    @PostConstruct
    public void initialiser() {

        this.gestionAchat.creeerMenuiserie("Portail Electrique", "MenuisMIAGE Fabrik");
        this.gestionAchat.creeerMenuiserie("Fenêtre coulissante", "Fenêtre Pro");
        this.gestionAchat.creeerMenuiserie("Portail à battants", "PortailPro");
        this.gestionAchat.creeerMenuiserie("Fenêtre baie vitrée", "SudMenuiserie");
        this.gestionAchat.creeerMenuiserie("Fenêtre battant unique", "Fenêtre Pro");
                
    }
}