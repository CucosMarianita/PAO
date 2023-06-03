package Service;

import Interfaces.MuzeuInterface;
import Service.Persistence.CRUD_Template;
import entities.Muzeu;
import Service.Persistence.Conn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MuzeuService implements MuzeuInterface, CRUD_Template<Muzeu> {

    private List<Muzeu> muzee = new ArrayList<>();
    private Conn connection;
    private static MuzeuService instance;


    private MuzeuService(){
        connection = new Conn();
        muzee = findAll();    // (citire din db)
    }

    public static MuzeuService getInstance(){
        if(instance == null){
            instance = new MuzeuService();
        }
        return instance;
    }


    @Override
    public Muzeu readMuzeu() {

        Scanner scanner = new Scanner(System.in);
        Muzeu muzeu = new Muzeu();

        System.out.println("Nume: ");
        muzeu.setNume(scanner.nextLine());

        System.out.println("Locatie: ");
        muzeu.setLocatie(scanner.nextLine());

        System.out.println("Program: ");
        muzeu.setProgram(scanner.nextLine());

        System.out.println("Descriere: ");
        muzeu.setDescriere(scanner.nextLine());

        return muzeu;
    }

    @Override
    public Muzeu getMuzeu() {
        return this.muzee.get(0);
    }


    @Override
    public void add(Muzeu obj) throws SQLException {
        // daca lista cu muzee este goala, adauga muzeul
        if (this.muzee.isEmpty()) {
            this.muzee.add(obj);

            connection.getS().execute("INSERT INTO muzeu (nume, program, locatie, descriere) VALUES "+
                    "('"+obj.getNume()+"', '"+obj.getProgram()+"', '"+obj.getLocatie()+"', '"+obj.getDescriere()+"')");

        }
        else
            System.out.println("Poate exista un singur muzeu!");
    }

    @Override
    public List<Muzeu> findAll() {
        try{
            String select = "SELECT * FROM muzeu";
            ResultSet resultSet = connection.getS().executeQuery(select);

            List<Muzeu> muzee = new ArrayList<>();

            while (resultSet.next()){
                Muzeu muzeu = new Muzeu();
                muzeu.setNume(resultSet.getString("nume"));
                muzeu.setProgram(resultSet.getString("program"));
                muzeu.setLocatie(resultSet.getString("locatie"));
                muzeu.setDescriere(resultSet.getString("descriere"));
                muzee.add(muzeu);
            }
            return muzee;

        }catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Muzeu obj) {
        this.muzee.set(0, obj);
        try {
            delete(1);
            add(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        this.muzee.remove(index-1);
        try {
            connection.getS().execute("DELETE from muzeu ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
