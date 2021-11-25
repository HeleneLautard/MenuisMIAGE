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
public class AffaireNotFoundException extends Exception {

    public AffaireNotFoundException() {
        System.err.println("ERREUR: L'affaire demandé n'existe pas");
    }
    
}
