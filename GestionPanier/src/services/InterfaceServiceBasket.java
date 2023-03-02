/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import models.Article;

import java.util.List;
import models.Basket;

/**
 *
 * @author medmo
 */
public interface InterfaceServiceBasket {
    public boolean ajouter(int idClient, int idArticle);
    public void supprimerArticle(int idClient, int idArticle);
    public void vider(int idClient);
    public Basket get(int idClient);
}