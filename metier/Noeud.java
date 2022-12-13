package metier;

public class Noeud {
    String nom;

    public Noeud(String nom)
    {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return "{" +
            " nom='" + getNom() + "'" +
            "}";
    }
}
