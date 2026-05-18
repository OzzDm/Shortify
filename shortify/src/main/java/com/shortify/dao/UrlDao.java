package com.shortify.dao;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shortify.model.Url;

@Repository
public class UrlDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public void saveUrl(Url url){
        Session session = sessionFactory.getCurrentSession();
        session.persist(url);
    }

    public Url fetchUrlByShortCode(String shortCode) {
        Session session =  sessionFactory.getCurrentSession();
        Url url = session.createSelectionQuery("from Url where shortCode = :shortCode and expiryDateTime > :currentDateTime", Url.class)
                        .setParameter("shortCode", shortCode)
                        .setParameter("currentDateTime", LocalDateTime.now())
                        .getSingleResultOrNull();
        return url;
    }

    public void incrementClickCount(Long urlId) {
        Session session = sessionFactory.getCurrentSession();
        int entitiesUpdated = session.createMutationQuery("UPDATE Url set clickCount = clickCount + 1 WHERE urlId = :urlId")
                         .setParameter("urlId", urlId)
                         .executeUpdate();

        if(entitiesUpdated > 0){
            System.out.println("Click Count Updated Successfully!");
        }
    }
}
