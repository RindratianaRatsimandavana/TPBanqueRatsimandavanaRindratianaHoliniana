/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueratsimandavanarindratianaholiniana.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.entity.CompteBancaire;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.jsf.util.Util;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.service.GestionnaireCompte;

/**
 *
 * @author rindr
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> allComptes;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public ListeComptes() {
    }

    /**
     * Retourne la liste des comptes pour affichage dans une DataTable.
     */
    public List<CompteBancaire> getAllComptes() {
        if (allComptes == null) {
            allComptes = gestionnaireCompte.getAllCompteBancaires();
        }
        return allComptes;
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        gestionnaireCompte.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }

}
