package Interfaces;

import entities.Angajat;

import java.util.List;
import java.util.Map;

public interface AngajatInterface {

    public Angajat readAngajat();


    public Map<Integer, String> getGhizi();

    public List<Angajat> getAngajati();

    public Angajat getAngajatById(int index);


    // CRUD
    public void addAngajat(Angajat angajat);

    public void updateAngajat(int index, Angajat angajat);

    public void deleteAngajatById(int index);

    public void deleteAngajat(Angajat angajat);

}

