package Interfaces;

import entities.Angajat;

import java.util.List;
import java.util.Map;

public interface AngajatInterface {

    public Angajat readAngajat();

    public Map<Integer, String> getGhizi();

    public List<Angajat> getAngajati();

    public Angajat getAngajatById(int index);


}

