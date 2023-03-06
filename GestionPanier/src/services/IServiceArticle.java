package services;

import models.Artiicle;
import java.util.List;

public interface IServiceArticle {

    public void ajouterArticle(Artiicle s);

    public List<Artiicle> afficherArticle();

    public void modifierArticle(Artiicle s);

    public void supprimerArticle(Artiicle c);

    public boolean getArticle(Artiicle c);

    public List<Artiicle> getArticleByCategorie();
}
