package services;

import models.Categorie;
import models.Artiicle;
import services.IServiceCategorie;
import db.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ServiceCategorie implements IServiceCategorie {

    Connection cnx;
ObservableList<Categorie>obList = FXCollections.observableArrayList();

    public ServiceCategorie() {
        cnx = MyConnection.getInstance().getConnection();
    }

    @Override
    public String ajouterCategorie(Categorie c) {
        String sql = " insert into categorie(CatLib)"
                + " values (?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, c.getCatLib());
            ps.executeUpdate();
            System.out.println("Catégorie ajouté avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return c.getCatLib();
    }

    @Override
    public ObservableList<Categorie> afficherCategorie() {
        String sql = "SELECT * FROM categorie";
        List<Categorie> listeCatg = new ArrayList<>();

        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int catid = result.getInt(1);
                String catlib = result.getString(2);
                Categorie c = new Categorie(catid, catlib);
                obList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obList;
    }

    @Override
    public void modifierCategorie(Categorie c) {
        String sql = " Update categorie set CatLib=? where CatId=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, c.getCatLib());
            ps.setInt(2, c.getCatId());
            ps.executeUpdate();
            System.out.println("Catégorie modifié avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerCategorie(Categorie c) {
        String sql = "DELETE from categorie WHERE CatId=?";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, c.getCatId());
            ps.executeUpdate();
            System.out.println("Catégorie supprimé avec succés !");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean getCategorie(Categorie c) {
        try {
            PreparedStatement ps;
            ps = cnx.prepareStatement("SELECT * FROM categorie WHERE CatLib = ?");
            ps.setString(1, c.getCatLib());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //System.out.println(rs.getString("CatLib"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
}
