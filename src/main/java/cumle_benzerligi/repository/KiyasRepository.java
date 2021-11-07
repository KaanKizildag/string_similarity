/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cumle_benzerligi.repository;

import cumle_benzerligi.entity.Kiyas;
import cumle_benzerligi.repository.db_helper.DatabaseHelper;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author kaan
 */
public class KiyasRepository {

    DatabaseHelper database = DatabaseHelper.getInstance();
    private static KiyasRepository INSTANCE;

    public static KiyasRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KiyasRepository();
        }
        return INSTANCE;
    }

    private KiyasRepository() {
    }

    public void insert(Kiyas kiyas) {
        try (Session session = database.getFactory().getCurrentSession()) {
            session.beginTransaction();

            session.persist(kiyas);

            session.getTransaction().commit();
        }
    }

    public void insert(List<Kiyas> kiyaslar) {
        try (Session session = database.getFactory().getCurrentSession()) {
            session.beginTransaction();

            for (int i = 0; i < kiyaslar.size(); i++) {

                session.save(kiyaslar.get(i));

                if (i % 1000 == 0) {
                    System.out.println("clear session");
                    session.flush();
                    session.clear();
                }

            }
            session.getTransaction().commit();

        }
    }

    public List<Kiyas> getKiyasOrderByBenzerlikDesc() {
        List<Kiyas> kiyaslar;
        try (Session session = database.getFactory().getCurrentSession()) {
            session.beginTransaction();

            kiyaslar = session.createQuery("from Kiyas order by benzerlik desc").list();

            session.getTransaction().commit();
        }
        return kiyaslar;
    }

}
