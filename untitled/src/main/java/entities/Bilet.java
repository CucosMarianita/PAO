package entities;

import java.time.LocalDate;

public class Bilet {
    private int ID_bilet;
    private String tip; // adulti, copii, pensionari, studenti, elevi
    private int pret;
    private boolean achitat;
    private String descriere;
    private int ID_expozitie;
    private LocalDate data_achizitie;


    public Bilet(){
        this.data_achizitie = LocalDate.now();
    }

    public Bilet (int ID_bilet, String tip, int pret, boolean achitat, String descriere, int ID_expozitie){
        this.ID_bilet = ID_bilet;
        this.tip = tip;
        this.pret = pret;
        this.achitat = achitat;
        this.descriere = descriere;
        this.ID_expozitie = ID_expozitie;
        this.data_achizitie = LocalDate.now();
    }


    public int getID_bilet() {
        return ID_bilet;
    }

    public void setID_bilet(int ID_bilet) {
        this.ID_bilet = ID_bilet;
    }

    public LocalDate getData_achizitie() {
        return data_achizitie;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public boolean isAchitat() {
        return achitat;
    }

    public void setAchitat(boolean achitat) {
        this.achitat = achitat;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setData_achizitie(LocalDate data_achizitie) {
        this.data_achizitie = data_achizitie;
    }

    public int getID_expozitie() {
        return ID_expozitie;
    }

    public void setID_expozitie(int ID_expozitie) {
        this.ID_expozitie = ID_expozitie;
    }

    @Override
    public String toString() {
        return "Bilet: " + '\n' +
                "   Tip: " + tip + '\n' +
                "   Pret: " + pret + '\n' +
                "   Descriere: " + descriere +'\n' +
                "   *ID_expozitie: " + ID_expozitie +'\n';
    }
}
