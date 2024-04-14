/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanqueratsimandavanarindratianaholiniana.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.tpbanqueratsimandavanarindratianaholiniana.entity.CompteBancaire;

/**
 *
 * @author rindr
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "@Mysql1234", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@RequestScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public List<CompteBancaire> getAllCompteBancaires() {
        String s = "select c from CompteBancaire as c"; 
        TypedQuery<CompteBancaire> query = em.createQuery(s, CompteBancaire.class); 
        List<CompteBancaire> liste = query.getResultList(); // Exécute la requête et récupère les résultats sous forme de liste d'objets Employe
        return liste;
    }

    @Transactional
    public void creerCompte(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }
    
    public long nbComptes() {
        Query query = em.createQuery("SELECT COUNT(c) FROM Compte c");
        return (long) query.getSingleResult();
    }

}
