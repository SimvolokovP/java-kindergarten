package test;

import Dao.DBRepository;
import Utils.DBManager;
import models.Child;
import models.Group;

public class App {
    public static void main(String[] args) {
        DBManager manager = new DBManager();
        Service service = new Service(new DBRepository(manager.getConnection()));
        service.showById(18);
    }
}
