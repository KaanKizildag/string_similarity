/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cumle_benzerligi.repository.db_helper;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author kaan
 */
public class DatabaseHelper {

    private final SessionFactory factory;

    private static DatabaseHelper INSTANCE;

    private DatabaseHelper() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static DatabaseHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseHelper();
        }
        return INSTANCE;
    }

    public SessionFactory getFactory() {
        return factory;
    }

}
