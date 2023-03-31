package Interfaces;

import entities.Expozitie;

import java.util.List;

public interface ExpozitieInterface {

    public Expozitie readExpozitie();


    public List<Expozitie> getExpozitii();

    public Expozitie getExpozitieById(int index);


    // CRUD
    public void addExpozitie(Expozitie expozitie);

    public void updateExpozitie(int index, Expozitie expozitie);

    public void deleteExpozitieById(int index);

    public void deleteExpozitie(Expozitie expozitie);
}
