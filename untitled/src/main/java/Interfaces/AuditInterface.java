package Interfaces;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface AuditInterface {

    default void audit(String actiune, String timestamp) {
        String path = "src/main/java/CSV_files/audit.csv";
        try {
            File csvAudit = new File(path);
            FileWriter writer = new FileWriter(path, true);
            if (csvAudit.isFile()) {
                if (csvAudit.length() == 0L) {
                    writer.append("Actiune,Timestamp\n");
                }
                String line = actiune + "," + timestamp + "\n";
                writer.append(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}

