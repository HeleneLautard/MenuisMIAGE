/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.shared.menuismiageshared.exceptions;

/**
 *
 * @author Hélène
 */
public class CreneauNotFoundException extends Exception {

    public CreneauNotFoundException() {
        System.err.println("ERREUR: Le créneau demandé ne correspond à aucune combinaise (numCommercial - numCalendrier)");
    }
    
}
