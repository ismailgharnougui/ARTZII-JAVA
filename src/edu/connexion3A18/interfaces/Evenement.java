/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A18.interfaces;

import java.util.List;
import static javafx.scene.input.KeyCode.H;

/**
 *
 * @author ademm
 * @param <E>
 */
public interface Evenement <E> {
      public void addEntity(E e);

    public List<E> EvenementList();
    
}
