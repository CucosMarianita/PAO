package entities;

public class Angajat extends User{
    private int CNP;
    private int salariu;
    private String departament;
    private int telefon;
    private int cod_ang;
    private int ID_ang;

    public Angajat(){}

    public Angajat(String nume, String prenume, int CNP, int salariu, String departament, int telefon, int cod_ang, int ID_ang) {
        super(nume, prenume);
        this.CNP = CNP;
        this.salariu = salariu;
        this.departament = departament;
        this.telefon = telefon;
        this.cod_ang = cod_ang;
        this.ID_ang = ID_ang;
    }

    public int getCNP() {
        return CNP;
    }

    public void setCNP(int CNP) {
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

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
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

    @Override
    public String toString() {
        return "Angajat: " + '\n' +
                "   Nume: " + nume + '\n' +
                "   Prenume: " + prenume + '\n' +
                "   CNP: " + CNP + '\n' +
                "   Departament: " + departament + '\n' +
                "   Salariu: " + salariu + '\n' +
                "   Telefon: " + telefon + '\n' +
                "   *Cod_ang: " + cod_ang + '\n' +
                "   *ID angajat: " + ID_ang + '\n';
    }
}
