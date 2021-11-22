/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.entities.Commercial;

/**
 *
 * @author Hélène
 */
@Local
public interface CommercialFacadeLocal {

    void create(Commercial commercial);

    void edit(Commercial commercial);

    void remove(Commercial commercial);

    Commercial find(Object id);

    List<Commercial> findAll();

    List<Commercial> findRange(int[] range);

    int count();
    
}
