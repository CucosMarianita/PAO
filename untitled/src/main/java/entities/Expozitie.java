package entities;

public class Expozitie {
    protected String descriere; // numele expozitiei
    protected int ID_expozitie;
    protected int ID_tur;

    public Expozitie(){}
    public Expozitie(String descriere, int ID_expozitie, int ID_tur) {
        this.descriere = descriere;
        this.ID_expozitie = ID_expozitie;
        this.ID_tur = ID_tur;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getID_expozitie() {
        return ID_expozitie;
    }

    public void setID_expozitie(int ID_expozitie) {
        this.ID_expozitie = ID_expozitie;
    }

    public int getID_tur() {
        return ID_tur;
    }

    public void setID_tur(int ID_tur) {
        this.ID_tur = ID_tur;
    }

    @Override
    public String toString() {
        return "Expozitie permanenta: " +
                "   Descriere: " + descriere + '\n' +
                "   *ID_expozitie: " + ID_expozitie + '\n' +
                "   *ID_tur: " + ID_tur + '\n';
    }
}
