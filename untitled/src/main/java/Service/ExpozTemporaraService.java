package Service;

import Interfaces.ExpozitieTemporaraInterface;
import Service.Persistence.CRUD_Template;
import Service.Persistence.Conn;
import entities.ExpozitieTemporara;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ExpozTemporaraService implements ExpozitieTemporaraInterface, CRUD_Template<ExpozitieTemporara> {

    private List<ExpozitieTemporara> expozitiiTemporare;
    private Conn connection;
    private static ExpozTemporaraService instance;

    private ExpozTemporaraService(){
        connection = new Conn();
        expozitiiTemporare = findAll();    // (citire din db)
    }

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
    public void add(ExpozitieTemporara obj) throws SQLException {
        this.expozitiiTemporare.add(obj);
        String sql = "INSERT INTO expozitie_temporara (ID_expozitie, perioada, descriere, ID_tur)" +
                "VALUES (" + obj.getID_expozitie() + ", '" + obj.getPerioada() + "', '" + obj.getDescriere() + "', " + obj.getID_tur() + ")";
        connection.getS().execute(sql);

    }

    @Override
    public List<ExpozitieTemporara> findAll() {
        try{
            String select = "SELECT * FROM expozitie_temporara";
            ResultSet rs = connection.getS().executeQuery(select);

            List<ExpozitieTemporara> expozitiiTemporare = new ArrayList<>();

            while(rs.next()){
                ExpozitieTemporara expozitieTemporara = new ExpozitieTemporara();
                expozitieTemporara.setID_expozitie(rs.getInt("ID_expozitie"));
                expozitieTemporara.setPerioada(rs.getString("perioada"));
                expozitieTemporara.setDescriere(rs.getString("descriere"));
                expozitieTemporara.setID_tur(rs.getInt("ID_tur"));
                expozitiiTemporare.add(expozitieTemporara);
            }
            rs.close();

            return expozitiiTemporare;
        }
        catch (SQLException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void update(ExpozitieTemporara obj) {
        this.expozitiiTemporare.set(obj.getID_expozitie()-1, obj);
        try{
            connection.getS().execute("UPDATE expozitie_temporara SET perioada = '" + obj.getPerioada() + "', descriere = '" + obj.getDescriere() +
                                    "', ID_tur = " + obj.getID_tur() +
                                    " WHERE ID_expozitie = " + obj.getID_expozitie() );

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        for(int i = 0; i < this.expozitiiTemporare.size(); ++i){
            if(this.expozitiiTemporare.get(i).getID_expozitie() == index){
                this.expozitiiTemporare.remove(i);
                break;
            }
        }
        try{
            connection.getS().execute("DELETE FROM expozitie_temporara WHERE ID_expozitie = " + index);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
