/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.connexion3A18.interfaces;

import java.util.List;

/**
 *
 * @author Aziza
 * @param <T>
 */
public interface EntityCRUD<T> {
    public void addEntity(T t);
    public List<T> entitiesList();
}
