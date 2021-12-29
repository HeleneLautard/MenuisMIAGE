/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.exposition;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.toulouse.m2.helene.lautard.planning.metier.GestionPlanningLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.AffaireDTO;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CalendrierNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CommercialNotAvailableException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CreneauNotFoundException;

/**
 *
 * @author Hélène
 */
@Stateless
public class ServicePlanning implements ServicePlanningLocal {

    @EJB
    private GestionPlanningLocal gestionPlanning;

    private Gson gson;

    public ServicePlanning() {
    }

    public ServicePlanning(Gson gson) {
        this.gson = gson;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String findCreneauxDispoCom() {
        return this.gson.toJson(this.gestionPlanning.findCreneauxDispoCom());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String bloquerCreneauCommercial(String content, int idCommercial, int idCreneau) throws CalendrierNotFoundException, CreneauNotFoundException, CommercialNotAvailableException {
        try {
            AffaireDTO numAffaire = this.gson.fromJson(content, AffaireDTO.class);
            return this.gson.toJson(this.gestionPlanning.bloquerCreneauCommercial(idCommercial, idCreneau, numAffaire.getNumAffaire()));
        } catch (CalendrierNotFoundException | CreneauNotFoundException | CommercialNotAvailableException ex) {
            throw ex;
        }
    }

}
