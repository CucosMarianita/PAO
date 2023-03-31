package entities;

public class Galerie {
    private String nume;
    private int ID_galerie;
    private int ID_expozitie;

    public Galerie(){}
    public Galerie(String nume, int ID_galerie, int ID_expozitie) {
        this.nume = nume;
        this.ID_galerie = ID_galerie;
        this.ID_expozitie = ID_expozitie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getID_galerie() {
        return ID_galerie;
    }

    public void setID_galerie(int ID_galerie) {
        this.ID_galerie = ID_galerie;
    }

    public int getID_expozitie() {
        return ID_expozitie;
    }

    public void setID_expozitie(int ID_expozitie) {
        this.ID_expozitie = ID_expozitie;
    }

    @Override
    public String toString() {
        return "Galerie: " + '\n' +
                "   Nume: " + nume + '\n' +
                "   *ID_galerie: " + ID_galerie + '\n' +
                "   *ID_expozitie: " + ID_expozitie + '\n';
    }
}
