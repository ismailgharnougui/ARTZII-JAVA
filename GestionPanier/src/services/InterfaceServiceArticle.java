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
    
    public void ajouter(Article article);
    public Article get(int id);
    public List<Article> getArticles(int id);
    public List<Article> afficherArticles();
    public void supprimerArticle(int idArtiste, int idArticle);
    
}
