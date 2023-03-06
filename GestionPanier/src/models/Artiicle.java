package models;

public class Artiicle {

    private int ArtId;
    private String ArtLib;
    private String ArtDesc;
    private int ArtDispo;
    private String ArtImg;
    private int ArtPrix;
    private String CatLib;
    private int CatId;

    public Artiicle() {
    }

    
    public Artiicle(int ArtId, String ArtLib, String ArtDesc, int ArtDispo, String ArtImg, int ArtPrix) {
        this.ArtId = ArtId;
        this.ArtLib = ArtLib;
        this.ArtDesc = ArtDesc;
        this.ArtDispo = ArtDispo;
        this.ArtImg = ArtImg;
        this.ArtPrix = ArtPrix;
    }

    public Artiicle(int ArtId, String ArtLib, String ArtDesc, int ArtDispo, String ArtImg, int ArtPrix, int CatId) {
        this.ArtId = ArtId;
        this.ArtLib = ArtLib;
        this.ArtDesc = ArtDesc;
        this.ArtDispo = ArtDispo;
        this.ArtImg = ArtImg;
        this.ArtPrix = ArtPrix;
        this.CatId = CatId;
    }

    public Artiicle(int ArtId, String ArtLib, String ArtDesc, int ArtDispo, String ArtImg, int ArtPrix, String CatLib) {
        this.ArtId = ArtId;
        this.ArtLib = ArtLib;
        this.ArtDesc = ArtDesc;
        this.ArtDispo = ArtDispo;
        this.ArtImg = ArtImg;
        this.ArtPrix = ArtPrix;
        this.CatLib = CatLib;
    }
    

    public Artiicle(String ArtLib, String ArtDesc, int ArtDispo, String ArtImg, int ArtPrix) {
        this.ArtLib = ArtLib;
        this.ArtDesc = ArtDesc;
        this.ArtDispo = ArtDispo;
        this.ArtImg = ArtImg;
        this.ArtPrix = ArtPrix;
    }
    
      public Artiicle(String ArtLib, String ArtDesc, int ArtDispo, String ArtImg, int ArtPrix,String catLib) {
        this.ArtLib = ArtLib;
        this.ArtDesc = ArtDesc;
        this.ArtDispo = ArtDispo;
        this.ArtImg = ArtImg;
        this.ArtPrix = ArtPrix;
        this.CatLib = catLib;
    }
    
    
    

    public Artiicle(String ArtLib, String ArtDesc, int ArtDispo, String ArtImg, int ArtPrix, String ArtVille, int CatId) {
        this.ArtLib = ArtLib;
        this.ArtDesc = ArtDesc;
        this.ArtDispo = ArtDispo;
        this.ArtImg = ArtImg;
        this.ArtPrix = ArtPrix;
        this.CatId = CatId;
    }

    public Artiicle(String ArtLib, String ArtDesc) {
        this.ArtLib = ArtLib;
        this.ArtDesc = ArtDesc;
    }

   

    public int getArtId() {
        return ArtId;
    }

    public void setArtId(int ArtId) {
        this.ArtId = ArtId;
    }

    public String getArtLib() {
        return ArtLib;
    }

    public void setArtLib(String ArtLib) {
        this.ArtLib = ArtLib;
    }

    public String getArtDesc() {
        return ArtDesc;
    }

    public void setArtDesc(String ArtDesc) {
        this.ArtDesc = ArtDesc;
    }

    public int getArtDispo() {
        return ArtDispo;
    }

    public void setArtDispo(int ArtDispo) {
        this.ArtDispo = ArtDispo;
    }

    public String getArtImg() {
        return ArtImg;
    }

    public void setArtImg(String ArtImg) {
        this.ArtImg = ArtImg;
    }

    public int getArtPrix() {
        return ArtPrix;
    }

    public void setArtPrix(int ArtPrix) {
        this.ArtPrix = ArtPrix;
    }


 

    public String getCatLib() {
        return CatLib;
    }

    public void setCatLib(String CatLib) {
        this.CatLib = CatLib;
    }
    

    @Override
    public String toString() {
        return "Article{" + "ArtId=" + ArtId + ",ArtLib=" + ArtLib + ", ArtDesc=" + ArtDesc + ", ArtDispo=" + ArtDispo + ", ArtImg=" + ArtImg + ", ArtPrix=" + ArtPrix + ", CatLib=" + CatLib + '}';
    }
}
