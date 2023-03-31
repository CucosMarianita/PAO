package Interfaces;

import entities.Sculptura;

import java.util.List;

public interface SculpturaInterface {

    public Sculptura readSculptura();


    public List<Sculptura> getSculpturi();

    public Sculptura getSculpturaById(int index);


    // CRUD
    public void addSculptura(Sculptura sculptura);

    public void updateSculptura(int index, Sculptura sculptura);

    public void deleteSculpturaById(int index);

    public void deleteSculptura(Sculptura sculptura);

}
