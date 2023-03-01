/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.MyConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author medmo
 */
public class ServiceUser implements InterfaceServiceUser{
Statement ste =null;
Connection conn = MyConnection.getInstance().getConnection();

    @Override
    public User get(int id) {
          try {
        String req = "SELECT * FROM `utilisateur` WHERE idU = ?";
        PreparedStatement pste=conn.prepareStatement(req);
        pste.setInt(1, id);
        
        ResultSet result = pste.executeQuery();
       result.next();
           User resultUser = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(7));
        return resultUser;
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
    return null;
    }

    @Override
public boolean ajouterUser(User user) throws SQLException {
    // Vérifier si l'adresse e-mail de l'utilisateur n'existe pas déjà dans la base de données
    String selectSql = "SELECT COUNT(*) FROM utilisateur WHERE emailU = ?";
    try (PreparedStatement selectStatement = conn.prepareStatement(selectSql)) {
        selectStatement.setString(1, user.getEmail());
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        if (count > 0) {
        return false;
        }
    }

    // Calculer le hachage du mot de passe de l'utilisateur
    byte[] passwordHash = hashPassword(user.getPass());

    // Insérer l'utilisateur dans la base de données avec le hachage du mot de passe
    String insertSql = "INSERT INTO utilisateur (nomU, prenomU, emailU, mdpU, roleU, adresse) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement insertStatement = conn.prepareStatement(insertSql)) {
        insertStatement.setString(1, user.getNom());
        insertStatement.setString(2, user.getPrenom());
        insertStatement.setString(3, user.getEmail());
        insertStatement.setBytes(4, passwordHash);
        insertStatement.setString(5, user.getRole());
        insertStatement.setString(6, user.getAddress());

        insertStatement.executeUpdate();
    }
    return true;
}

private static byte[] hashPassword(String password) {
    // Generate a salt using jBCrypt's default strength (10)
    String salt = BCrypt.gensalt();
    
    // Hash the password using the generated salt and the bcrypt algorithm
    return BCrypt.hashpw(password, salt).getBytes();
}


    @Override
    public void modifierUser(User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerUser(String email) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> afficherUsers() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      public User getByEmail(String email) {
          try {
        String req = "SELECT * FROM `utilisateur` WHERE emailU = ?";
        PreparedStatement pste=conn.prepareStatement(req);
        pste.setString(1, email);
        
        ResultSet result = pste.executeQuery();
       result.next();
           User resultUser = new User(result.getInt(1), result.getString(4), result.getString(5));
        return resultUser;
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
    return null;
    }
      
      
      public User login(String email, String password) throws SQLException {
    // Get the user with the given email from the database
    String selectSql = "SELECT * FROM utilisateur WHERE emailU = ?";
    try (PreparedStatement selectStatement = conn.prepareStatement(selectSql)) {
        selectStatement.setString(1, email);
        ResultSet resultSet = selectStatement.executeQuery();
        if (!resultSet.next()) {
            System.out.println("Email inexistante");
            // User not found
            return null;
        }

        // Get the password hash for the user
        byte[] passwordHash = resultSet.getBytes("mdpU");

        // Check if the password is correct
        if (!checkPassword(password, passwordHash)) {
            System.out.println("Incorrect password");
            return null;
        }
        System.out.println("connected");        
        // Create and return a User object with the user's information
        User user = new User();
        user.setId(resultSet.getInt("idU"));
        user.setNom(resultSet.getString("nomU"));
        user.setPrenom(resultSet.getString("prenomU"));
        user.setEmail(resultSet.getString("emailU"));
        user.setRole(resultSet.getString("roleU"));
        user.setAddress(resultSet.getString("adresse"));
        return user;
    }
}
      private static boolean checkPassword(String password, byte[] passwordHash) {
    // Check if the password matches the hash using the bcrypt algorithm
    return BCrypt.checkpw(password, new String(passwordHash));
}

        }

