/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanqueratsimandavanarindratianaholiniana.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.entity.CompteBancaire;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.jsf.util.Util;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.service.GestionnaireCompte;

/**
 *
 * @author rindr
 */
@Named(value = "modification")
@ViewScoped
public class Modification implements Serializable {

    private Long id;
    private CompteBancaire compte;
    private String nouveauNom;
    private String ancienNom;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public String getNouveauNom() {
        return nouveauNom;
    }

    public void setNouveauNom(String nouveauNom) {
        this.nouveauNom = nouveauNom;
    }
    
    public String getAncienNom() {
        return ancienNom;
    }

    public void setAncienNom() {
        this.ancienNom = this.getCompte().getNom();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void loadCompte() {
        compte = gestionnaireCompte.getCompteById(id);
        setAncienNom();
    }

    public String updateName() {
        gestionnaireCompte.modifierNom(compte, nouveauNom);
        Util.addFlashInfoMessage("Nom du possesseur du compte modifié de " + ancienNom + " en " + nouveauNom);
        return "listeComptes?faces-redirect=true";
    }

    /**
     * Creates a new instance of Modification
     */
    public Modification() {
    }

}
