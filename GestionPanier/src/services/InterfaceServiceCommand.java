/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.Command;

/**
 *
 * @author medmo
 */
public interface InterfaceServiceCommand {
    
     public void ajouter(Command c);
     //public Command recupererCommClient(int idClient);
     public void supprimer(int id);
     //public Command get(int id);
     public List<Command> afficherCommands();
     public void modifierCommand(Command c);
}
