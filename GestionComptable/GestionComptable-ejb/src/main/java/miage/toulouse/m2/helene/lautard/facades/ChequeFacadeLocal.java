/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.entities.Cheque;

/**
 *
 * @author Hélène
 */
@Local
public interface ChequeFacadeLocal {

    Cheque create(Cheque cheque);

    void edit(Cheque cheque);

    void remove(Cheque cheque);

    Cheque find(Object id);

    List<Cheque> findAll();

    List<Cheque> findRange(int[] range);

    int count();
    
    /**
     * Création d'un nouveau chèque
     * @param montant montant du chèque
     * @param rangencaissement range de l'encaissement sur l'affaire
     * @param keynumaffaire numéro de l'affaire concernée 
     * @return Cheque
     */
    Cheque creerCheque(float montant, String rangencaissement, int keynumaffaire);
}
