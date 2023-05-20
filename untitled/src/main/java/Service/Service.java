package Service;

import entities.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

public class Service {

    private final AuditService audit_service = AuditService.getInstance();
    private final AngajatService service_angajat = AngajatService.getInstance();
    private final BiletService service_bilet = BiletService.getInstance();
    private final TipBiletService service_tip_bilet = TipBiletService.getInstance();
    private final TurService service_tur = TurService.getInstance();
    private final ExpozitieService service_expozitie = ExpozitieService.getInstance();
    private final GalerieService service_galerie = GalerieService.getInstance();
    private final TablouService service_tablou = TablouService.getInstance();
    private final SculpturaService service_sculptura = SculpturaService.getInstance();
    private final RelicvaService service_relicva = RelicvaService.getInstance();
    private final MuzeuService service_muzeu = MuzeuService.getInstance();


    private static Service instance;
    private Scanner scanner = new Scanner(System.in);

    private Service() {
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public void printUserMenu() {
        System.out.println(" 1 - Vizitator");
        System.out.println(" 2 - Angajat");
        System.out.println(" 0 - Exit");
    }

    public void meniu() throws IOException, SQLException {
        while (true) {
            printUserMenu();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 2) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 2");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 2");
                }
            }

            if (option == 0) {
                break;
            } else if (option == 1) {
                meniuVizitator();
            } else if (option == 2) {
                meniuAngajat();
            }
        }
    }

    public void printMeniuVizitator() {
        System.out.println();
        System.out.println(" 1 - Rezervare bilet");
        System.out.println(" 2 - Afisare program muzeu");
        System.out.println(" 3 - Afisare tururi");
        System.out.println(" 4 - Afisare expozitii");
        System.out.println(" 0 - Exit");

    }

    public void meniuVizitator() throws SQLException {
        while (true) {
            printMeniuVizitator();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 4) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 4");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 4");
                }
            }

            if (option == 1) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Rezervare bilet de catre vizitator" , timeStamp);

                Bilet achizitie = service_bilet.readBilet();
                service_bilet.add(achizitie);

            } else if (option == 2) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare program muzeu" , timeStamp);

                Muzeu m = service_muzeu.getMuzeu();
                System.out.println(m.toString());

            } else if (option == 3) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare tururi" , timeStamp);

                List<Tur> tururi = service_tur.getTururi();
                System.out.println(tururi);

            } else if (option == 4) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare expozitii" , timeStamp);

                List<Expozitie> exp = service_expozitie.getExpozitii();
                System.out.println(exp);

            } else
                break;
        }
    }

    public void printMeniuAngajat() {
        System.out.println();
        System.out.println(" 1 - Afisare numar vizitatori");
        System.out.println(" 2 - Sortare exponate");
        System.out.println(" 3 - Admin");
        System.out.println(" 0 - Exit");
    }

    public void meniuAngajat() throws SQLException {
        while (true) {
            printMeniuAngajat();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 3) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 3");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 3");
                }
            }

            if (option == 1) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare numar vizitatori" , timeStamp);

                System.out.println("Introduceti numarul de luni pentru care doriti sa afisati numarul de vizitatori");
                int nr_luni = scanner.nextInt();
                System.out.println("Numarul de bilete vandute in ultimele "+ nr_luni +" luni este:");
                List<Bilet> bilete = service_bilet.getBiletePerioada(nr_luni);
                System.out.println(bilete.size());

            } else if (option == 2) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Sortare exponate" , timeStamp);

                System.out.println("Tablouri (sortare dupa stil, an si id): ");
                System.out.println(service_tablou.getTablouri());       // sortare dupa stil si an

                System.out.println("Sculpturi (sortare dupa stil si id):");
                System.out.println(service_sculptura.getSculpturi());   // sortare dupa stil si id

                System.out.println("Relicve (sortare dupa an si id):");
                System.out.println(service_relicva.getRelicve());       // sortare dupa an si id

            } else if (option == 3) {
                meniuAdmin();

            } else
                break;
        }
    }

    public void meniuAdmin() throws SQLException {
        while (true) {
            // print Meniu Admin
            System.out.println();
            System.out.println("Ce categorie doriti sa modificati?");
            System.out.println(" 1 - Angajati");
            System.out.println(" 2 - Bilete");
            System.out.println(" 3 - Tururi");
            System.out.println(" 4 - Expozitii");
            System.out.println(" 5 - Galerii");
            System.out.println(" 6 - Exponate");
            System.out.println(" 0 - Exit");

            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 6) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 6");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 6");
                }
            }
            if (option == 1) {
                CRUD_Angajati();
            } else if (option == 2) {
                CRUD_Bilete();
            } else if (option == 3) {
                CRUD_Tururi();
            } else if (option == 4) {
                CRUD_Expozitii();
            } else if (option == 5) {
                CRUD_Galerii();
            } else if (option == 6) {
                Meniu_exponate();
            } else if (option == 0) {
                break;
            }
        }
    }

    public void Meniu_exponate() throws SQLException {
        while (true) {
            System.out.println();
            System.out.println("Ce exponate doriti sa modificati?");
            System.out.println(" 1 - Tablouri");
            System.out.println(" 2 - Sculpturi");
            System.out.println(" 3 - Relicve");
            System.out.println(" 0 - Exit");

            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 3) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 3");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 3");
                }
            }
            if (option == 1) {
                CRUD_Tablouri();
            } else if (option == 2) {
                CRUD_Sculpturi();
            } else if (option == 3) {
                CRUD_Relicve();
            } else if (option == 0) {
                break;
            }
        }
    }

    public void printOptions() {
        System.out.println();
        System.out.println(" 1 - Get All");
        System.out.println(" 2 - Get By Id");
        System.out.println(" 3 - Add");
        System.out.println(" 4 - Update");
        System.out.println(" 5 - Delete");
        System.out.println(" 0 - Exit");
    }

    public void CRUD_Angajati() throws SQLException {
        while (true) {
            printOptions();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }

            if (option == 1) {  // get all
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Angajati" , timeStamp);

                if (service_angajat.getAngajati().size() == 0) {
                    System.out.println("Nu exista angajati!");
                }
                for (int i = 0; i < service_angajat.getAngajati().size(); i++) {
                    System.out.println(service_angajat.getAngajati().get(i).toString());
                }

            } else if (option == 2) {  // get by id
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Angajat" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul angajatului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_angajat.getAngajati().size() == 0) {
                    System.out.println("Nu exista angajati!");
                }
                boolean ok = false;
                for (int i = 0; i < service_angajat.getAngajati().size(); ++i) {
                    if (service_angajat.getAngajati().get(i).getID_ang() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(service_angajat.getAngajatById(index).toString());
                } else {
                    System.out.println("Nu exista angajati cu acest id");
                }

            } else if (option == 3) {  // add
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Adaugare Angajat" , timeStamp);

                Angajat angajat = service_angajat.readAngajat();
                service_angajat.add(angajat);

            } else if (option == 4) {  // update
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Editare Angajat" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul angajatului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_angajat.getAngajati().size() == 0) {
                    System.out.println("Nu exista angajati!");
                }
                boolean ok = false;
                for (int i = 0; i < service_angajat.getAngajati().size(); i++) {
                    if (service_angajat.getAngajati().get(i).getID_ang() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    Angajat angajat = service_angajat.readAngajat();
                    angajat.setID_ang(index);
                    service_angajat.update(angajat);
                } else {
                    System.out.println("Nu exista angajati cu acest id");
                }
            } else if (option == 5) {  // delete
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Stergere Angajat" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul angajatului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_angajat.getAngajati().size() == 0) {
                    System.out.println("Nu exista angajati!");
                }
                boolean ok = false;
                for (int i = 0; i < service_angajat.getAngajati().size(); i++) {
                    if (service_angajat.getAngajati().get(i).getID_ang() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    service_angajat.delete(index);
                } else {
                    System.out.println("Nu exista angajati cu acest id");
                }
            } else if (option == 0) {  // exit
                break;
            }

        }
    }

    public void CRUD_Bilete() throws SQLException {
        while (true) {
            printOptions();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }
            if (option == 1) {   // get all
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Bilete" , timeStamp);

                if (service_tip_bilet.getBilete().size() == 0) {
                    System.out.println("Nu exista bilete!");
                }
                for (int i = 0; i < service_tip_bilet.getBilete().size(); i++) {
                    System.out.println(service_tip_bilet.getBilete().get(i).toString());
                }
            } else if (option == 2) {    // get by id
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Bilet" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul biletului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tip_bilet.getBilete().size() == 0) {
                    System.out.println("Nu exista bilete!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tip_bilet.getBilete().size(); ++i) {
                    if (service_tip_bilet.getBilete().get(i).getID_bilet() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(service_tip_bilet.getBiletById(index).toString());
                } else {
                    System.out.println("Nu exista bilete cu acest id");
                }
            } else if (option == 3) {  // add
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Adaugare Bilet" , timeStamp);

                Bilet bilet = service_tip_bilet.readBilet();
                service_tip_bilet.add(bilet);

            } else if (option == 4) {  // update
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Editare Bilet" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul biletului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tip_bilet.getBilete().size() == 0) {
                    System.out.println("Nu exista bilete!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tip_bilet.getBilete().size(); i++) {
                    if (service_tip_bilet.getBilete().get(i).getID_bilet() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    Bilet bilet = service_tip_bilet.readBilet();
                    bilet.setID_bilet(index);
                    service_tip_bilet.update(bilet);
                } else {
                    System.out.println("Nu exista bilete cu acest id");
                }
            } else if (option == 5) {  // delete
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Stergere Bilet" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul biletului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tip_bilet.getBilete().size() == 0) {
                    System.out.println("Nu exista bilete!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tip_bilet.getBilete().size(); i++) {
                    if (service_tip_bilet.getBilete().get(i).getID_bilet() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    service_tip_bilet.delete(index);
                } else {
                    System.out.println("Nu exista bilete cu acest id");
                }
            } else if (option == 0) {
                break;
            }
        }

    }

    public void CRUD_Tururi() throws SQLException {
        while (true) {
            printOptions();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }
            if (option == 1) {   // get all
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Tururi" , timeStamp);

                if (service_tur.getTururi().size() == 0) {
                    System.out.println("Nu exista tururi!");
                }
                for (int i = 0; i < service_tur.getTururi().size(); i++) {
                    System.out.println(service_tur.getTururi().get(i).toString());
                }
            } else if (option == 2) {    // get by id
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Tur" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul turului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tur.getTururi().size() == 0) {
                    System.out.println("Nu exista tururi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tur.getTururi().size(); ++i) {
                    if (service_tur.getTururi().get(i).getID_tur() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(service_tur.getTurById(index).toString());
                } else {
                    System.out.println("Nu exista tururi cu acest id");
                }
            } else if (option == 3) {  // add
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Adaugare Tur" , timeStamp);

                Tur tur = service_tur.readTur();
                service_tur.add(tur);

            } else if (option == 4) {  // update
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Editare Tur" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul turului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tur.getTururi().size() == 0) {
                    System.out.println("Nu exista tururi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tur.getTururi().size(); i++) {
                    if (service_tur.getTururi().get(i).getID_tur() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    Tur tur = service_tur.readTur();
                    tur.setID_tur(index);
                    service_tur.update(tur);
                } else {
                    System.out.println("Nu exista tururi cu acest id");
                }
            } else if (option == 5) {  // delete
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Stergere Tur" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul turului: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tur.getTururi().size() == 0) {
                    System.out.println("Nu exista tururi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tur.getTururi().size(); i++) {
                    if (service_tur.getTururi().get(i).getID_tur() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    service_tur.delete(index);
                } else {
                    System.out.println("Nu exista tururi cu acest id");
                }
            } else if (option == 0) { // exit
                break;
            }
        }
    }

    public void CRUD_Expozitii() throws SQLException {
        while (true) {
            printOptions();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }
            if (option == 1) {   // get all
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Expozitii" , timeStamp);

                if (service_expozitie.getExpozitii().size() == 0) {
                    System.out.println("Nu exista expozitii!");
                }
                for (int i = 0; i < service_expozitie.getExpozitii().size(); i++) {
                    System.out.println(service_expozitie.getExpozitii().get(i).toString());
                }

            } else if (option == 2) { // get by id
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Expozitie" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul expozitiei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_expozitie.getExpozitii().size() == 0) {
                    System.out.println("Nu exista expozitii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_expozitie.getExpozitii().size(); ++i) {
                    if (service_expozitie.getExpozitii().get(i).getID_expozitie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(service_expozitie.getExpozitieById(index).toString());
                } else {
                    System.out.println("Nu exista expozitii cu acest id");
                }

            } else if (option == 3) {  // add
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Adaugare Expozitie" , timeStamp);

                Expozitie expozitie = service_expozitie.readExpozitie();
                service_expozitie.add(expozitie);

            } else if (option == 4) {  // update
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Editare Expozitie" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul expozitiei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_expozitie.getExpozitii().size() == 0) {
                    System.out.println("Nu exista expozitii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_expozitie.getExpozitii().size(); i++) {
                    if (service_expozitie.getExpozitii().get(i).getID_expozitie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    Expozitie expozitie = service_expozitie.readExpozitie();
                    expozitie.setID_expozitie(index);
                    service_expozitie.update(expozitie);
                } else {
                    System.out.println("Nu exista expozitii cu acest id");
                }

            } else if (option == 5) { // delete
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Stergere Expozitie" , timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul expozitiei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_expozitie.getExpozitii().size() == 0) {
                    System.out.println("Nu exista expozitii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_expozitie.getExpozitii().size(); i++) {
                    if (service_expozitie.getExpozitii().get(i).getID_expozitie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    service_expozitie.delete(index);
                } else {
                    System.out.println("Nu exista expozitii cu acest id");
                }
            } else if (option == 0) { // exit
                break;
            }
        }
    }

    public void CRUD_Galerii() throws SQLException {
        while (true) {
            printOptions();
            int option;
            while (true) {
                String line = scanner.nextLine();
                try {
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5) {
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e) {
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }
            if (option == 1){  // get all
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Galerii" , timeStamp);

                if (service_galerie.getGalerii().size() == 0) {
                    System.out.println("Nu exista galerii!");
                }
                for (int i = 0; i < service_galerie.getGalerii().size(); i++) {
                    System.out.println(service_galerie.getGalerii().get(i).toString());
                }

            } else if (option == 2) { // get by id
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Galerie", timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul galeriei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_galerie.getGalerii().size() == 0) {
                    System.out.println("Nu exista galerii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_galerie.getGalerii().size(); ++i) {
                    if (service_galerie.getGalerii().get(i).getID_galerie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(service_galerie.getGalerieById(index).toString());
                } else {
                    System.out.println("Nu exista galerii cu acest id");
                }

            }else if(option == 3){
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Adaugare Galerie", timeStamp);

                Galerie galerie = service_galerie.readGalerie();
                service_galerie.add(galerie);

            } else if (option == 4){  // update
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Editare Galerie", timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul galeriei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_galerie.getGalerii().size() == 0) {
                    System.out.println("Nu exista galerii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_galerie.getGalerii().size(); i++) {
                    if (service_galerie.getGalerii().get(i).getID_galerie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    Galerie galerie = service_galerie.readGalerie();
                    galerie.setID_galerie(index);
                    service_galerie.update(galerie);
                } else {
                    System.out.println("Nu exista galerii cu acest id");
                }
            } else if (option == 5){  // delete
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Stergere Galerie", timeStamp);

                int index;
                while (true) {
                    System.out.println("Introduceti id-ul galeriei: ");
                    String line = scanner.nextLine();
                    try {
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e) {
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_galerie.getGalerii().size() == 0) {
                    System.out.println("Nu exista galerii!");
                }
                boolean ok = false;
                for (int i = 0; i < service_galerie.getGalerii().size(); i++) {
                    if (service_galerie.getGalerii().get(i).getID_galerie() == index) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    service_galerie.delete(index);
                } else {
                    System.out.println("Nu exista galerii cu acest id");
                }
            } else if (option == 0) { // exit
                break;
            }
        }
    }

    public void CRUD_Tablouri() throws SQLException {
        while(true){
            printOptions();
            int option;
            while (true){
                String line = scanner.nextLine();
                try{
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5){
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e){
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }
            if (option == 1){   // get all
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Tablouri" , timeStamp);

                if (service_tablou.getTablouri().size() == 0){
                    System.out.println("Nu exista tablouri!");
                }
                for (int i = 0; i < service_tablou.getTablouri().size(); i++){
                    System.out.println(service_tablou.getTablouri().get(i).toString());
                }
            } else if (option == 2){  // get by id
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Tablou", timeStamp);

                int index;
                while (true){
                    System.out.println("Introduceti id-ul tabloului: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tablou.getTablouri().size() == 0){
                    System.out.println("Nu exista tablouri!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tablou.getTablouri().size(); ++i){
                    if (service_tablou.getTablouri().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    System.out.println(service_tablou.getTablouById(index).toString());
                } else {
                    System.out.println("Nu exista tablouri cu acest id");
                }

            } else if(option == 3){   // add
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Adaugare Tablou", timeStamp);

                Tablou tablou = service_tablou.readTablou();
                service_tablou.add(tablou);

            } else if (option == 4){   // update
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Editare Tablou", timeStamp);

                int index;
                while (true){
                    System.out.println("Introduceti id-ul tabloului: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tablou.getTablouri().size() == 0){
                    System.out.println("Nu exista tablouri!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tablou.getTablouri().size(); i++){
                    if (service_tablou.getTablouri().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    Tablou tablou = service_tablou.readTablou();
                    tablou.setID_exponat(index);
                    service_tablou.update(tablou);
                } else {
                    System.out.println("Nu exista tablouri cu acest id");
                }

            } else if (option == 5){   // delete
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Stergere Tablou", timeStamp);

                int index;
                while (true){
                    System.out.println("Introduceti id-ul tabloului: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_tablou.getTablouri().size() == 0){
                    System.out.println("Nu exista tablouri!");
                }
                boolean ok = false;
                for (int i = 0; i < service_tablou.getTablouri().size(); i++){
                    if (service_tablou.getTablouri().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    service_tablou.delete(index);
                } else {
                    System.out.println("Nu exista tablouri cu acest id");
                }
            } else if (option == 0){   // exit
                break;
            }
        }
    }

    public void CRUD_Sculpturi() throws SQLException {
        while (true){
            printOptions();
            int option;
            while (true){
                String line = scanner.nextLine();
                try{
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5){
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e){
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }

            if (option == 1){
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Sculpturi" , timeStamp);

                if (service_sculptura.getSculpturi().size() == 0){
                    System.out.println("Nu exista sculpturi!");
                }
                for (int i = 0; i < service_sculptura.getSculpturi().size(); i++){
                    System.out.println(service_sculptura.getSculpturi().get(i).toString());
                }
            }
            else if (option == 2){
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Sculptura", timeStamp);

                int index;
                while (true){
                    System.out.println("Introduceti id-ul sculpturii: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_sculptura.getSculpturi().size() == 0){
                    System.out.println("Nu exista sculpturi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_sculptura.getSculpturi().size(); ++i){
                    if (service_sculptura.getSculpturi().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    System.out.println(service_sculptura.getSculpturaById(index).toString());
                } else {
                    System.out.println("Nu exista sculpturi cu acest id");
                }
            }
            else if (option == 3){
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Adaugare Sculptura", timeStamp);

                Sculptura sculptura = service_sculptura.readSculptura();
                service_sculptura.add(sculptura);
            }
            else if (option == 4){
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Editare Sculptura", timeStamp);

                int index;
                while (true){
                    System.out.println("Introduceti id-ul sculpturii: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_sculptura.getSculpturi().size() == 0){
                    System.out.println("Nu exista sculpturi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_sculptura.getSculpturi().size(); i++){
                    if (service_sculptura.getSculpturi().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    Sculptura sculptura = service_sculptura.readSculptura();
                    sculptura.setID_exponat(index);
                    service_sculptura.update(sculptura);
                } else {
                    System.out.println("Nu exista sculpturi cu acest id");
                }
            }
            else if (option == 5){
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Stergere Sculptura", timeStamp);

                int index;
                while (true){
                    System.out.println("Introduceti id-ul sculpturii: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_sculptura.getSculpturi().size() == 0){
                    System.out.println("Nu exista sculpturi!");
                }
                boolean ok = false;
                for (int i = 0; i < service_sculptura.getSculpturi().size(); i++){
                    if (service_sculptura.getSculpturi().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    service_sculptura.delete(index);
                } else {
                    System.out.println("Nu exista sculpturi cu acest id");
                }
            }else if (option == 0){
                break;
            }
        }
    }

    public void CRUD_Relicve() throws SQLException {
        while(true){
            printOptions();
            int option;
            while (true){
                String line = scanner.nextLine();
                try{
                    option = Integer.parseInt(line);
                    if (option >= 0 && option <= 5){
                        break;
                    } else {
                        System.out.println("Introduceti un numar intre 0 si 5");
                    }
                } catch (Exception e){
                    System.out.println("Introduceti un numar intre 0 si 5");
                }
            }

            if (option == 1){
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Relicve" , timeStamp);

                if(service_relicva.getRelicve().size() == 0){
                    System.out.println("Nu exista relicve!");
                }
                for (int i = 0; i < service_relicva.getRelicve().size(); i++){
                    System.out.println(service_relicva.getRelicve().get(i).toString());
                }
            }
            else if (option == 2){
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Afisare Relicva", timeStamp);

                int index;
                while (true){
                    System.out.println("Introduceti id-ul relicvei: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_relicva.getRelicve().size() == 0){
                    System.out.println("Nu exista relicve!");
                }
                boolean ok = false;
                for (int i = 0; i < service_relicva.getRelicve().size(); ++i){
                    if (service_relicva.getRelicve().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    System.out.println(service_relicva.getRelicvaById(index).toString());
                } else {
                    System.out.println("Nu exista relicve cu acest id");
                }

            } else if (option == 3) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Adaugare Relicva", timeStamp);

                Relicva relicva = service_relicva.readRelicva();
                service_relicva.add(relicva);

            }else if (option == 4){
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Editare Relicva", timeStamp);

                int index;
                while (true){
                    System.out.println("Introduceti id-ul relicvei: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_relicva.getRelicve().size() == 0){
                    System.out.println("Nu exista relicve!");
                }
                boolean ok = false;
                for (int i = 0; i < service_relicva.getRelicve().size(); i++){
                    if (service_relicva.getRelicve().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    Relicva relicva = service_relicva.readRelicva();
                    relicva.setID_exponat(index);
                    service_relicva.update(relicva);
                } else {
                    System.out.println("Nu exista relicve cu acest id");
                }

            }else if (option == 5){
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String timeStamp = date.format(new Date());
                audit_service.audit("Stergere Relicva", timeStamp);

                int index;
                while (true){
                    System.out.println("Introduceti id-ul relicvei: ");
                    String line = scanner.nextLine();
                    try{
                        index = Integer.parseInt(line);
                        break;
                    } catch (Exception e){
                        System.out.println("Introduceti un numar!");
                    }
                }
                if (service_relicva.getRelicve().size() == 0){
                    System.out.println("Nu exista relicve!");
                }
                boolean ok = false;
                for (int i = 0; i < service_relicva.getRelicve().size(); i++){
                    if (service_relicva.getRelicve().get(i).getID_exponat() == index){
                        ok = true;
                        break;
                    }
                }
                if (ok){
                    service_relicva.delete(index);
                } else {
                    System.out.println("Nu exista relicve cu acest id");
                }

            } else if (option == 0){
                break;
            }
        }
    }

}
