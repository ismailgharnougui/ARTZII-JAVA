/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entites.Article;
import java.util.List;

/**
 *
 * @author belkn
 */
public interface IArticle {
    public void ajouterArticle(Article A);
    public void ajouterArticle2 (Article A);
        public void ajouterArticle3(Article A);

   
    public void modifierArticle(Article A);
    public void supprimerArticle(int id);
    public List<Article> afficherArticle();
    public boolean getArticle(Article A);


    
}
