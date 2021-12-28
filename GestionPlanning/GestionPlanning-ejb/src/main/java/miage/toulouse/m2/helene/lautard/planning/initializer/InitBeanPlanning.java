/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.planning.initializer;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import miage.toulouse.m2.helene.lautard.planning.entities.Calendrier;
import miage.toulouse.m2.helene.lautard.planning.metier.GestionPlanningLocal;

/**
 *
 * @author Hélène
 */
/*@Singleton
@Startup
*/
public class InitBeanPlanning {

    @EJB
    private GestionPlanningLocal gestionPlanning;
    
    /*@PostConstruct
    public void initialiser(){
        List<Calendrier> creneauxCalendier = this.gestionPlanning.getAllCreneauxCalendrier();
        
        for(Calendrier creneau: creneauxCalendier){
            for(int i=1; i<4; i++){
                this.gestionPlanning.creerDispoCommercial(i, creneau);
            }
        }
    }
*/
}
