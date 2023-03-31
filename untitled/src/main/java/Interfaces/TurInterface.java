package Interfaces;

import entities.Tur;

import java.util.List;

public interface TurInterface {

    public Tur readTur();


    public List<Tur> getTururi();

    public Tur getTurById(int index);


    // CRUD
    public void addTur(Tur tur);

    public void updateTur(int index, Tur tur);

    public void deleteTurById(int index);

    public void deleteTur(Tur tur);
}
