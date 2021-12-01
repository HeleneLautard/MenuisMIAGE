/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.initializer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import miage.toulouse.m2.helene.lautard.entities.Affaire;
import miage.toulouse.m2.helene.lautard.entities.Client;
import miage.toulouse.m2.helene.lautard.metier.GestionAffaireLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongClientException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException;

/**
 *
 * @author Hélène
 */
@Singleton
@Startup
public class InitBeanGestionAffaire {

    @EJB
    private GestionAffaireLocal gestionAffaire;
    
    
    @PostConstruct
    public void initialiser() {
        Client clientDupont = this.gestionAffaire.creerClient("Dupount", "Jean", "dupont@miage.fr", "9 rue des fleurs, Toulouse", "06.......");
        Client clientZ = this.gestionAffaire.creerClient("Monsieur", "Z", "z@miage.fr", "9 rue des lilas, Toulouse", "06.......");
        Client clientP = this.gestionAffaire.creerClient("Madame", "P", "p@miage.fr", "9 rue des tournesols", "06.......");
        Client clientGir = this.gestionAffaire.creerClient("Girardet", "Antho", "blond@miage.fr", "9 rue du Mont Blanc", "06.......");
        
        
        try {
            Affaire aff1 = this.gestionAffaire.creerAffaire(clientZ, clientZ.getAdressep());
            Affaire aff2 = this.gestionAffaire.creerAffaire(clientP, clientP.getAdressep());
            Affaire aff3 = this.gestionAffaire.creerAffaire(clientGir, clientGir.getAdressep());
            
            this.gestionAffaire.renseingerCommande(aff1.getNumaffaire(),clientZ.getNumclient() , 1, "230x180", 1589.2F);
            
            //this.gestionAffaire.validerCommande(aff1.getNumaffaire(), 1588.2F, 1);
        } catch (ClientNotFoundException | AffaireNotFoundException | WrongClientException ex) {
            Logger.getLogger(InitBeanGestionAffaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
