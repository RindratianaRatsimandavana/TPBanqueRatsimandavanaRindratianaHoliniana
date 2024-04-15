/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueratsimandavanarindratianaholiniana.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.entity.CompteBancaire;
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
        if (source != null && destination != null) {
            gestionnaireCompte.transferer(source, destination, (int) montant);
            return "listeComptes?faces-redirect=true";
        } else {
            // Gérer les cas où les comptes source ou destination n'existent pas
            return null;
        }
    }

}
