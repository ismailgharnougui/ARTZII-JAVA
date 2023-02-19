/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpanier;

import java.util.ArrayList;
import models.Article;
import models.Client;
import models.Command;
import services.ServiceArticle;
import services.ServiceBasket;
import services.ServiceClient;
import services.ServiceCommand;

/**
 *
 * @author medmo
 */
public class GestionPanier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ServiceArticle sa = new ServiceArticle();
        //System.out.println( sa.get(2));
        
        ServiceClient scl = new ServiceClient();
        //System.out.println(scl.get(4));
        
        ServiceBasket sb = new ServiceBasket();
        //sb.ajouter(2, 5);
        //sb.vider(2);
        //sb.supprimerArticle(4,6);
        //System.out.println(sb.get(4).getArticles());
        
        ServiceCommand sc = new ServiceCommand();
        
           
        //sc.ajouter(command);
        //sc.supprimer(3);
        //System.out.println(sc.recupererCommClient(2));       
        
       
    }
    
}






















        
        /*Client cl = new Client(2,"ramzi", "mohsen", "123 Main St");
        ArrayList<Article> articles = new ArrayList<Article>();
        articles.add(new Article(2,"15*51", (float) 15.99));
        articles.add(new Article(3,"51*48", (float) 29.99));
        articles.add(new Article(4,"7*65", (float) 5.99));
        String comDate = "2023-02-14";
        double totalCost = 51.97;
        String payMethod = "Credit Card";
        Command command = new Command(cl, articles, comDate, totalCost, payMethod);*/
