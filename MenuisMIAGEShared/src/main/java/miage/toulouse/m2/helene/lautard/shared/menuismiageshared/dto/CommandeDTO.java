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
public class CommandeDTO implements Serializable {
    
    int numCommande; 
    String cotes; 
    float montant; 
    int numAffaire; 
    int numMenuiserie;
    int numClient;

    public CommandeDTO(int numCommande, String cotes, float montant, int numAffaire, int numMenuiserie) {
        this.numCommande = numCommande;
        this.cotes = cotes;
        this.montant = montant;
        this.numAffaire = numAffaire;
        this.numMenuiserie = numMenuiserie;
    }

    public CommandeDTO(String cotes, float montant, int numAffaire, int numMenuiserie) {
        this.cotes = cotes;
        this.montant = montant;
        this.numAffaire = numAffaire;
        this.numMenuiserie = numMenuiserie;
    }

    public CommandeDTO(String cotes, float montant, int numAffaire, int numMenuiserie, int numClient) {
        this.cotes = cotes;
        this.montant = montant;
        this.numAffaire = numAffaire;
        this.numMenuiserie = numMenuiserie;
        this.numClient = numClient;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }
    
    

    
    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public String getCotes() {
        return cotes;
    }

    public void setCotes(String cotes) {
        this.cotes = cotes;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getNumAffaire() {
        return numAffaire;
    }

    public void setNumAffaire(int numAffaire) {
        this.numAffaire = numAffaire;
    }

    public int getNumMenuiserie() {
        return numMenuiserie;
    }

    public void setNumMenuiserie(int numMenuiserie) {
        this.numMenuiserie = numMenuiserie;
    }

    
}
