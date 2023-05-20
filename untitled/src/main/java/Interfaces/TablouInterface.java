package Interfaces;

import entities.Tablou;

import java.util.List;

public interface TablouInterface {

    public Tablou readTablou();


    public List<Tablou> getTablouri();

    public Tablou getTablouById(int index);



}
