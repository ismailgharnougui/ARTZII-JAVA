package IServices;

import Entites.Categorie;
import java.util.List;

public interface ICategorie {

    public void ajouterCategorie(Categorie C);
    public void ajouterCategorie2(Categorie C);
    public void modifierCategorie(Categorie C);
    public void supprimerCategorie(int id);
    public List<Categorie> afficherCategorie();
    public boolean getCategorie(Categorie C);

}
