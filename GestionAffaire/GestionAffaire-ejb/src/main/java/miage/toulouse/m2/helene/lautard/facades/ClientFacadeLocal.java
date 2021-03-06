/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.entities.Client;
import miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions.ClientNotFoundException;

/**
 *
 * @author Hélène
 */
@Local
public interface ClientFacadeLocal {

    Client create(Client client);

    void edit(Client client);

    void remove(Client client);

    Client find(Object id);

    List<Client> findAll();

    List<Client> findRange(int[] range);

    int count();
    
    /**
     * Recherche d'un client selon son numéro d'identification
     * @param numClient numéro client
     * @return Client
     */
    Client findClientByNum(int numClient);
    
    /**
     * Création d'un client
     * @param nom nom du client
     * @param prenom prenom du client
     * @param mail adresse mail du client
     * @param adresseP adresse postale du client
     * @param telephone numéro de téléphone du client
     * @return Client
     */
    Client creerClient(String nom, String prenom, String mail, String adresseP, String telephone);
    
}
