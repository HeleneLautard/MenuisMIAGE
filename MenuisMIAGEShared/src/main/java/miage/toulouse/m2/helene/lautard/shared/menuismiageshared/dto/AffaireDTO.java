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
public class AffaireDTO implements Serializable {
    
    int numAffaire;
    String lieuPose; 
    String statut;
    int numClient; 
    int numCommande;
    float montant1;
    float montant2;

    public AffaireDTO(int numAffaire, String lieuPose, String statut, int numClient,int numCommande, float montant1, float montant2) {
        this.numAffaire = numAffaire;
        this.lieuPose = lieuPose;
        this.statut = statut;
        this.numClient = numClient;
        this.numCommande = numCommande;
        this.montant1 = montant1;
        this.montant2 = montant2;
    }

    public AffaireDTO(int numAffaire, String lieuPose, String statut, int numClient, int numCommande) {
        this.numAffaire = numAffaire;
        this.lieuPose = lieuPose;
        this.statut = statut;
        this.numClient = numClient;
        this.numCommande = numCommande;
    }

    public AffaireDTO(int numCommande) {
        this.numCommande = numCommande;
    }

    public AffaireDTO() {
        
    }

    
    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    
    public int getNumAffaire() {
        return numAffaire;
    }

    public void setNumAffaire(int numAffaire) {
        this.numAffaire = numAffaire;
    }

    public String getLieuPose() {
        return lieuPose;
    }

    public void setLieuPose(String lieuPose) {
        this.lieuPose = lieuPose;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }



    public float getMontant1() {
        return montant1;
    }

    public void setMontant1(float montant1) {
        this.montant1 = montant1;
    }

    public float getMontant2() {
        return montant2;
    }

    public void setMontant2(float montant2) {
        this.montant2 = montant2;
    }

    @Override
    public String toString() {
        return "AffaireDTO{" + "numAffaire=" + numAffaire + ", lieuPose=" + lieuPose + ", statut=" + statut + ", numClient=" + numClient + ", montant1=" + montant1 + ", montant2=" + montant2 + '}';
    }

    
    
    
    
           
    
}
