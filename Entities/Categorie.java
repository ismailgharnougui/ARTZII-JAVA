package Entities;

public class Categorie {

    private int CatId;
    private String CatLib;

    public Categorie() {
    }

    public Categorie(int CatId, String CatLib) {
        this.CatId = CatId;
        this.CatLib = CatLib;
    }

    public Categorie(String CatLib) {
        this.CatLib = CatLib;
    }

    public int getCatId() {
        return CatId;
    }

    public void setCatId(int CatId) {
        this.CatId = CatId;
    }

    public String getCatLib() {
        return CatLib;
    }

    public void setCatLib(String CatLib) {
        this.CatLib = CatLib;
    }

    @Override
    public String toString() {
        return "Categorie{" + "CatId=" + CatId + ", CatLib=" + CatLib + "}\n";
    }
}
