package Interfaces;

import entities.Relicva;

import java.util.List;

public interface RelicvaInterface {

    public Relicva readRelicva();


    public List<Relicva> getRelicve();

    public Relicva getRelicvaById(int index);


    // CRUD
    public void addRelicva(Relicva relicva);

    public void updateRelicva(int index, Relicva relicva);

    public void deleteRelicvaById(int index);

    public void deleteRelicva(Relicva relicva);
}
