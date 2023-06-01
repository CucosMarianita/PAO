package entities;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

public class Angajat extends User {

    private String CNP;
    private int salariu;
    private String departament;
    private String telefon;
    private int cod_ang;
    private int ID_ang;
    private LocalDate startDate;

    public Angajat(){
        startDate =  LocalDate.now();
    }

    public Angajat(String nume, String prenume, String CNP, int salariu, String departament, String telefon, int cod_ang, int ID_ang) {
        super(nume, prenume);
        this.CNP = CNP;
        this.salariu = salariu;
        this.departament = departament;
        this.telefon = telefon;
        this.cod_ang = cod_ang;
        this.ID_ang = ID_ang;
        this.startDate = LocalDate.now();
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public int getSalariu() {
        return salariu;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getCod_ang() {
        return cod_ang;
    }

    public void setCod_ang(int cod_ang) {
        this.cod_ang = cod_ang;
    }

    public int getID_ang() {
        return ID_ang;
    }

    public void setID_ang(int ID_ang) {
    this.ID_ang = ID_ang;
}

    public String getStartDate() {
        return startDate.toString();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Angajat: " + '\n' +
                "   Nume: " + nume + '\n' +
                "   Prenume: " + prenume + '\n' +
                "   CNP: " + CNP + '\n' +
                "   Start_Date: " + startDate + '\n' +
                "   Departament: " + departament + '\n' +
                "   Salariu: " + salariu + '\n' +
                "   Telefon: " + telefon + '\n' +
                "   *Cod_ang: " + cod_ang + '\n' +
                "   *ID angajat: " + ID_ang + '\n';
    }


    @Override
    public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if (ob == null || getClass() != ob.getClass())
            return false;

        Angajat angajat = (Angajat) ob;
        return CNP.equals(angajat.CNP)
                && nume.equals(angajat.nume)
                && prenume.equals(angajat.prenume);
    }


    // override hashCode() in order to avoid using different hashCode for equal objects
    @Override
    public int hashCode() {
        return Objects.hash(CNP, nume, prenume);
    }
}
