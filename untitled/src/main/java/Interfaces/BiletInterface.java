package Interfaces;

import entities.Bilet;

import java.util.List;

public interface BiletInterface {

    public Bilet readBilet();

    public List<Bilet> getBilete();

    public List<Bilet> getBiletePerioada(int nr_luni);

    public Bilet getBiletById(int index);


    // CRUD
    public void addBilet(Bilet bilet);

    public void updateBilet(int index, Bilet bilet);

    public void deleteBiletById(int index);

    public void deleteBilet(Bilet bilet);
}
