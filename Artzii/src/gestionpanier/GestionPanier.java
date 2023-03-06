/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpanier;

import java.sql.SQLException;
import models.Article;
import models.Command;
import services.CRUDReclamation;
import services.CRUDReponse;
import services.ServiceArticle;
import services.ServiceBasket;
import services.ServiceUser;
import services.ServiceCommand;

/**
 *
 * @author medmo
 */
public class GestionPanier {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        Command c =new Command(4,"Livré");
        
        ServiceArticle sa = new ServiceArticle();
        Article a = new Article(19,26 , "mod2", "mod2", 12, "url2");
        //sa.modifierArticle(a);
        //System.out.println( sa.get(2));
        //sa.afficherArticles();
        //sa.getArticles(12);
        //sa.ajouter(new Article(12,"Article","50*60",45,"czdzdzdzd"));
        //sa.supprimerArticle(12, 8);
        
        ServiceUser scl = new ServiceUser();
        scl.afficherUtilisateur();
        //scl.supprimerUtilisateur(9);
        //System.out.println(scl.get(4));
        //System.out.println(scl.getByEmail("mokl@gmail.cim"));
        //scl.ajouterUser(new User("moklll", "mplll", "mokh11@gmail.com", "Mokhtar1234", "Client", "Manzel Bourguiba"));
        //System.out.println(scl.login("mokh11@gmail.com", "Mokhtar1234"));//scl.login("mokh11@gmail.com", "Mokhtar1234");
        
        ServiceBasket sb = new ServiceBasket();
        //sb.ajouter(12, 7);
        //sb.vider(2);
        //sb.supprimerArticle(4,6);
        //System.out.println(sb.get(4));
        
        ServiceCommand sc = new ServiceCommand();
        // sc.ajouter(c);
        //sc.supprimer(3);
        //System.out.println(sc.recupererCommClient(2));      
        //sc.afficherCommands();
        //sc.modifierCommand(c);   
        
        CRUDReclamation rec = new CRUDReclamation();
        //rec.afficherReclamation();
        
        CRUDReponse rep = new CRUDReponse();
        //rep.afficherReponse();
    }
    
}