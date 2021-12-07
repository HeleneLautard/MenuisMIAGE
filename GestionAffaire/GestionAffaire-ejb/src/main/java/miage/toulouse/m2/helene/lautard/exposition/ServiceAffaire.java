/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.exposition;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
        CommandeDTO commande = this.gson.fromJson(content, CommandeDTO.class);
        return this.gson.toJson(this.gestionAffaire.renseignerCommande(commande.getNumAffaire(), commande.getNumClient(), commande.getNumMenuiserie(), commande.getCotes(), commande.getMontant()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validerCommande(String numAffaire, String content) throws AffaireNotFoundException, WrongTotalAmountException {
        ChequesCommandeDTO cheques = this.gson.fromJson(content, ChequesCommandeDTO.class);
        this.gestionAffaire.validerCommande(Integer.parseInt(numAffaire), cheques.getMontantCheque1(), cheques.getMontantCheque2());
    }

}
