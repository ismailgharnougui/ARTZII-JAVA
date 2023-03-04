package IServices;

import Entities.Article;
import java.util.List;

public interface IServiceArticle {

    public void ajouterArticle(Article s);

    public List<Article> afficherArticle();

    public void modifierArticle(Article s);

    public void supprimerArticle(Article c);

    public boolean getArticle(Article c);

    public List<Article> getArticleByCategorie();
}
