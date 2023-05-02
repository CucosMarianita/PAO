package Service;

import Interfaces.ExpozitieTemporaraInterface;
import entities.ExpozitieTemporara;

import java.util.*;

public class ExpozTemporaraService implements ExpozitieTemporaraInterface {

    private List<ExpozitieTemporara> expozitiiTemporare;
    private static ExpozTemporaraService instance;


    public static ExpozTemporaraService getInstance(){
        if(instance == null){
            instance = new ExpozTemporaraService();
        }
        return instance;
    }

    public int getMaxId(){
        int max = 0;
        for (ExpozitieTemporara e : expozitiiTemporare) {
            if (e.getID_expozitie() > max) {
                max = e.getID_expozitie();
            }
        }
        return max;
    }


    @Override
    public ExpozitieTemporara readExpozitieTemp() {

        Scanner scanner = new Scanner(System.in);
        ExpozitieTemporara expozTemp = new ExpozitieTemporara();

        int id = getMaxId() + 1;
        expozTemp.setID_expozitie(id);

        System.out.println("Descriere: ");
        expozTemp.setDescriere(scanner.nextLine());

        System.out.println("ID_tur: ");
        while (true) {
            try {
                expozTemp.setID_tur(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("ID-ul trebuie sa fie un numar!");
            }
        }

        System.out.println("Perioada: ");
        expozTemp.setPerioada(scanner.nextLine());

        return expozTemp;
    }

    @Override
    public List<ExpozitieTemporara> getExpozitiiTemp() {
        List<ExpozitieTemporara> expTempCopy = new ArrayList<>();
        expTempCopy.addAll(this.expozitiiTemporare);
        // sortare dupa id
        Collections.sort(expTempCopy, new Comparator<ExpozitieTemporara>() {
            @Override
            public int compare(ExpozitieTemporara o1, ExpozitieTemporara o2) {
                return o1.getID_expozitie() - o2.getID_expozitie();
            }
        });
        return expTempCopy;
    }

    @Override
    public ExpozitieTemporara getExpozitieTempById(int index) {
        ExpozitieTemporara expozTemp = new ExpozitieTemporara();
        for (ExpozitieTemporara e : expozitiiTemporare) {
            if (e.getID_expozitie() == index) {
                expozTemp = e;
            }
        }
        return expozTemp;
    }

    @Override
    public void addExpozitieTemp(ExpozitieTemporara expozitieTemp) {
        this.expozitiiTemporare.add(expozitieTemp);
    }

    @Override
    public void updateExpozitieTemp(int index, ExpozitieTemporara expozitieTemp) {
        for(int i = 0; i < this.expozitiiTemporare.size(); ++i){
            if(this.expozitiiTemporare.get(i).getID_expozitie() == index){
                this.expozitiiTemporare.remove(i);
                this.expozitiiTemporare.add(i, expozitieTemp);
                break;
            }
        }
    }

    @Override
    public void deleteExpozitieTempById(int index) {
        for(int i = 0; i < this.expozitiiTemporare.size(); ++i){
            if(this.expozitiiTemporare.get(i).getID_expozitie() == index){
                this.expozitiiTemporare.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteExpozitieTemp(ExpozitieTemporara expozitieTemp) {
        this.expozitiiTemporare.remove(expozitieTemp);
    }
}
