/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import miage.toulouse.m2.helene.lautard.entities.Client;

/**
 *
 * @author Hélène
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> implements ClientFacadeLocal {

    @PersistenceContext(unitName = "miage.toulouse.m2.eai_GestionAffaire-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Client findClientByNum(int numClient) {
        List<Client> res = em.createNamedQuery("Client.findByNumclient")
                .setParameter("numclient", numClient)
                .getResultList();
        return res.get(0);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Client creerClient(String nom, String prenom, String mail, String adresseP, String telephone) {
        Client client = new Client(nom, prenom, mail, telephone, adresseP);
        return this.create(client);
    }
    
}
