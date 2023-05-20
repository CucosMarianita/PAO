package Interfaces;

import entities.ExpozitieTemporara;

import java.util.List;

public interface ExpozitieTemporaraInterface {

    public ExpozitieTemporara readExpozitieTemp();


    public List<ExpozitieTemporara> getExpozitiiTemp();

    public ExpozitieTemporara getExpozitieTempById(int index);


}



