/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueratsimandavanarindratianaholiniana.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.entity.CompteBancaire;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.jsf.util.Util;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.service.GestionnaireCompte;

/**
 *
 * @author rindr
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private String nom;
    @PositiveOrZero(message = "Le solde doit être positif ou nul")
    private double solde;

    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String creerCompte() {
        if (solde < 0) {
            Util.messageErreur("Le solde doit être positif ou nul");
            return null;
        }

        CompteBancaire nouveauCompte = new CompteBancaire(nom, (int) solde);
        gestionnaireCompte.creerCompte(nouveauCompte);

        Util.addFlashInfoMessage("Nouveau compte créé pour " + nom + " avec un solde de " + solde);
        return "listeComptes?faces-redirect=true";
    }

}
