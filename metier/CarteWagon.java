package metier;

public class CarteWagon {
    String couleur;
    String imgRecto;
    String imgVerso;

    public CarteWagon(String couleur, String imgRecto, String imgVerso)
    {
        this.couleur = couleur;
        this.imgRecto = imgRecto;
        this.imgVerso = imgVerso;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
     
    public String getImgRecto() {
        return this.imgRecto;
    }

    public void setImgRecto(String imgRecto) {
        this.imgRecto = imgRecto;
    }

    public String getImgVerso() {
        return this.imgVerso;
    }

    public void setImgVerso(String imgVerso) {
        this.imgVerso = imgVerso;
    }

}
