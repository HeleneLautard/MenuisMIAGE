/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.entities.Dispocommercial;

/**
 *
 * @author Hélène
 */
@Local
public interface DispocommercialFacadeLocal {

    void create(Dispocommercial dispocommercial);

    void edit(Dispocommercial dispocommercial);

    void remove(Dispocommercial dispocommercial);

    Dispocommercial find(Object id);

    List<Dispocommercial> findAll();

    List<Dispocommercial> findRange(int[] range);

    int count();
    
}
