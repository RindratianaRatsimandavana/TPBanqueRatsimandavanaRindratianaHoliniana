/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueratsimandavanarindratianaholiniana.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.entity.CompteBancaire;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.jsf.util.Util;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.service.GestionnaireCompte;

/**
 *
 * @author rindr
 */
@Named(value = "transfert")
@RequestScoped
public class Transfert {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private Long idSource;
    private Long idDestination;
    private double montant;

    public Transfert() {
    }

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String effectuerTransfert() {
        CompteBancaire source = gestionnaireCompte.getCompteById(idSource);
        CompteBancaire destination = gestionnaireCompte.getCompteById(idDestination);
        boolean erreur = false;

        if (source == null) {
            Util.messageErreur("Aucun compte source avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        }

        if (destination == null) {
            Util.messageErreur("Aucun compte destination avec cet id !", "Aucun compte avec cet id !", "form:destination");
            erreur = true;
        }

        if (erreur) {
            return null; // En cas d'erreur, rester sur la même page
        }

        if (source.getSolde() < montant) {
            Util.messageErreur("Solde insuffisant pour le compte source", "Solde insuffisant pour le compte source", "form:montant");
            return null;
        }

        gestionnaireCompte.transferer(source, destination, (int) montant);

        // Message de succès avec redirection
        String message = "Transfert de " + montant + " effectué de " + source.getNom() + " à " + destination.getNom();
        Util.addFlashInfoMessage(message);

        return "listeComptes?faces-redirect=true";
    }

}
