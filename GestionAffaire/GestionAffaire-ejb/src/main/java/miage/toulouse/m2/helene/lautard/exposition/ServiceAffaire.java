/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.exposition;

import com.google.gson.Gson;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import miage.toulouse.m2.helene.lautard.entities.Affaire;
import miage.toulouse.m2.helene.lautard.metier.GestionAffaireLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.ChequesCommandeDTO;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.CommandeDTO;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.AffaireNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongClientException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.WrongTotalAmountException;


/**
 *
 * @author Hélène
 */
@Stateless
public class ServiceAffaire implements ServiceAffaireLocal {

    @EJB
    private GestionAffaireLocal gestionAffaire;
    
    private Gson gson;

    public ServiceAffaire() {
        this.gson = new Gson();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String renseignerCommande(String content) throws AffaireNotFoundException, WrongClientException {
        try {
            CommandeDTO commande = this.gson.fromJson(content, CommandeDTO.class);
            return this.gson.toJson(this.gestionAffaire.renseignerCommande(commande.getNumAffaire(), commande.getNumClient(), commande.getNumMenuiserie(), commande.getCotes(), commande.getMontant()));
        } catch (JMSException ex) {
            Logger.getLogger(ServiceAffaire.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validerCommande(String numAffaire, String content) throws AffaireNotFoundException, WrongTotalAmountException {
        ChequesCommandeDTO cheques = this.gson.fromJson(content, ChequesCommandeDTO.class);
        this.gestionAffaire.validerCommande(Integer.parseInt(numAffaire), cheques.getMontantCheque1(), cheques.getMontantCheque2());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Affaire findAffaireByNum(int numAffaire) throws AffaireNotFoundException {
        try {
            return this.gestionAffaire.findAffaire(numAffaire);
        } catch (AffaireNotFoundException ex) {
            throw ex;
        }
    }
}
