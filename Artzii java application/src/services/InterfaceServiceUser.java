/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;
import models.User;

/**
 *
 * @author medmo
 */
public interface InterfaceServiceUser {
    
    public boolean ajouterUser(User user) throws SQLException;

    public void modifierUser(User user) throws SQLException;
    
    public User get(int id);
}
