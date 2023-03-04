package Service;

import Entities.Categorie;
import Entities.Article;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import IServices.IServiceArticle;

public class ServiceArticle implements IServiceArticle {

    Connection cnx;
ObservableList<Article>obList = FXCollections.observableArrayList();
ObservableList<Article>obListCat = FXCollections.observableArrayList();

    public ServiceArticle() {
        cnx = Maconnexion.getInstance().getConnection();
    }

    public void ajouterArticle(Article s) {
        String sql = " insert into article(ArtLib, ArtDesc, ArtDispo, ArtImg, ArtPrix, CatLib)"
                + " values (?,?,?,?,?,?)";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, s.getArtLib());
            ps.setString(2, s.getArtDesc());
            ps.setInt(3, s.getArtDispo());
            ps.setString(4, s.getArtImg());
            ps.setInt(5, s.getArtPrix());
            ps.setString(6, s.getCatLib());
            ps.executeUpdate();
            System.out.println("Article ajouté avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Article> afficherArticle() {
        String sql = "SELECT * FROM article";
        List<Article> listeArticle = new ArrayList<>();

        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int ArtId = result.getInt(1);
                String ArtLib = result.getString(2);
                String ArtDesc = result.getString(3);
                int ArtDispo = result.getInt(4);
                String ArtImg = result.getString(5);
                int ArtPrix = result.getInt(6);
                String CatLib = result.getString(7);

                Article s = new Article(ArtId, ArtLib, ArtDesc, ArtDispo, ArtImg, ArtPrix, CatLib);
                obList.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obList;
    }

    @Override
    public void modifierArticle(Article s) {
        String sql = " Update article set ArtLib=?, ArtDesc=?,ArtDispo=?, ArtImg=?, ArtPrix=?, CatLib=? where ArtId=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, s.getArtLib());
            ps.setString(2, s.getArtDesc());
            ps.setInt(3, s.getArtDispo());
            ps.setString(4, s.getArtImg());
            ps.setInt(5, s.getArtPrix());
             ps.setString(6, s.getCatLib());
            ps.setInt(8, s.getArtId());
            ps.executeUpdate();
            System.out.println("Article modifié avec succés !");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerArticle(Article s) {
        String sql = "DELETE from article WHERE ArtId=?";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, s.getArtId());
            ps.executeUpdate();
            System.out.println("Article supprimé avec succés !");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean getArticle(Article s) {
        try {
            PreparedStatement ps;
            ps = cnx.prepareStatement("SELECT * FROM article WHERE ArtLib = ?");
            ps.setString(1, s.getArtLib());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //System.out.println(rs.getString("ServLib"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public ObservableList<Article> getArticleByCategorie() {
        String sql ="select * from article S "
                + "JOIN Categorie C ON C.CatLib=S.CatLib";
        List<Article> listeArticle = new ArrayList<>();

        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int Artid = result.getInt(1);
                String Artlib = result.getString(2);
                String Artdesc = result.getString(3);
                int Artdispo = result.getInt(4);
                String Artimg = result.getString(5);
                int Artprix = result.getInt(6);
                String catlib= result.getString(7);

                Article s = new Article(Artid, Artlib, Artdesc, Artdispo, Artimg, Artprix, catlib);
                obListCat.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obListCat;
    }
}
