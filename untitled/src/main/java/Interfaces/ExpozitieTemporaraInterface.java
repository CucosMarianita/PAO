package Interfaces;

import entities.ExpozitieTemporara;

import java.util.List;

public interface ExpozitieTemporaraInterface {

    public ExpozitieTemporara readExpozitieTemp();


    public List<ExpozitieTemporara> getExpozitiiTemp();

    public ExpozitieTemporara getExpozitieTempById(int index);


    // CRUD
    public void addExpozitieTemp(ExpozitieTemporara expozitieTemp);

    public void updateExpozitieTemp(int index, ExpozitieTemporara expozitieTemp);

    public void deleteExpozitieTempById(int index);

    public void deleteExpozitieTemp(ExpozitieTemporara expozitieTemp);

}



