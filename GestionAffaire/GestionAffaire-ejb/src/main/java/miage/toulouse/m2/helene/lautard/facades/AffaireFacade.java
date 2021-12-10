/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.toulouse.m2.helene.lautard.entities.Affaire;
import miage.toulouse.m2.helene.lautard.entities.Client;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto.CommandeDTO;

/**
 *
 * @author Hélène
 */
@Stateless
public class AffaireFacade extends AbstractFacade<Affaire> implements AffaireFacadeLocal {

    @EJB
    private ClientFacadeLocal clientFacade;

    @PersistenceContext(unitName = "miage.toulouse.m2.eai_GestionAffaire-ejb_ejb_1.0PU")
    private EntityManager em;
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AffaireFacade() {
        super(Affaire.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Affaire creerAffaire(Client client, String lieuPose) {
        Affaire affaire = new Affaire(lieuPose, "créée", client);
        return this.create(affaire);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Affaire renseignerCommande(Affaire affaire, CommandeDTO commande) {
        affaire.setKeynumcommande(commande.getNumCommande());
        affaire.setStatut("Commande passée");
        this.edit(affaire);
        return affaire;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Affaire findAffaireByNum(int numAffaire) {
        List<Affaire> res = em.createNamedQuery("Affaire.findByNumaffaire")
                .setParameter("numaffaire", numAffaire)
                .getResultList();
        return res.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Affaire checkClientAffaire(int numAffaire, int numClient) {
        Client client = this.clientFacade.findClientByNum(numClient);
        List<Affaire> res = this.em.createNamedQuery("Affaire.checkClientAffaire")
                .setParameter("numaffaire", numAffaire)
                .setParameter("numclient", client)
                .getResultList();
        return res.get(0);
    }
    
    
}
