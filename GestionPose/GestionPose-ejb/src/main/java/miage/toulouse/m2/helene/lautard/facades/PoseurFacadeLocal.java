/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.facades;

import java.util.List;
import javax.ejb.Local;
import miage.toulouse.m2.helene.lautard.entities.Poseur;

/**
 *
 * @author Hélène
 */
@Local
public interface PoseurFacadeLocal {

    void create(Poseur poseur);

    void edit(Poseur poseur);

    void remove(Poseur poseur);

    Poseur find(Object id);

    List<Poseur> findAll();

    List<Poseur> findRange(int[] range);

    int count();
    
}
