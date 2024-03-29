package Service;

import Interfaces.AuditInterface;

public class AuditService implements AuditInterface {
    private static AuditService instance;

    private AuditService() {
    }

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

}