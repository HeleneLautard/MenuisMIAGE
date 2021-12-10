/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.facades;

import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.planning.entities.Dispoposeur;

/**
 *
 * @author Hélène
 */
@Local
public interface DispoposeurFacadeLocal {

    Dispoposeur create(Dispoposeur dispoposeur);

    Dispoposeur edit(Dispoposeur dispoposeur);

    void remove(Dispoposeur dispoposeur);

    Dispoposeur find(Object id);

    List<Dispoposeur> findAll();

    List<Dispoposeur> findRange(int[] range);

    int count();
    
}
