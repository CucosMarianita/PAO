package entities;

public class Muzeu {
    private String nume;
    private String locatie;
    private String program;
    private String descriere;

    public Muzeu(){}
    public Muzeu(String nume, String locatie, String program, String descriere) {
        this.nume = nume;
        this.locatie = locatie;
        this.program = program;
        this.descriere = descriere;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return "Muzeu: " + '\n' +
                "   Nume: " + nume + '\n' +
                "   Locatie: " + locatie + '\n' +
                "   Program: " + program + '\n' +
                "   Descriere: " + descriere + '\n';
    }
}
