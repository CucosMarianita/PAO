package entities;

public class Tur {
    private String descriere;
    private String durata;
    private int ID_tur;
    private int ID_ghid;

    public Tur(){}
    public Tur(String descriere, String durata, int ID_tur, int ID_ghid) {
        this.descriere = descriere;
        this.durata = durata;
        this.ID_tur = ID_tur;
        this.ID_ghid = ID_ghid;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public int getID_tur() {
        return ID_tur;
    }

    public void setID_tur(int ID_tur) {
        this.ID_tur = ID_tur;
    }

    public int getID_ghid() {
        return ID_ghid;
    }

    public void setID_ghid(int ID_ghid) {
        this.ID_ghid = ID_ghid;
    }

    @Override
    public String toString() {
        return "Tur: " + '\n' +
                "   Descriere: " + descriere + '\n' +
                "   Durata: " + durata + '\n' +
                "   *ID_tur: " + ID_tur + '\n' +
                "   *ID_ghid: " + ID_ghid + '\n';
    }
}
