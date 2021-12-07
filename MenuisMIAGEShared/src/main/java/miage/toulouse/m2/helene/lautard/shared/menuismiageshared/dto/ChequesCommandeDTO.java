/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.toulouse.m2.helene.lautard.shared.menuismiageshared.dto;

import java.io.Serializable;
/**
 *
 * @author Hélène
 */
public class ChequesCommandeDTO implements Serializable {
    
    private float montantCheque1;
    private float montantCheque2;

    public ChequesCommandeDTO(float montantCheque1, float montantCheque2) {
        this.montantCheque1 = montantCheque1;
        this.montantCheque2 = montantCheque2;
    }

    public float getMontantCheque1() {
        return montantCheque1;
    }

    public void setMontantCheque1(float montantCheque1) {
        this.montantCheque1 = montantCheque1;
    }

    public float getMontantCheque2() {
        return montantCheque2;
    }

    public void setMontantCheque2(float montantCheque2) {
        this.montantCheque2 = montantCheque2;
    }
    
    
}
