/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Article;
import java.util.List;

/**
 *
 * @author belkn
 */
public interface InterfaceServiceArticle {
    
    public List<Article> afficherArticles();
    public void ajouter(Article article);
    public void modifierArticle(Article article);
    public void supprimerArticle(int idArtiste, int idArticle);
    public Article get(int id);
    public List<Article> getArticles(int id);
    
    
    
}
