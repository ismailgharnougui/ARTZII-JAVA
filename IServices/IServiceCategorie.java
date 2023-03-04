package IServices;

import Entities.Categorie;
import java.util.List;

public interface IServiceCategorie {

    public String ajouterCategorie(Categorie c);

    public List<Categorie> afficherCategorie();

    public void modifierCategorie(Categorie c);

    public void supprimerCategorie(Categorie c);

    public boolean getCategorie(Categorie c);
}
