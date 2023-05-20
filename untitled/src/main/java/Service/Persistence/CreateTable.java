package Service.Persistence;

import java.sql.*;
public class CreateTable {

    public static void main(String[] args) throws SQLException {
        try {
            Conn connection = new Conn();

            String createAngajat = "CREATE TABLE ANGAJAT(" +
                    "ID_ang INTEGER , PRIMARY KEY(ID_ang) ," +
                    "nume VARCHAR(255) ," +
                    "prenume VARCHAR(255) ," +
                    "CNP VARCHAR(255) ," +
                    "departament VARCHAR(255) ," +
                    "salariu INTEGER ," +
                    "telefon VARCHAR(255) ," +
                    "cod_ang INTEGER ," +
                    "startDate VARCHAR(255) )";

            String createTur = "CREATE TABLE TUR(" +
                    "ID_tur INTEGER , PRIMARY KEY(ID_tur) ," +
                    "durata VARCHAR(255) ," +
                    "descriere VARCHAR(255) ," +
                    "ID_ghid INTEGER , FOREIGN KEY (ID_ghid) REFERENCES ANGAJAT(ID_ang) )";

            String createExpozitie = "CREATE TABLE EXPOZITIE(" +
                    "ID_expozitie INTEGER , PRIMARY KEY(ID_expozitie) ," +
                    "descriere VARCHAR(255) ," +
                    "ID_tur INTEGER , FOREIGN KEY (ID_tur) REFERENCES TUR(ID_tur) )";

            String createExpozitieTemporara = "CREATE TABLE EXPOZITIE_TEMPORARA(" +
                    "ID_expozitie INTEGER , PRIMARY KEY(ID_expozitie) ," +
                    "perioada VARCHAR(255) ," +
                    "descriere VARCHAR(255) ," +
                    "ID_tur INTEGER , FOREIGN KEY (ID_tur) REFERENCES TUR(ID_tur) )";

            String createBilet = "CREATE TABLE BILET(" +
                    "ID_bilet INTEGER , PRIMARY KEY(ID_bilet) ," +
                    "tip VARCHAR(255) ," +
                    "pret INTEGER ," +
                    "achitat VARCHAR(255) ," +
                    "descriere VARCHAR(255) ," +
                    "data_achizitie VARCHAR(255) ," +
                    "ID_expozitie INTEGER , FOREIGN KEY (ID_expozitie) REFERENCES EXPOZITIE(ID_expozitie) )";

            String createGalerie = "CREATE TABLE GALERIE(" +
                    "ID_galerie INTEGER , PRIMARY KEY(ID_galerie) ," +
                    "nume VARCHAR(255) ," +
                    "ID_expozitie INTEGER , FOREIGN KEY (ID_expozitie) REFERENCES EXPOZITIE(ID_expozitie) )";

            String createMuzeu = "CREATE TABLE MUZEU(" +
                    "nume varchar(255) ," +
                    "program VARCHAR(255) ," +
                    "locatie VARCHAR(255) ," +
                    "descriere VARCHAR(255) )";

            String createRelicva = "CREATE TABLE RELICVA(" +
                    "ID_exponat INTEGER , PRIMARY KEY(ID_exponat) ," +
                    "denumire VARCHAR(255) ," +
                    "an INTEGER ," +
                    "locatie VARCHAR(255) ," +
                    "descriere VARCHAR(255) ," +
                    "ID_galerie INTEGER , FOREIGN KEY (ID_galerie) REFERENCES GALERIE(ID_galerie) )";

            String createSculptura = "CREATE TABLE SCULPTURA(" +
                    "ID_exponat INTEGER , PRIMARY KEY(ID_exponat) ," +
                    "denumire VARCHAR(255) ," +
                    "an INTEGER ," +
                    "stil VARCHAR(255) ," +
                    "material VARCHAR(255) ," +
                    "descriere VARCHAR(255) ," +
                    "ID_galerie INTEGER , FOREIGN KEY (ID_galerie) REFERENCES GALERIE(ID_galerie) )";

            String createTablou = "CREATE TABLE TABLOU(" +
                    "ID_exponat INTEGER , PRIMARY KEY(ID_exponat) ," +
                    "denumire VARCHAR(255) ," +
                    "an INTEGER ," +
                    "stil VARCHAR(255) ," +
                    "tehnica VARCHAR(255) ," +
                    "descriere VARCHAR(255) ," +
                    "ID_galerie INTEGER , FOREIGN KEY (ID_galerie) REFERENCES GALERIE(ID_galerie) )";



            try {

                connection.s.execute("BEGIN\n" +
                        "    EXECUTE IMMEDIATE 'DROP TABLE ANGAJAT CASCADE CONSTRAINTS';\n" +
                        "EXCEPTION\n" +
                        "    WHEN OTHERS THEN\n" +
                        "        IF SQLCODE != -942 THEN\n" +
                        "            RAISE;\n" +
                        "        END IF;\n" +
                        "END;\n" );
                connection.s.execute(createAngajat);

                connection.s.execute("BEGIN\n" + "    EXECUTE IMMEDIATE 'DROP TABLE TUR CASCADE CONSTRAINTS';\n" + "EXCEPTION\n" + "    WHEN OTHERS THEN\n" + "        IF SQLCODE != -942 THEN\n" + "            RAISE;\n" + "        END IF;\n" + "END;\n" );
                connection.s.execute(createTur);

                connection.s.execute("BEGIN\n" + "    EXECUTE IMMEDIATE 'DROP TABLE EXPOZITIE CASCADE CONSTRAINTS';\n" + "EXCEPTION\n" + "    WHEN OTHERS THEN\n" + "        IF SQLCODE != -942 THEN\n" + "            RAISE;\n" + "        END IF;\n" + "END;\n" );
                connection.s.execute(createExpozitie);

                connection.s.execute("BEGIN\n" + "    EXECUTE IMMEDIATE 'DROP TABLE EXPOZITIE_TEMPORARA CASCADE CONSTRAINTS';\n" + "EXCEPTION\n" + "    WHEN OTHERS THEN\n" + "        IF SQLCODE != -942 THEN\n" + "            RAISE;\n" + "        END IF;\n" + "END;\n" );
                connection.s.execute(createExpozitieTemporara);

                connection.s.execute("BEGIN\n" + "    EXECUTE IMMEDIATE 'DROP TABLE BILET CASCADE CONSTRAINTS';\n" + "EXCEPTION\n" + "    WHEN OTHERS THEN\n" + "        IF SQLCODE != -942 THEN\n" + "            RAISE;\n" + "        END IF;\n" + "END;\n" );
                connection.s.execute(createBilet);

                connection.s.execute("BEGIN\n" + "    EXECUTE IMMEDIATE 'DROP TABLE GALERIE CASCADE CONSTRAINTS';\n" + "EXCEPTION\n" + "    WHEN OTHERS THEN\n" + "        IF SQLCODE != -942 THEN\n" + "            RAISE;\n" + "        END IF;\n" + "END;\n" );
                connection.s.execute(createGalerie);

                connection.s.execute("BEGIN\n" + "    EXECUTE IMMEDIATE 'DROP TABLE MUZEU CASCADE CONSTRAINTS';\n" + "EXCEPTION\n" + "    WHEN OTHERS THEN\n" + "        IF SQLCODE != -942 THEN\n" + "            RAISE;\n" + "        END IF;\n" + "END;\n" );
                connection.s.execute(createMuzeu);

                connection.s.execute("BEGIN\n" + "    EXECUTE IMMEDIATE 'DROP TABLE RELICVA CASCADE CONSTRAINTS';\n" + "EXCEPTION\n" + "    WHEN OTHERS THEN\n" + "        IF SQLCODE != -942 THEN\n" + "            RAISE;\n" + "        END IF;\n" + "END;\n" );
                connection.s.execute(createRelicva);

                connection.s.execute("BEGIN\n" + "    EXECUTE IMMEDIATE 'DROP TABLE SCULPTURA CASCADE CONSTRAINTS';\n" + "EXCEPTION\n" + "    WHEN OTHERS THEN\n" + "        IF SQLCODE != -942 THEN\n" + "            RAISE;\n" + "        END IF;\n" + "END;\n" );
                connection.s.execute(createSculptura);

                connection.s.execute("BEGIN\n" + "    EXECUTE IMMEDIATE 'DROP TABLE TABLOU CASCADE CONSTRAINTS';\n" + "EXCEPTION\n" + "    WHEN OTHERS THEN\n" + "        IF SQLCODE != -942 THEN\n" + "            RAISE;\n" + "        END IF;\n" + "END;\n");
                connection.s.execute(createTablou);

                System.out.println("Table created successfully");

            } catch (Exception e) {
                e.printStackTrace();
            }

            connection.c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}