/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.metier;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.toulouse.m2.helene.lautard.entities.Affaire;
import miage.toulouse.m2.helene.lautard.entities.Client;
import miage.toulouse.m2.helene.lautard.facades.AffaireFacadeLocal;
import miage.toulouse.m2.helene.lautard.facades.ClientFacadeLocal;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException;

/**
 *
 * @author Hélène
 */
@Stateless
public class GestionAffaire implements GestionAffaireLocal {

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private AffaireFacadeLocal affaireFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    public Affaire creerAffaire(Client client, String lieuPose) throws ClientNotFoundException {
        try {
            Client clt = this.findClient(client.getNumclient());
            Affaire affaire = this.affaireFacade.creerAffaire(clt, lieuPose);
            return affaire;
        } catch (ClientNotFoundException ex) {
            throw(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Client findClient(int numClient) throws ClientNotFoundException {
        Client client = this.clientFacade.findClientByNum(numClient);
        if (client == null) {
            throw new ClientNotFoundException();
        }
        return client;
    }

    @Override
    public Client creerClient(String nom, String prenom, String mail, String adresseP, String telephone) {
        return this.clientFacade.creerClient(nom, prenom, mail, adresseP, telephone);
    }

}
