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
public class WrongTotalAmountException extends Exception {

    public WrongTotalAmountException() {
        System.err.println("ERREUR : Le total des montant saisi ne correspond pas au montant de la commande.");
    }
    
}
