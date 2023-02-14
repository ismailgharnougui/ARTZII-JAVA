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
    public void ajouterArticle(Article a);
    public void supprimerArticle(int id);
    public List<Article> afficherArticles();
    public Article get(int id);
    
    
}
