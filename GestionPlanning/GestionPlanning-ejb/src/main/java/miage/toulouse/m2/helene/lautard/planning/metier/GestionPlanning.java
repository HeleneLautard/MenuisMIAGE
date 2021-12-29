/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.toulouse.m2.helene.lautard.planning.entities.Calendrier;
import miage.toulouse.m2.helene.lautard.planning.entities.Dispocommercial;
import miage.toulouse.m2.helene.lautard.planning.facades.CalendrierFacadeLocal;
import miage.toulouse.m2.helene.lautard.planning.facades.DispocommercialFacadeLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.PlanningCommercialDTO;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CalendrierNotFoundException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CommercialNotAvailableException;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.CreneauNotFoundException;

/**
 *
 * @author Hélène
 */
@Stateless
public class GestionPlanning implements GestionPlanningLocal {

    @EJB
    private DispocommercialFacadeLocal dispocommercialFacade;

    @EJB
    private CalendrierFacadeLocal calendrierFacade;
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Calendrier creerCreneauCalendrier(Date dateHeureDeb, Date dateHeureFin) {
        return this.calendrierFacade.creerCreneauCalendrier(dateHeureDeb, dateHeureFin);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Calendrier> getAllCreneauxCalendrier() {
        return this.calendrierFacade.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispocommercial creerDispoCommercial(int numCommercial, Calendrier creneau) {
        return this.dispocommercialFacade.creerDispoCommercial(numCommercial, creneau);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispocommercial bloquerCreneauCommercial(int numCommercial, int numCreneau, int numAffaire) throws CalendrierNotFoundException, CreneauNotFoundException, CommercialNotAvailableException {
        // Chercher le créneau du calendrier correspondant 
        Calendrier creneau = this.calendrierFacade.findCreneauByNum(numCreneau);
        if(creneau == null){
            throw new CalendrierNotFoundException();
        }
        // Vérifier la combinaison (créneau, commercial, dispo)
        Dispocommercial dispoCom;
        try {
            dispoCom = this.checkCreneauDispo(numCommercial, numCreneau);
        } catch (CreneauNotFoundException | CommercialNotAvailableException ex) {
            throw ex;
        }
        // Bloquer le créneau
        return this.dispocommercialFacade.bloquerCreneauCommercial(dispoCom, numAffaire);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PlanningCommercialDTO> findCreneauxDispoCom() {
        List<Dispocommercial> listeDispo = this.dispocommercialFacade.findCreneauxDispoCom();
        List<PlanningCommercialDTO> res = new ArrayList();
        for(Dispocommercial d : listeDispo){
            PlanningCommercialDTO newDTO = new PlanningCommercialDTO(d.getNumcommercial(),d.getCalendrieridcalendrier().getIdcalendrier(), d.getCalendrieridcalendrier().getDateheuredeb(), d.getCalendrieridcalendrier().getDateheurefin(), d.getStatut());
            res.add(newDTO);
        }
        return res;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<PlanningCommercialDTO> findCreneauxCom(int numCommercial) {
        List<Dispocommercial> listCreneaux = this.dispocommercialFacade.findCreneauxCom(numCommercial);
        List<PlanningCommercialDTO> res = new ArrayList();
        for(Dispocommercial d : listCreneaux){
            PlanningCommercialDTO newDTO = new PlanningCommercialDTO(d.getNumcommercial(),d.getCalendrieridcalendrier().getIdcalendrier(), d.getCalendrieridcalendrier().getDateheuredeb(), d.getCalendrieridcalendrier().getDateheurefin(), d.getStatut());
            if(d.getNumaffaire() != null){
                newDTO.setNumAffaire(d.getNumaffaire());
            }
            res.add(newDTO);
        }
        return res;
    }

    /**
     * Vérification de la disponibilité d'un créneau
     * @param numCommercial numéro de commercial recherché
     * @param numCreneau numéro du créneau du calendrier
     * @return DispoCommercial
     * @throws CreneauNotFoundException
     * @throws CommercialNotAvailableException 
     */
    private Dispocommercial checkCreneauDispo(int numCommercial, int numCreneau) throws CreneauNotFoundException, CommercialNotAvailableException {
        // Get dispo selon numComercial et numCreneau 
        Dispocommercial dispo = this.dispocommercialFacade.findDispoByCommercialCreneau(numCommercial, numCreneau);
        if(dispo == null){
            throw new CreneauNotFoundException();
        }
        // Vérifier dispo du créneau
        if(dispo.getStatut()==false){
            throw new CommercialNotAvailableException();
        }
        return dispo;
    }

}
