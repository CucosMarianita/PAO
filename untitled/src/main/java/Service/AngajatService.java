package Service;

import Interfaces.AngajatInterface;
import Service.Persistence.CRUD_Template;
import Service.Persistence.Conn;
import entities.Angajat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;


public class AngajatService implements AngajatInterface, CRUD_Template<Angajat> {

    private List<Angajat> angajati = new ArrayList<>();
    private Conn connection;
    private static AngajatService instance;


    private AngajatService(){
        connection = new Conn();
        angajati = findAll();    // (citire din db)
    }

    public static AngajatService getInstance(){
        if(instance == null){
            instance = new AngajatService();
        }
        return instance;
    }


    public int getMaxId(){
        int max = 0;
        for(int i = 0; i < angajati.size(); i++){
            if(angajati.get(i).getID_ang() > max){
                max = angajati.get(i).getID_ang();
            }
        }
        return max;
    }

    @Override
    public Angajat readAngajat() {

        Scanner scanner = new Scanner(System.in);
        Angajat angajat = new Angajat();

        int id = getMaxId() + 1;
        angajat.setID_ang(id);

        System.out.println("Prenume: ");
        angajat.setPrenume(scanner.nextLine());

        System.out.println("Nume: ");
        angajat.setNume(scanner.nextLine());

        System.out.println("CNP: ");
        while (true) {
            String cnp = scanner.nextLine();
            try{
                Long.parseLong(cnp);
            } catch (NumberFormatException e){
                System.out.println("CNP-ul trebuie sa contina doar cifre!");
            }
            if (cnp.length() == 13) {
                angajat.setCNP(cnp);
                break;
            } else {
                System.out.println("CNP-ul trebuie sa aiba 13 cifre!");
            }
        }

        System.out.println("Salariu: ");
        while(true){
            try {
                angajat.setSalariu(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e){
                System.out.println("Salariul trebuie sa fie un numar!");
            }
        }

        System.out.println("Departament: ");
        angajat.setDepartament(scanner.nextLine());

        System.out.println("Telefon: ");
        while(true){
            String tel = scanner.nextLine();
            try {
                Integer.parseInt(tel);
            } catch (NumberFormatException e) {
                System.out.println("Numarul de telefon trebuie sa contina doar cifre!");
            }
            if(tel.length() == 10){
                angajat.setTelefon(tel);
                break;
            } else {
                System.out.println("Numarul de telefon trebuie sa aiba 10 cifre!");
            }
        }

        System.out.println("Data angajarii(yyyy-mm-dd): ");
        while (true) {
            try {
                angajat.setStartDate(LocalDate.parse(scanner.nextLine()));
                break;

            } catch (Exception e) {
                System.out.println("Data trebuie sa fie de forma: yyyy-mm-dd");
            }
        }


        System.out.println("Cod angajat: ");
        boolean cauta = true;
        while(cauta){
            String cod = scanner.nextLine();
            boolean cod_ok = true;

            for(int i = 0; i < angajati.size(); i++){
                if(angajati.get(i).getCod_ang() == Integer.parseInt(cod)){
                    cod_ok = false;
                    System.out.println("Codul este deja existent! Reintroduceti: ");
                }
            }
            if(cod_ok == true) {
                cauta = false;
                angajat.setCod_ang(Integer.parseInt(cod));
            }
        }

        return angajat;
    }

    @Override
    public Map<Integer, String> getGhizi() {
        Map<Integer, String> ghizi = new HashMap<Integer, String>();
        for(Angajat ang : angajati){
            if(ang.getDepartament().toUpperCase().equals("GHID")){
                ghizi.put(ang.getID_ang(), ang.getNume() + " " + ang.getPrenume());
            }
        }
        return ghizi;
    }


    @Override
    public List<Angajat> getAngajati() {
        List<Angajat> angajatiCopy = new ArrayList<>();
        angajatiCopy.addAll(this.angajati);
        // sortare dupa salariu
        Collections.sort(angajatiCopy, new Comparator<Angajat>() {
            @Override
            public int compare(Angajat ang1, Angajat ang2) {
                if (ang1.getSalariu() - ang2.getSalariu() > 0){
                    return 1;
                }
                return 0;
            }
        });
        return angajatiCopy;
    }


    @Override
    public Angajat getAngajatById(int index) {
        Angajat angajat = new Angajat();
        for(int i = 0; i < this.angajati.size(); ++i){
            if(this.angajati.get(i).getID_ang() == index){
                angajat = this.angajati.get(i);
            }
        }
        return angajat;
    }


    @Override
    public void add(Angajat obj) throws SQLException {
        this.angajati.add(obj);


        String insert = "INSERT INTO angajat (ID_ang, nume, prenume, CNP, departament, salariu, telefon, cod_ang, startDate )" +
                    " VALUES (" + obj.getID_ang() + ", '" + obj.getNume() + "', '" + obj.getPrenume() + "', '" + obj.getCNP() + "', '" + obj.getDepartament() + "', " + obj.getSalariu() + ", '" + obj.getTelefon() + "', " + obj.getCod_ang() + ", '" + obj.getStartDate() + "')";

        connection.getS().execute(insert);

    }

    public List<Angajat> findAll() {
        try{
           String select = "SELECT * FROM angajat";
           ResultSet rs = connection.getS().executeQuery(select);

           List<Angajat> angajati = new ArrayList<>();

           while (rs.next()) {
               Angajat angajat = new Angajat();
               angajat.setID_ang(rs.getInt("ID_ang"));
               angajat.setNume(rs.getString("nume"));
               angajat.setPrenume(rs.getString("prenume"));
               angajat.setCNP(rs.getString("CNP"));
               angajat.setDepartament(rs.getString("departament"));
               angajat.setSalariu(rs.getInt("salariu"));
               angajat.setTelefon(rs.getString("telefon"));
               angajat.setCod_ang(rs.getInt("cod_ang"));
               angajat.setStartDate(rs.getDate("startDate").toLocalDate());

               angajati.add(angajat);
           }
           rs.close();

           return angajati;


        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Angajat obj) {
        this.angajati.set(obj.getID_ang()-1, obj);
        try{
            connection.getS().execute("update angajat set nume = " + obj.getNume() + ", prenume = " + obj.getPrenume() + ", CNP = " + obj.getCNP() +
                    ", departament = " + obj.getDepartament() + ", salariu = " + obj.getSalariu() + ", telefon = " + obj.getTelefon()
                    + ", cod_ang = " + obj.getCod_ang() + ", startDate = " + obj.getStartDate() + " where ID_ang = " + obj.getID_ang() );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(int index) {
        for(int i = 0; i < this.angajati.size(); i++){
            if(this.angajati.get(i).getID_ang() == index){
                this.angajati.remove(i);
                break;
            }
        }
        try{
            connection.getS().execute("delete from angajat where ID_ang = " + index );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

