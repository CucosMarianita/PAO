package Interfaces;

import entities.Tablou;

import java.util.List;

public interface TablouInterface {

    public Tablou readTablou();


    public List<Tablou> getTablouri();

    public Tablou getTablouById(int index);


    // CRUD
    public void addTablou(Tablou tablou);

    public void updateTablou(int index, Tablou tablou);

    public void deleteTablouById(int index);

    public void deleteTablou(Tablou tablou);

}
